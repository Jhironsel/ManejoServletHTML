package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/servicio")
public class Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("txtUsuario");
        String password = req.getParameter("txtPassword");
        String[] tecnologias = req.getParameterValues("tecnologia");
        String genero = req.getParameter("genero");
        String ocupacion = req.getParameter("ocupacion");
        String[] musica = req.getParameterValues("musica");
        String comentario = req.getParameter("comentario");

        PrintWriter out = resp.getWriter();
        
        String auxTec="";
        String auxMuc="";
        
        for (String m : musica) {
            auxMuc = auxMuc + m.concat(" ");
        }
        
        for (String t : tecnologias) {
            auxTec = auxTec + t.concat(" ");
        }

        out.print("<!DOCTYPE html> <html><head><style>");
        out.print("table {\n"
                + "  font-family: arial, sans-serif;\n"
                + "  border-collapse: collapse;\n"
                + "  width: 100%;\n"
                + "}"
                + "td, th {\n"
                + "  border: 1px solid #dddddd;\n"
                + "  text-align: left;\n"
                + "  padding: 8px;\n"
                + "}\n"
                + "\n"
                + "tr:nth-child(even) {\n"
                + "  background-color: #dddddd;\n"
                + "}");
        out.print("</style> </head> <body>");
        out.print("<h1>Resultado de registro del usuario</h1>");
        out.print("<table>");
        out.print("<tr> <th>Atributo</th> <th>Valores</th> </tr>");
        out.print("<tr> <td>Usuario:</td> <td>"+usuario+"</td> </tr>");
        out.print("<tr> <td>Password:</td> <td>"+password+"</td> </tr>");
        out.print("<tr> <td>Tecnologias:</td> <td>"+auxTec.strip()+"</td> </tr>");
        out.print("<tr> <td>Genero:</td> <td>"+(genero.equals("h") ? "Hombre" : "Mujer")+"</td> </tr>");
        out.print("<tr> <td>Ocupaccion:</td> <td>"+ocupacion+"</td> </tr>");
        out.print("<tr> <td>Musica Favorita:</td> <td>"+auxMuc.strip()+"</td> </tr>");
        out.print("<tr> <td>Comentario:</td> <td>"+comentario+"</td> </tr>");
        
        out.print("</table> </body> </html>");
        
    }

}
