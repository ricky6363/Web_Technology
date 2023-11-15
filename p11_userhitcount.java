import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class p11_userhitcount extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int score = 0;

        String[] userAnswers = new String[5];
        userAnswers[0] = request.getParameter("q1");
        userAnswers[1] = request.getParameter("q2");
        userAnswers[2] = request.getParameter("q3");
        userAnswers[3] = request.getParameter("q4");
        userAnswers[4] = request.getParameter("q5");

        Connection connection = null;

        try {
            String url = "jdbc:mysql://localhost:3307/results";
            String dbUsername = "root";
            String dbPassword = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT * FROM exam";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            String[] correctAnswers = new String[5];
            int questionNumber = 0;

            while (resultSet.next()) {
                questionNumber = resultSet.getInt("Qno");
                correctAnswers[questionNumber - 1] = resultSet.getString("Answer");
            }

            for (int i = 0; i < 5; i++) {
                if (userAnswers[i] != null && userAnswers[i].equals(correctAnswers[i])) {
                    score++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.write("<h1>You have Scored: " + score + "</h1>"); 
        
    }
}
