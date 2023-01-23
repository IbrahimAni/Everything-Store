package pages.admins;

import com.sun.net.httpserver.*;
import cookies.*;
import orderItems.*;
import utils.*;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 * This class implements the HttpHandler interface, and handles the HTTP requests and responses for the add admin page.
 * It handles the GET requests sent to the "/users/processAddUsers" URI, which is used to add a new admin to the system.
 * It creates an HTML form with input fields for the new admin's username and password, and a submit button to send the form data to the server.
 * The form data is then processed by the "/users/processAddUsers" URI to add the new admin to the system.
 * Additionally, it also provides a link to redirect the user back to the menu page.
 */
public class AddAdmin implements HttpHandler {

    /**
     * The handle method is overridden from the HttpHandler interface, and handles the HTTP requests and responses for the add admin page.
     * @param he This is the HttpExchange object, which holds the request and response objects for the current request.
     * @throws IOException if a system error occurs
     */
    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println("Add Handler Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        String userName = Cookies.readCookies().get(0);
        String password = HashPassword.hash(Cookies.readCookies().get(1));
        String authentication = Cookies.readCookies().get(2);

        if (authentication.equals("loggedIn")) {
            try {
                int itemsInCart = Util.itemsInCart();
            out.write(
                    "<html>" +
                            "<head>" +
                            Util.headTag() +
                            "</head>" +
                            "<body>" +
                            Util.navBar(userName, password, authentication, itemsInCart) +
                            "<div class=\"flex justify-between\">" +
                            "   <h4 class=\"ml-6 text-2xl font-bold dark:text-white\">Add Admin</h4>" +
                            "   <div class=\"mr-4 flex justify-end\">" +
                            "       <a href=\"/admins/add\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Add Admin</a>\n" +
                            "       <a href=\"/admins/find\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Find Admin</a>\n" +
                            "   </div>" +
                            "</div>" +
                            "<form class=\"ml-6 mr-6\" method=\"get\" action=\"/admins/processAddAdminHandler\">\n" +
                            "  <div class=\"mb-6\">\n" +
                            "    <label for=\"userName\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Username</label>\n" +
                            "    <input type=\"text\" id=\"userName\" name=\"userName\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" >\n" +
                            "  </div>\n" +
                            "  <div class=\"mb-6\">\n" +
                            "    <label for=\"password\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Password</label>\n" +
                            "    <input type=\"password\" id=\"password\" name=\"password\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" >\n" +
                            "  </div>\n" +
                            "  <button type=\"submit\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800\">Submit</button>\n" +
                            "</form>\n" +
                            Util.forResponsiveness() +
                            "</body>" +
                            "</html>");

            }catch (SQLException se){
                System.out.println(se.getMessage());
            }
            out.close();
        }
    }
}
