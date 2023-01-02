package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/generarExcel")
public class GenerarExcelServlet extends HttpServlet{

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
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition", "attachment;filename=excelEjemplo.xls");
        
        //poi.apache.org
        
        //Indicamos al navegador que no guarde cache
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-store");
        resp.setDateHeader("Expires", -1);
        
        //Desplegamos la informacion al cliente.
        PrintWriter out = resp.getWriter();
        out.println("\tValores");
        out.println("\t20");
        out.println("\t45");
        out.println("Total\t=SUMA(B2:B3)");
        out.close();
    }
    
}
