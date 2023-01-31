package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/mostrarDatosUsuarios")
public class MostrarDatosUsuarios extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("txtUsuario");
        String password = req.getParameter("txtPassword");
        String[] tecnologias = req.getParameterValues("tecnologia");
        String genero = req.getParameter("genero");
        String ocupacion = req.getParameter("ocupacion");
        String[] musica = req.getParameterValues("musica");
        String comentario = req.getParameter("comentario");

        String auxTec = "";
        String auxMuc = "";

        if (tecnologias != null) {
            for (String t : tecnologias) {
                auxTec = auxTec + t.concat(" ");
            }
        }

        if (musica != null) {
            for (String m : musica) {
                auxMuc = auxMuc + m.concat(" ");
            }
        }

        Cookie[] c = req.getCookies();
        
        try (PrintWriter out = resp.getWriter();) {
            out.print("<!DOCTYPE html> <html><head>");
            out.print("<link href='recursos/estilos.css' rel='stylesheet' type='text/css'/>");
            out.print("</head> <body>");
            out.print("<h1>Resultado de registro del usuario</h1>");
            out.print("<table>");
            out.print("<tr> <th>Atributo</th> <th>Valores</th> </tr>");
            out.print("<tr> <td>Usuario:</td> <td>" + usuario + "</td> </tr>");
            out.print("<tr> <td>Password:</td> <td>" + password + "</td> </tr>");
            out.print("<tr> <td>Tecnologias:</td> <td>" + auxTec.strip() + "</td> </tr>");
            out.print("<tr> <td>Genero:</td> <td>" + (genero.equals("h") ? "Hombre" : "Mujer") + "</td> </tr>");
            out.print("<tr> <td>Ocupaccion:</td> <td>" + ocupacion + "</td> </tr>");
            out.print("<tr> <td>Musica Favorita:</td> <td>" + auxMuc.strip() + "</td> </tr>");
            out.print("<tr> <td>Comentario:</td> <td>" + comentario + "</td> </tr>");
            out.print("</table> </body> </html>");
            
            if(c != null){
                for (Cookie cookie : c) {
                    out.println(cookie.getName() + "\t|\t" + cookie.getValue());
                }
            }
        }

        
    }

}
