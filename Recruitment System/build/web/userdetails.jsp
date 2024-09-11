<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
String driverName = "org.apache.derby.jdbc.ClientDriver";
String connectionUrl = "jdbc:derby://localhost:1527/RecruitmentSystem";
String userId = "root";
String password = "root";

try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<h2 align="center"><font><strong>Recruitment System </strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td><b>fname</b></td>
<td><b>lname</b></td>
<td><b>email</b></td>
<td><b>gender</b></td>
<td><b>age</b></td>
<td><b>job_applied</b></td>

</tr>
<%
try{ 
connection = DriverManager.getConnection(connectionUrl, userId, password);
statement=connection.createStatement();
String sql ="SELECT * FROM APPLICATIONS";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr bgcolor="blue">

<td><%=resultSet.getString("firstname") %></td>
<td><%=resultSet.getString("lastname") %></td>
<td><%=resultSet.getString("email") %></td>
<td><%=resultSet.getString("gender") %></td>
<td><%=resultSet.getString("age") %></td>
<td><%=resultSet.getString("job_applied") %></td>
</tr>

<% 
}

} catch (Exception e) {
e.printStackTrace();
}
%>
</table>