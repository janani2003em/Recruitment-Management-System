import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class Job extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        String email = request.getParameter("email");
        String pass = request.getParameter("pwd");
        String job = request.getParameter("apply");
        pw.println("there");
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RecruitmentSystem", "root", "root");
            PreparedStatement pst = con.prepareStatement("Select * from recruits where email=?");
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                pw.println("there");
                String fname=rs.getString(1); 
                String lname=rs.getString(2);
                String gender=rs.getString(4);
                String age=rs.getString(5);
                PreparedStatement ps = con.prepareStatement("insert into applications values(?,?,?,?,?,?)");
                ps.setString(1,fname);
                ps.setString(2,lname);
                ps.setString(3,email);
                ps.setString(4,gender);
                ps.setString(5,age);
                ps.setString(6,job); 
                int i = ps.executeUpdate();
                pw.println("<script type='text/javascript'>");
                pw.println("alert(" + "'Applied !!!'" + ");</script>");
                pw.println("</head><body></body></html>");
                request.getRequestDispatcher("login.html").include(request, response);
            }
            else {
                pw.println("not there");
                pw.println("<script type='text/javascript'>");
                pw.println("alert(" + "'Create an account and profile to apply for jobs !!!'" + ");</script>");
                pw.println("</head><body></body></html>");
                request.getRequestDispatcher("login.html").include(request, response);
            }
            con.close();
        }
        catch(Exception e)
        {
            pw.println(e);
        }
    }
}
