package pages.login;

import java.io.OutputStream;
import java.io.OutputStreamWriter;

import admins.*;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import cookies.*;
import utils.*;

import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

/**
 *
 * The Login class is an implementation of HttpHandler that handles incoming HTTP requests for the login page.
 * It implements the handle() method of the HttpHandler interface, and sends an HTML response to the client containing a form to enter login credentials.
 * Upon receiving a form submission, it processes the login request by passing the entered credentials to the UserDAO class, which is responsible for checking the validity of the credentials.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class Login implements HttpHandler{

    /**
     * Handles incoming HTTP requests for the login page and sends a response to the client.
     * The response contains an HTML form for the user to enter login credentials.
     * Upon receiving a form submission, it processes the login request by passing the entered credentials to the UserDAO class, which is responsible for checking the validity of the credentials.
     *
     * @param he the HttpExchange object representing the incoming request
     * @throws IOException if there is an error sending the response
     */
    public void handle(HttpExchange he) throws IOException {

        System.out.println("Login Handler Called");
        he.sendResponseHeaders(200, 0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody()));

//        int itemsInCart;
//        if(Cookies.readCookies() == Cookies.readCookies()){
//            itemsInCart = Cookies.readCart();
//        }else {
//            itemsInCart = 0;
//        }
//        int itemsInCart = Util.itemsInCart();


       out.write(
                    "<html>" +
                            "<head>" +
                            Util.headTag()+
                            "</head>" +
                            "<body>" +
                            Util.navBar(null, null,"notLoggedIn", 0)+
                            "<section class=\"bg-gray-50 dark:bg-gray-900\">\n" +
                            "  <div class=\"flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0\">\n" +

                            "      <div class=\"w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700\">\n" +
                            "          <div class=\"p-6 space-y-4 md:space-y-6 sm:p-8\">\n" +
                            "              <h1 class=\"text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white\">\n" +
                            "                  Sign in to your account\n" +
                            "              </h1>\n" +
                            "              <form class=\"space-y-4 md:space-y-6\" method=\"GET\" action=\"/login/processLoginHandler\">\n" +
                            "                  <div>\n" +
                            "                      <label for=\"userName\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Username</label>\n" +
                            "                      <input type=\"text\" name=\"userName\" id=\"userName\" class=\"bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\"admin\">\n" +
                            "                  </div>\n" +
                            "                  <div>\n" +
                            "                      <label for=\"password\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Password</label>\n" +
                            "                      <input type=\"password\" name=\"password\" id=\"password\" class=\"bg-gray-50 border border-gray-300 text-gray-900 sm:text-sm rounded-lg focus:ring-primary-600 focus:border-primary-600 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\"admin\">\n" +
                            "                  </div>\n" +
                            "                  <button type=\"submit\" class=\"w-full text-white bg-blue-600 hover:bg-blue-700 focus:ring-4 focus:outline-none focus:ring-primary-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-primary-800\">Sign in</button>\n" +
                            "                  <p class=\"text-sm font-light text-gray-500 dark:text-gray-400\">\n" +
//                            "                      Don’t have an account yet? <a href=\"#\" class=\"font-medium text-primary-600 hover:underline dark:text-primary-500\">Sign up</a>\n" +
                            "                  </p>\n" +
                            "              </form>\n" +
                            "          </div>\n" +
                            "      </div>\n" +
                            "  </div>\n" +
                            "</section>"+
                            Util.forResponsiveness()+
                            "</body>" +
                            "</html>");

        out.close();
    }
}
