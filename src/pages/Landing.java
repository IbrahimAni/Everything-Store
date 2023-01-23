package pages;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import cookies.*;
import products.*;
import utils.*;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 *
 * The Landing class is an implementation of HttpHandler that handles incoming HTTP requests and sends a response to the client.
 * It implements the handle() method of the HttpHandler interface, and sends an HTML response to the client containing a login link and a message.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class Landing implements HttpHandler{

    /**
     * Handles incoming HTTP request and sends a response to the client.
     * The response contains an HTML page with a login link and a message.
     *
     * @param he the HttpExchange object representing the incoming request
     * @throws IOException if there is an error sending the response
     */
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody() ));

        //Deleting all orders in the order table
        ProductDAO productDAO = new ProductDAO();


        out.write(
                "<html>" +
                        Util.headTag()+
                        "<body>" +
                        "\n" +
                        Util.navBar(null, null, "notLoggedIn", 0)+
//                        "<div class=\"container\">" +
//                        "<h4>Everything Store</h4>"+
//                        "<p>Login into the one-stop store</p>"+
//                        "<a href=\"/login\">pages.login.Login</a> " +
//                        "</div>" +
                        Util.forResponsiveness()+
                        "</body>" +
                        "</html>"
        );
        out.close();

    }
}