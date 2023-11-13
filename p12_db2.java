import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class p12_db2 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String department = request.getParameter("depname");
        Connection conn = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            // Create a database connection using JDBC
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/student", "root", "");

            String sql = "SELECT * FROM student WHERE studentDept = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, department);
                ResultSet rs = pstmt.executeQuery();

                out.println("<html><body>"); // Open HTML tags

                while (rs.next()) {
                    String did = rs.getString("DeptID");
                    String dname = rs.getString("DeptName");
                    int dnos = rs.getInt("NOS");
                    String stdName = rs.getString("studentName");
                    String stdDept = rs.getString("studentDept");
                    String stdSport = rs.getString("studentSport");

                    out.println("<p>DeptID: " + did + "<br>");
                    out.println("DeptName: " + dname + "<br>");
                    out.println("Number of Students: " + dnos + "<br></p>");
                    out.println("Student Name: " + stdName + "<br>");
                    out.println("Student Department: " + stdDept + "<br>");
                    out.println("Student Sport: " + stdSport + "<br>");
                }

                out.println("</body></html>"); // Close HTML tags
            }
        } catch (Exception e) {
            out.print("Failed to connect to the database - Error: " + e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
