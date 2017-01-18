package ua.nung.education;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/users")
public class ConnectionPoolServlet extends javax.servlet.http.HttpServlet {

    /**
     * Searching the Data source in JNDI using the annotation @Resource
     */

    //@Resource(name = "jdbc/ConnPoolTestDB")
    DataSource dataSource;
    Connection connect = null;

    public void init() throws ServletException {

        /**
         * Searching the Data source in JNDI using the lookup service
         */

        try {
            Context ctx = new InitialContext();
            Context environmentContext = (Context) ctx.lookup("java:comp/env");
            dataSource = (DataSource) environmentContext.lookup("jdbc/ConnPoolTestDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try {
            connect = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        try {
            PreparedStatement pstmt = connect.prepareStatement("SELECT id, login, password, createDate FROM user");
            ResultSet rs = pstmt.executeQuery();

            out.print("<table border='1'>" +
                "<tr>" +
                    "<th>ID</th>" +
                    "<th>Login</th>" +
                    "<th>Password</th>" +
                    "<th>Create date</th>" +
                "</tr>");

            while (rs.next()) {
                out.print("<tr>");
                out.print("<td>" + rs.getInt(1) + "</td>");
                out.print("<td>" + rs.getString(2) + "</td>");
                out.print("<td>" + rs.getString(3) + "</td>");
                out.print("<td>" + rs.getTimestamp(4) + "</td>");
                out.print("</tr>");
            }

            out.print("</table>");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}