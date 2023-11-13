import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class p12_db1 extends HttpServlet{
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Connection conn=null;
        Statement stmt=null;
        PrintWriter out = response.getWriter();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            //out.println("<h1> into class</h1>");
            //create a database connection using jdbc , port no used here is 3306
            // database name is college and username is root , there is no password
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/student","root", "");
            if(conn!=null)
            {
                out.println("<h1> No issues in Connection</h1>");
            }
            stmt = conn.createStatement();
            String sql;
            //display all the students' marks
            sql = "SELECT * FROM student";
            ResultSet rs = stmt.executeQuery(sql);
            // Extract data from result set
            while(rs.next())
            {
                String did = rs.getString("DeptID");
                        String dname = rs.getString("DeptName");
                        int dnos = rs.getInt("NOS");
                        String std_Name = rs.getString("studentName");
                        String std_Dept = rs.getString("studentDept");
                        String std_Sport = rs.getString("studentSport");

                        // Display values
                        out.println("<p>DeptID: " + did + "<br>");
                        out.println("DeptName: " + dname + "<br>");
                        out.println("Number of Students: " + dnos + "<br></p>");
                        out.println("Student Name: " + std_Name + "<br>");
                        out.println("Student Department: " + std_Dept + "<br>");
                        out.println("Student Sport: " + std_Sport + "<br>");
            }
            out.println("</body></html>");
            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.print("Do not connect to DB - Error:"+e);
        }
    }
}