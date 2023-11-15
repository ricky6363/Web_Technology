import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
public class p10_sessid1 extends HttpServlet {  
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{  
        try{  
        response.setContentType("text/html");  
        PrintWriter out = response.getWriter();  
        String n=request.getParameter("userName");
        String sp=request.getParameter("sport");  
        out.print("Welcome "+n+", and your favourite Sport is"+sp);   
        HttpSession session=request.getSession();  
        session.setAttribute("login_name",n);
        session.setAttribute("fav_sport",sp);  
        out.print("<a href='sessid2'>visit</a>");           
        out.close(); 
        }catch(Exception e){System.out.println(e);}  
    }
}  