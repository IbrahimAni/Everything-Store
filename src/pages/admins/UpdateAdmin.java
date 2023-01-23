package pages.admins;

import com.sun.net.httpserver.*;
import cookies.*;
import admins.*;
import utils.*;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 * The UpdateAdmin class is responsible for handling the HTTP request for updating an admin user in the system.
 * It implements the HttpHandler interface and overrides the handle method to handle the request.
 * It uses the UserDAO class to update the user in the database and display the updated information to the user.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class UpdateAdmin implements HttpHandler {

    /**
    * The handle method is called when an HTTP request is sent to the server, it processes the request and sends an appropriate response.
    * It gets the user's id from the URL parameters and retrieves the user's details from the database.
    * It then displays a form to the user with the user's details pre-filled, allowing the user to update the information.
    * When the form is submitted, it sends a request to the /users/processUpdateUsers endpoint to update the user in the database.
    * @param he The HttpExchange object that holds the request and response information.
    * @throws IOException if an I/O error occurs
    * @see HttpExchange
    * @see UserDAO
    */
    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println("Update Handler Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map<String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);

        //get ID number
        int id = Integer.parseInt(params.get("id"));

        UserDAO userDAO = new UserDAO();

        String userName = Cookies.readCookies().get(0);
        String password = HashPassword.hash(Cookies.readCookies().get(1));
        String authentication = Cookies.readCookies().get(2);


        if (authentication.equals("loggedIn")) {
            try {
                int itemsInCart = Util.itemsInCart();
                // get the user's details before we delete from the Database
                User user = userDAO.getUser(id);

                out.write(
                        "<html>" +
                                "<head>" +
                                Util.headTag() +
                                "</head>" +
                                "<body>" +
                                Util.navBar(userName, password, authentication, itemsInCart) +
                                "<div class=\"flex justify-between\">"+
                                "   <h4 class=\"ml-6 text-2xl font-bold dark:text-white\">Update Admins</h4>"+
                                "   <div class=\"mr-4 flex justify-end\">"+
                                "       <a href=\"/admins/add\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Add Admin</a>\n" +
                                "       <a href=\"/admins/find\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Find Admin</a>\n" +
                                "   </div>"+
                                "</div>"+
                                "<form class=\"ml-6 mr-6\" method=\"get\" action=\"/admins/processUpdateAdminHandler\">\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"id\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Admin ID</label>\n" +
                                "    <input type=\"text\" id=\"id\" name=\"id\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+user.getUserId()+"\" >\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"userName\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Username</label>\n" +
                                "    <input type=\"text\" id=\"userName\" name=\"userName\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+user.getUserName()+"\">\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"password\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Password</label>\n" +
                                "    <input type=\"password\" id=\"password\" name=\"password\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" >\n" +
                                "  </div>\n" +
                                "  <button type=\"submit\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800\">Submit</button>\n" +
                                "</form>\n"+
                                Util.forResponsiveness()+
                                "</body>" +
                                "</html>");

            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
            out.close();
        }
    }
}
