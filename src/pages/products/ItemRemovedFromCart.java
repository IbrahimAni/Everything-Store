package pages.products;

import com.sun.net.httpserver.*;
import cookies.*;
import orderItems.*;
import products.*;
import utils.*;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 *
 * The ItemRemovedFromCart class is an implementation of the HttpHandler interface. It deletes an item from the cart in the database and displays them in an HTML table.
 * The class also provides links for updating and deleting products.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class ItemRemovedFromCart implements HttpHandler {
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
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map<String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());
        int id = Integer.parseInt(params.get("id"));

        OrderItemDAO orderItemDAO = new OrderItemDAO();
        String userName = Cookies.readCookies().get(0);
        String password = HashPassword.hash(Cookies.readCookies().get(1));
        String authentication = Cookies.readCookies().get(2);

        if (authentication.equals("loggedIn")) {
            try {
                int itemsInCart = Util.itemsInCart();
                orderItemDAO.deleteOrderItem(id);
        out.write(
                "<html>" +
                        "<head>" +
                        Util.headTag()+
                        "</head>" +
                        "<body>" +
                        Util.navBar(userName, password,authentication, itemsInCart)+
                        "<div class=\"flex justify-between\">"+
                        "   <h4 class=\"ml-6 text-2xl font-bold dark:text-white\">Cart</h4>"+
                        "   <div class=\"mr-4 flex justify-end\">"+
                        "       <a href=\"/products/add\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Add Product</a>\n" +
                        "       <a href=\"/products/find\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Find Product</a>\n" +
                        "   </div>"+
                        "</div>"+
                        "<section class=\"bg-gray-50 dark:bg-gray-900\">\n" +
                        "  <div class=\"flex flex-col items-center justify-center px-6 py-8 mx-auto md:h-screen lg:py-0\">\n" +

                        "      <div class=\"w-full bg-white rounded-lg shadow dark:border md:mt-0 sm:max-w-md xl:p-0 dark:bg-gray-800 dark:border-gray-700\">\n" +
                        "          <div class=\"p-6 space-y-4 md:space-y-6 sm:p-8\">\n" +
                        "              <h1 class=\"text-xl font-bold leading-tight tracking-tight text-gray-900 md:text-2xl dark:text-white\">\n" +
                        "                  Product Removed from cart    \n" +
                        "              </h1>\n" +

                        "          </div>\n" +
                        "      </div>\n" +
                        "  </div>\n" +
                        "</section>"+
                        Util.forResponsiveness()+
                        "<script type=\"text/javascript\">\n" +
                        "setTimeout(function(){\n" +
                        "    window.location.href = \"/products/cart\"\n" +
                        "}, 1000);\n" +
                        "</script>"+
                        "</body>" +
                        "</html>");

            } catch (SQLException se) {
                System.out.println(se.getMessage());
            }
            out.close();
        }
    }
}
