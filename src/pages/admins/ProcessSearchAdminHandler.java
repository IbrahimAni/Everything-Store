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
 * This class is used to handle the process of searching for an admin user in the system.
 * It implements the HttpHandler interface and overrides the handle method to handle HTTP requests and responses.
 * It uses the UserDAO class to retrieve the user information from the database.
 * The user information is then displayed in an HTML table for the user to see.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class ProcessSearchAdminHandler implements HttpHandler {

    /**
     *
     * @param he the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException if a system error occurs
     */
    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println("ProcessFindCustomer Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map<String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);

        //get ID number
        UserDAO userDAO = new UserDAO();

        System.out.println("about to get data");

        int search = Integer.parseInt(params.get("search"));

        System.out.println("about to find user"); // Debugging message
        ArrayList<String> cookies = Cookies.readCookies();
        String userName = Cookies.readCookies().get(0);
        String password = HashPassword.hash(Cookies.readCookies().get(1));
        String authentication = Cookies.readCookies().get(2);


        if (authentication.equals("loggedIn")) {
            try {
                User user = userDAO.getUser(search); // get data
                int itemsInCart = Util.itemsInCart();

                out.write(
                        "<html>" +
                                "<head>" +
                                Util.headTag() +
                                "</head>" +
                                "<body>" +
                                Util.navBar(userName, password, authentication, itemsInCart) +
                                "<div class=\"flex justify-between\">" +
                                "   <h4 class=\"ml-6 text-2xl font-bold dark:text-white\">Admins Result</h4>" +
                                "   <div class=\"mr-4 flex justify-end\">" +
                                "       <a href=\"/admins/add\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Add Admin</a>\n" +
                                "       <a href=\"/admins/find\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Find Admin</a>\n" +
                                "   </div>" +
                                "</div>" +
                                "<div class=\"relative overflow-x-auto shadow-md sm:rounded-lg\">\n" +
                                "    <table class=\"w-full text-sm text-left text-gray-500 dark:text-gray-400\">\n" +
                                "        <thead class=\"text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400\">\n" +
                                "            <tr>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Admin ID\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Username\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Password\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Action\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Action\n" +
                                "                </th>\n" +
                                "            </tr>\n" +
                                "        </thead>\n" +
                                "        <tbody>\n");
                out.write(
                        "            <tr class=\"bg-white border-b dark:bg-gray-900 dark:border-gray-700\">\n" +
                                "                <th scope=\"row\" class=\"px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white\">\n" +
                                "                    " + user.getUserId() + "\n" +
                                "                </th>\n" +
                                "                <td class=\"px-6 py-4\">\n" +
                                "                    " + user.getUserName() + "\n" +
                                "                </td>\n" +
                                "                <td class=\"px-6 py-4\">\n" +
                                "                    " + HashPassword.hash(user.getPassword() )+ "\n" +
                                "                </td>\n" +
                                "                <td class=\"px-6 py-4\">\n" +
                                "                    <a href=\"/admins/update?id=" + user.getUserId() + "\" class=\"font-medium text-blue-600 dark:text-blue-500 hover:underline\">Update</a>\n" +
                                "                </td>\n" +
                                "                <td class=\"px-6 py-4\">\n" +
                                "                    <a href=\"/admins/delete?id=" + user.getUserId() + "\" class=\"font-medium text-blue-600 dark:text-blue-500 hover:underline\">Delete</a>\n" +
                                "                </td>\n" +
                                "            </tr>\n"
                );
                out.write(
                        " </tbody>\n" +
                                "    </table>\n" +
                                "</div>\n" +
                                Util.forResponsiveness() +
                                "</body>" +
                                "</html>");
            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }

            out.close();
        }
    }
}
