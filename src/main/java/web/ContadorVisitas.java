package web;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author jhironsel
 */
@WebServlet("/contadorVisitas")
public class ContadorVisitas extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ambos(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ambos(req, resp);
    }

    private void ambos(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Crear una variable contador
        var contador = 0;

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cooky : cookies) {
                if (cooky.getName().equals("contadorVisita")) {
                    contador = Integer.valueOf(cooky.getValue());
                }
            }
        }

        contador++;

        Cookie c = new Cookie("contadorVisita", String.valueOf(contador));

        resp.addCookie(c);

        resp.setContentType("text/html; chartset=UTF-8");

        try (PrintWriter out = resp.getWriter();) {
            out.print("Cantidad de visita: " + contador);
        }

        //sendEmail("jhironsel@gmail.com", "Una prueba", "Esta prueba está cabrona.");
    }

    private void sendEmail(String to, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Obtén las credenciales de la cuenta de correo desde las variables de entorno
        String username = "@gmail.com";
        String password = "";

        // Crea una sesión autenticada
        Session session = Session.getInstance(props,
                new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Crea el mensaje
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        // Envía el mensaje
        Transport.send(message);
    }

}
