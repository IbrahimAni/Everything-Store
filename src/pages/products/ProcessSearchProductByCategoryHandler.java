package pages.products;

import com.sun.net.httpserver.*;
import cookies.*;
import products.*;
import utils.*;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 * The ProcessSearchProductByCategoryHandler class is a HttpHandler that is responsible for processing a request to add a product to the database.
 * It implements the handle method that takes an HttpExchange object as a parameter and handles the request by adding a product to the database and then returning a response.
 * It starts by sending a response header with a 200 status code to indicate that the request was successful.
 * It then gets the parameters passed in the request URL using the Util.requestStringToMap() method and maps them to variables.
 * It creates a new Product object with the values of the parameters and adds it to the database using the ProductDAO.addProduct() method.
 * Finally, it generates an HTML response that shows the product that was added, and a link to go back to the menu.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class ProcessSearchProductByCategoryHandler implements HttpHandler {
    /**
     * Handle the given request and generate an appropriate response.
     * See {@link HttpExchange} for a description of the steps
     * involved in handling an exchange.
     *
     * @param he the exchange containing the request from the
     *                 client and used to send the response
     * @throws NullPointerException if exchange is {@code null}
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void handle(HttpExchange he) throws IOException {
        System.out.println("ProcessFindProduct Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map<String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);

        //get ID number
        ProductDAO productDAO = new ProductDAO();

        System.out.println("about to get data");

        String search = params.get("search");

        System.out.println("about to find product by category"); // Debugging message
        String userName = Cookies.readCookies().get(0);
        String password = HashPassword.hash(Cookies.readCookies().get(1));
        String authentication = Cookies.readCookies().get(2);
//        String searchIgnoreCases = search.;
        if (authentication.equals("loggedIn")) {
            try {
                int itemsInCart = Util.itemsInCart();
                ArrayList<Product> allProducts = productDAO.getProductByCategory(search); // get data


                out.write(
                        "<html>" +
                                "<head>" +
                                Util.headTag() +
                                "</head>" +
                                "<body>" +
                                Util.navBar(userName, password, authentication, itemsInCart) +
                                "<div class=\"flex justify-between\">"+
                                "   <h4 class=\" ml-6 text-2xl font-bold dark:text-white\">Product Result</h4>"+
                                "   <div class=\"flex justify-end mr-4\">"+
                                "       <a href=\"/products/add\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Add Product</a>\n" +
                                "       <a href=\"/products/find\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Find Product</a>\n" +
                                "   </div>"+
                                "</div>"+
                                "<div class=\"relative overflow-x-auto shadow-md sm:rounded-lg\">\n" +
                                "    <table class=\"w-full text-sm text-left text-gray-500 dark:text-gray-400\">\n" +
                                "        <thead class=\"text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400\">\n" +
                                "            <tr>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Product ID\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Product SKU\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Product Name\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Category\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Price\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Action\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Action\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Add to Cart\n" +
                                "                </th>\n" +
                                "            </tr>\n" +
                                "        </thead>\n" +
                                "        <tbody>\n");
                for(Product product : allProducts) {
                    out.write(
                            "            <tr class=\"bg-white border-b dark:bg-gray-900 dark:border-gray-700\">\n" +
                                    "                <th scope=\"row\" class=\"px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white\">\n" +
                                    "                    " + product.getId() + "\n" +
                                    "                </th>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    " + product.getSku() + "\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    " + product.getDescription() + "\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    " + product.getCategory() + "\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                   &#163 " + product.getPrice() + "\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    <a href=\"/products/update?id=" + product.getId() + "\" class=\"font-medium text-blue-600 dark:text-blue-500 hover:underline\">Update</a>\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    <a href=\"/products/delete?id=" + product.getId() + "\" class=\"font-medium text-blue-600 dark:text-blue-500 hover:underline\">Delete</a>\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    <a href=\"/products/add-to-cart?id=" + product.getId() + "\" class=\"font-medium text-blue-600 dark:text-blue-500 hover:underline\">Add</a>\n" +
                                    "                </td>\n" +
                                    "            </tr>\n"
                    );
                }
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
