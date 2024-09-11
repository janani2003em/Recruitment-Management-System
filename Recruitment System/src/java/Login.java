import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String adm = request.getParameter("admin/user");
        String email = request.getParameter("email");
        String pass = request.getParameter("pwd");
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/RecruitmentSystem","root","root");
            PreparedStatement pst = conn.prepareStatement("Select * from userdetails where email=? and password=?");
            pst.setString(1, email);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();
            if ("admin".equals(adm) && "admin@gmail.com".equals(email) && "1234".equals(pass)){
                    request.getRequestDispatcher("adminhome.html").include(request, response);
            }
            else if (rs.next()) {
                    request.getRequestDispatcher("form.html").include(request, response);
            }
            else {
                out.println("<script type='text/javascript'>");
                out.println("alert(" + "'User Name or Password Incorrect !!!'" + ");</script>");
                out.println("</head><body></body></html>");
                request.getRequestDispatcher("index.html").include(request, response);
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
