import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;  
public class p8_h2 extends HttpServlet {  
        /*
        public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{  
                try
                {  
                        response.setContentType("text/html");  
                        PrintWriter out = response.getWriter();  
                        //Getting the value from the hidden field  
                        String n=request.getParameter("uname");  
                        out.print("Hello "+n+". Good to be back.");  
                        out.close();  
                }
                catch(Exception e){System.out.println(e);}  
        }  
        public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{  
                doGet(request,response);  
        } */ 
        public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{  
                try
                {  
                        response.setContentType("text/html");  
                        PrintWriter out = response.getWriter();  
                        //Getting the value from the hidden field  
                        String n=request.getParameter("uname"); 
                        String SP=request.getParameter("SPORT");  
 
                        out.print("Hello "+n+". Good to be back.");
                        out.print("Hello "+n+". You have picked the sport."+SP);  
  
                        out.close();  
                }
                catch(Exception e){System.out.println(e);}    
        }
}