<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML5>
<html>
<head>
    <title>JNDIConnectionPool</title>
</head>
<body>

    <%
        List<String> tables = (ArrayList<String>)request.getAttribute("dbtables");
    %>

    <table border="1">
        <tr>
            <th>#</th>
            <th>Name</th>
        </tr>

        <% for (int i=0; i < tables.size(); i++) { %>
        <tr>
            <td><%= i %></td>
            <td><%= tables.get(i) %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>