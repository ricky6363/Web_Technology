import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
public class p11_hitcount extends HttpServlet 
{
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        // Set response content type
        response.setContentType("text/html");
        PrintWriter pwriter = response.getWriter();
        String in_uname = request.getParameter("uname");
        String l_name = request.getParameter("lname");
        String sp = request.getParameter("sp");
        String pos = request.getParameter("pos");

        pwriter.print("<h3>Welcome:</h3>"+in_uname+" "+l_name);

        pwriter.println("<br /> Here is your Answers for filling the form");

        pwriter.print("<h3>Name: </h3>"+in_uname);
        pwriter.print("<h3>Last Name: </h3>" + l_name); 
        pwriter.print("<h3>Sport You chose :</h3>"+sp);
        pwriter.print("<h3>Your position in the  Sport  is </h3>"+pos);
        pwriter.close();
    }
    public void destroy() {
    //do nothing
    }
}