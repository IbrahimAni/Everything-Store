package pages.products;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import cookies.*;
import products.*;
import utils.*;

import java.sql.*;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * Handles the search for a product by ID number.
 * This class is responsible for displaying the search form for the product ID,
 * it then processes the request and retrieves the product from the database and displays the details
 * it also provides a link to go back to the menu.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class SearchProduct implements HttpHandler{

    /**
    *Handles the request and response for searching a product by ID.
    * It reads the ID entered by the user and retrieves the product details from the database.
    * Then it displays the details of the product and provides a link to go back to the menu.
    * @param he HttpExchange object representing the current request and response
    * @throws IOException if an I/O error occurs while writing to the output stream
    */
    public void handle(HttpExchange he) throws IOException {

        System.out.println("Search Handler Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        String userName = Cookies.readCookies().get(0);
        String password = HashPassword.hash(Cookies.readCookies().get(1));
        String authentication = Cookies.readCookies().get(2);

        if (authentication.equals("loggedIn")) {
            try{
                int itemsInCart = Util.itemsInCart();
                out.write(
                    "<html>" +
                            "<head>" +
                            Util.headTag()+
                            "</head>" +
                            "<body>" +
                            Util.navBar(userName, password, authentication, itemsInCart)+
                            "<div class=\"flex justify-between\">"+
                            "   <h4 class=\" ml-6 text-2xl font-bold dark:text-white\">Search Product</h4>"+
                            "   <div class=\"flex justify-end mr-4\">"+
                            "       <a href=\"/products/add\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Add Product</a>\n" +
                            "       <a href=\"/products/find/category\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Find Product by Category</a>\n" +
                            "   </div>"+
                            "</div>"+
                            ///
                            "<form class=\"ml-6 mr-6\" method=\"get\" action=\"/products/processSearchProductHandler\">\n" +
                            "  <div class=\"mb-6\">\n" +
                            "    <label for=\"search\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Product ID</label>\n" +
                            "    <input type=\"text\" id=\"search\" name=\"search\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" >\n" +
                            "  </div>\n" +
                            "  <button type=\"submit\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm w-full sm:w-auto px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800\">Submit</button>\n" +
                            "</form>\n"+
                            ///
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
