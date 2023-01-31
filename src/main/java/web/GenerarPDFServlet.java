package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/generarPDF")
public class GenerarPDFServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ambos(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ambos(req, resp);
    }

    private void ambos(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Indicamos el tipo de respuesta al navegador.
        resp.setContentType("application/pdf");
        resp.setHeader("Content-Disposition", "attachment;filename=pdfEjemplo.pdf");

        //poi.apache.org
        //Indicamos al navegador que no guarde cache
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-store");
        resp.setDateHeader("Expires", -1);

         //Desplegamos la informacion al cliente.
         try (PrintWriter out = resp.getWriter()) {
            out.println("Valores");
            out.println("20");
            out.println("45");
            out.println("Total:");
        }
    }

}
