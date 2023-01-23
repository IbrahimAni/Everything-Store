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
 * This class is used to handle the HTTP requests for adding a new product to the store. It implements the HttpHandler interface
 * and overrides the handle method to handle the HTTP request and response. The class uses the ProductDAO class to interact with the
 * database and add a new product. It also uses the Cookies class to read the cookies stored in the browser.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class AddProduct implements HttpHandler{

    /**
     * This method is used to handle the HTTP request for adding a new product to the store. It takes in the HttpExchange object
     * which contains the request and response details. It uses the ProductDAO class to interact with the database and add a new product.
     * It also uses the Cookies class to read the cookies stored in the browser.
     *
     * @param he the HttpExchange object which contains the request and response details.
     * @throws IOException if an input or output exception occurred.
     *
     * @see HttpExchange
     * @see ProductDAO
     * @see Cookies
     */
    public void handle(HttpExchange he) throws IOException {

        System.out.println("Add Handler Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

//        int itemsInCart;
//        if(Cookies.readCookies() == Cookies.readCookies()){
//            itemsInCart = Cookies.readCart();
//        }else {
//            itemsInCart = 0;
//        }
        String userName = Cookies.readCookies().get(0);
        String password = HashPassword.hash(Cookies.readCookies().get(1));
        String authentication = Cookies.readCookies().get(2);

        if (authentication.equals("loggedIn")) {
            try{
                int itemsInCart = Util.itemsInCart();
            out.write(
                    "<html>" +
                            "<head>" +
                            Util.headTag() +
                            "</head>" +
                            "<body>" +
                            Util.navBar(userName, password, authentication, itemsInCart) +
                            "<div class=\"flex justify-between\">"+
                            "   <h4 class=\" ml-6 text-2xl font-bold dark:text-white\">Add Product</h4>"+
                            "   <div class=\"flex justify-end mr-4\">"+
                            "       <a href=\"/products/add\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Add Product</a>\n" +
                            "       <a href=\"/products/find\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Find Product</a>\n" +
                            "   </div>"+
                            "</div>"+
                            "<form class=\"ml-6 mr-6\" method=\"get\" action=\"/products/processAddProductHandler\">\n" +
                            "  <div class=\"mb-6\">\n" +
                            "    <label for=\"sku\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Product SKU</label>\n" +
                            "    <input type=\"text\" id=\"sku\" name=\"sku\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" >\n" +
                            "  </div>\n" +
                            "  <div class=\"mb-6\">\n" +
                            "    <label for=\"description\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Description</label>\n" +
                            "    <input type=\"text\" id=\"description\" name=\"description\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" >\n" +
                            "  </div>\n" +
                            "  <div class=\"mb-6\">\n" +
                            "    <label for=\"category\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Category</label>\n" +
                            "    <input type=\"text\" id=\"category\" name=\"category\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" >\n" +
                            "  </div>\n" +
                            "  <div class=\"mb-6\">\n" +
                            "    <label for=\"price\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Price</label>\n" +
                            "    <input type=\"text\" id=\"price\" name=\"price\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" >\n" +
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
