<%-- 
    Document   : primerJSPScribles
    Created on : 8 ene. 2023, 2:21:31 p. m.
    Author     : jhironsel
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <ol>
            <li> <% out.print("Scriplets"); %> </li>
            <li> ${"Expression Language ELl"} </li>
            <li> <%= "Expressiones "%> </li>
            <li> <c:out value="Usando (JSTL)" default="Mejor Opcion"/> </li>
        </ol>
    </body>
</html>