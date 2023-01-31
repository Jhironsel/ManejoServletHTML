package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/carritoServlet")
public class carritoServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ambos(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ambos(req, resp);
    }

    private void ambos(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; chartset=UTF-8");
        
        //Solicitamos la session de la peticion. 
        HttpSession session = req.getSession();
        
        //Solicitamos a la session que nos cargue el objeto List<String> 
        //almacenado en el server.
        List<String> articulosList = (List<String>) session.getAttribute("articulosList");
        
        //Si la lista está vacia, entonces creamos una lista nueva
        if(articulosList == null){
            articulosList = new ArrayList<>();
            //La siguiente linea almacena en el servidor los articulos que se
            //vallan guardando en el objecto de articulosList. 
            session.setAttribute("articulosList", articulosList);
        }
        
        //Recuperamos el articulo que viene por parametros de la peticion. 
        String articulo = req.getParameter("articulo");
        
        //Antes de agregarlo nos aseguramos que el objecto contenga valor en la
        //Variable articulo, de lo contrario no se inserta valores nulos.
        if(!articulo.isBlank() && !articulo.isEmpty()){
            //Aun la variables articulosList se encuentre en este punto, el 
            //articulo será almacenado de toda forma, ya que son objetos de 
            //referencias. 
            articulosList.add(articulo);
        }
        
        //Aqui procedemos a mostrar todo lo que tiene la lista. 
        try(PrintWriter out = resp.getWriter();){
            out.print("<h1> Listado de producto en el carrito.</h1>");
            //Si el objeto está vacio, no imprime nada. 
            //Si la variable articulo está vacio y articuloList contiene elemento
            //lo mostrará.

            for (String art : articulosList) {
                out.print("<li>"+art+"</li>");
            }
            
            out.append("<br>");
            out.println("<a href='carrito.html'>Anterior</a>");
            out.println("<a href='index.html'>Inicio</a>");
        }
    }
    
}
