import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
public class p9_u1 extends HttpServlet {  
public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{  
        try{  
            response.setContentType("text/html");  
            PrintWriter out = response.getWriter();  
            String n=request.getParameter("userName");
            String sp=request.getParameter("sport");  
  
            out.print("Welcome "+n);  
            //appending the username in the query string  
            out.print("<a href='url2?uname="+n+"sport="+sp+"'>visit the next page</a>");  
            out.close();  
        }
        catch(Exception e){System.out.println(e);}  
    } 
}  