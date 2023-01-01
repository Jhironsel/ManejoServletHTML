package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/cabeceras")
public class Cabeceras extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resuelve(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resuelve(req, resp);
    }

    private void resuelve(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Enumeration<String> headerNames = req.getHeaderNames();
        
        try(PrintWriter out = resp.getWriter();){
            out.println("Metodo utilizado es: "+req.getMethod());
            out.println("\nLa URI utilizada es:"+req.getRequestURI());
            out.println("\nLa URI id Session:"+req.getRequestedSessionId());
            out.println("\nLa URL:"+req.getRequestURL());
            
            while(headerNames.hasMoreElements()){
                String element = headerNames.nextElement();
                out.println("=> "+ element +"   |   "+req.getHeader(element));                
            }
            
        }
        
    }
    
    
}
