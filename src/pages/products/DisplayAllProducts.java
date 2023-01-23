package pages.products;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import cookies.*;
import products.*;
import utils.*;

import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * The DisplayAllProducts class is an implementation of the HttpHandler interface. It retrieves all products from the database and displays them in an HTML table.
 * The class also provides links for updating and deleting products.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class DisplayAllProducts implements HttpHandler{

    /**
    * handle method is responsible for handling the HTTP request, sending response headers, and writing the HTML content to the response body.
    * This method retrieves all products from the database using the getAllProducts() method of the ProductDAO class, and then it displays them in an HTML table.
    * It also provides links for updating and deleting products.
    * @param he HttpExchange object representing the current HTTP request and response.
    * @throws IOException if an I/O error occurs.
    */
    public void handle(HttpExchange he) throws IOException {

        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        ProductDAO productDAO = new ProductDAO();
        String userName = Cookies.readCookies().get(0);
        String password = HashPassword.hash(Cookies.readCookies().get(1));
        String authentication = Cookies.readCookies().get(2);

        if (authentication.equals("loggedIn")) {
            try {
                int itemsInCart = Util.itemsInCart();
                ArrayList<Product> allProducts = productDAO.getAllProducts();

                out.write(
                        "<html>" +
                                "<head>" +
                                Util.headTag() +
                                "</head>" +
                                "<body>" +
                                Util.navBar(userName, password, authentication, itemsInCart) +
                                "<div class=\"flex justify-between\">"+
                                "   <h4 class=\"ml-6 text-2xl font-bold dark:text-white\">Products</h4>"+
                                "   <div class=\"mr-4 flex justify-end\">"+
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
                for (Product product : allProducts) {
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