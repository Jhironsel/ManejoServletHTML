package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ValidarUsuario")
public class ValidarUsuario extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ambos(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ambos(req, resp);
    }

    private void ambos(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;chartset=UTF8");
        String usuario = req.getParameter("txtUsuario");
        String clave = req.getParameter("txtPassword");
        
        boolean nuevoUsuario = true;
        
        Cookie[] cokies = req.getCookies();
        
        if(cokies != null){
            for (Cookie coky : cokies) {
                if(coky.getName().equals("visitanteRecurrente") && coky.getValue().equals("si")){
                    nuevoUsuario = false;
                    break;
                }
            }
        }
        
        if(nuevoUsuario){
            Cookie c = new Cookie("visitanteRecurrente", "si");
            resp.addCookie(c);
        }
        
        try (PrintWriter out = resp.getWriter()) {
            if(usuario.isBlank() || usuario.isEmpty()){
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Nombre de usuario en blanco");
                return;
            }
            
            if(usuario.length() <= 4){
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Nombre de usuario demasiado corto");
                return;
            }
            
            if(clave.isBlank() || clave.isEmpty() || clave.length() < 8){
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Contraseña incorrecta!");
                return;
            }
            
            
            out.println("Los datos son correctos.");
            out.println("<br/>");
            out.println("<a href=\"index.html\">Inicio</a>");
            out.println("<br/>");
            out.println("<br/>");
            
            if(nuevoUsuario){
                out.println("Bienvenido...");
            }else{
                out.print("Saludos nuevamente.");
            }
        }
    }
}
