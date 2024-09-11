import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class SignUp extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String name = request.getParameter("uname");
        String email = request.getParameter("email");
        String pass = request.getParameter("pwd");
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RecruitmentSystem", "root", "root");
            PreparedStatement pst = con.prepareStatement("Select * from userdetails where email=? or username=?");
            pst.setString(1, email);
            pst.setString(2, name);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                pw.println("<script type='text/javascript'>");
                pw.println("alert(" + "'User Name Already Exists !!!'" + ");</script>");
                pw.println("</head><body></body></html>");
                request.getRequestDispatcher("index.html").include(request, response);
            }
            else {
                PreparedStatement ps = con.prepareStatement("insert into userdetails values(?,?,?)");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, pass);
                int i = ps.executeUpdate();
                request.getRequestDispatcher("form.html").include(request, response);
            }
            con.close();
        }
        catch(Exception e)
        {
            pw.println(e);
        }
    }
}

