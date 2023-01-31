package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author jhironsel
 */
@WebServlet("/ejemploSessiones")
public class EjemploSessiones extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ambos(req, resp); 
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ambos(req, resp); 
    }

    private void ambos(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; chartset=UTF-8;");
        
        HttpSession session = req.getSession();
        String titulo = null;
        
        //Pedir el atributo contadorVisita a la session
        Integer contadorVisita = (Integer) session.getAttribute("contadorVisita");
        
        //Si el atributo contadorVisita es nulo entonces es la primera vez que viene.
        if(contadorVisita == null){
            contadorVisita = 1;
            titulo = "Bienvenido por PRIMERA VEZ.";
        }else{
            contadorVisita++;
            titulo = "Bienvenido NUEVAMENTE.";
        }
        
        session.setAttribute("contadorVisita", contadorVisita);
        
        try(PrintWriter out = resp.getWriter();){
            out.print(titulo);
            out.print("<br>");
            out.print("Numero total de vistas: "+contadorVisita);
            out.print("<br>");
            out.print("Id Session: "+session.getId());
        }
    }
    
}
