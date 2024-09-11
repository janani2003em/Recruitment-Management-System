import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig(maxFileSize = 16177215)
public class Fillform extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String fname = request.getParameter("first-name");
        String lname = request.getParameter("last-name");
        String email = request.getParameter("email");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String deg = request.getParameter("degree");
        String maj = request.getParameter("major");
        InputStream inputStream = null;
        String message = null;
        Part filePart = request.getPart("resume");
        if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());
            inputStream = filePart.getInputStream();
        }
         try
        {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RecruitmentSystem","root","root");
                PreparedStatement ps = con.prepareStatement("insert into Recruits values(?,?,?,?,?,?,?,?)");
                ps.setString(1,fname);
                ps.setString(2,lname);
                ps.setString(3,email);
                ps.setString(4,gender);
                ps.setString(5,age);
                ps.setString(6,deg);
                ps.setString(7,maj);
                ps.setBlob(8, inputStream);  
                int i = ps.executeUpdate();
                pw.println("<script type='text/javascript'>");
                pw.println("alert(" + "'Profile Created Successfully !!!'" + ");</script>");
                pw.println("</head><body></body></html>");
            request.getRequestDispatcher("index.html").include(request, response);
            con.close();
        }
        catch(Exception e)
        {
            pw.println(e);
        }
    }
}
