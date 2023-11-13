import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class p12_db3 extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3307/student";
        String dbUser = "root";
        String dbPassword = "";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish a database connection
            try (Connection conn = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
                String insertSQL = "INSERT INTO student (DeptID, DeptName, NOS, studentName, studentDept, studentSport) VALUES (?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                    pstmt.setString(1, request.getParameter("depid"));
                    pstmt.setString(2, request.getParameter("depname"));
                    pstmt.setInt(3, Integer.valueOf(request.getParameter("depnos")));
                    pstmt.setString(4, request.getParameter("std_name"));
                    pstmt.setString(5, request.getParameter("std_dept"));
                    pstmt.setString(6, request.getParameter("std_sport"));

                    // Execute the insert command using executeUpdate()
                    int rowsInserted = pstmt.executeUpdate();

                    if (rowsInserted > 0) {
                        out.println("<html><body><p>Record inserted successfully.</p>");
                    } else {
                        out.println("<html><body><p>Failed to insert the record.</p>");
                    }
                }

                // Retrieve and display data
                String selectSQL = "SELECT * FROM student";
                try (PreparedStatement selectStmt = conn.prepareStatement(selectSQL);
                    ResultSet rs = selectStmt.executeQuery()) {
                    while (rs.next()) {
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
                }
                out.println("</body></html>");
            }
        } catch (ClassNotFoundException | SQLException e) {
            out.println("<html><body><p>Error: " + e.getMessage() + "</p></body></html>");
        }
    }
}
