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
import java.util.Map;

/**
 *
 * The UpdateProduct class is a HttpHandler that handle the http request for update a product.
 * It will get the product details from the database using the product id, and then display the form
 * for user to update the product information.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class UpdateProduct implements HttpHandler{

    /**
     * handle method is implemented from the HttpHandler interface and handle the http request for update a product.
     * It will get the product details from the database using the product id, and then display the form
     * for user to update the product information.
     *
     * @param he The HttpExchange object which contains information about the request and response of the http request
     * @throws IOException in case of any IO errors
     */
    public void handle(HttpExchange he) throws IOException {

        System.out.println("Update Handler Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map <String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);

        //get ID number
        int id = Integer.parseInt(params.get("id"));

        ProductDAO productDAO = new ProductDAO();
        String userName = Cookies.readCookies().get(0);
        String password = HashPassword.hash(Cookies.readCookies().get(1));
        String authentication = Cookies.readCookies().get(2);

        if (authentication.equals("loggedIn")) {
            try {
                int itemsInCart = Util.itemsInCart();
                // get the product details before we delete from the Database
                Product product = productDAO.getProduct(id);

                out.write(
                        "<html>" +
                                "<head> " +
                                Util.headTag()+
                                "</head>" +
                                "<body>" +
                                Util.navBar(userName, password, authentication,itemsInCart)+
                                "<div class=\"flex justify-between\">"+
                                "   <h4 class=\" ml-6 text-2xl font-bold dark:text-white\">Update Product</h4>"+
                                "   <div class=\"flex justify-end mr-4\">"+
                                "       <a href=\"/products/add\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Add Product</a>\n" +
                                "       <a href=\"/products/find\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Find Product</a>\n" +
                                "   </div>"+
                                "</div>"+
                                "<form class=\"ml-6 mr-6\" method=\"get\" action=\"/products/processUpdateProductHandler\">\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"id\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Product ID</label>\n" +
                                "    <input type=\"text\" id=\"id\" name=\"id\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+product.getId()+"\" >\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"sku\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Product SKU</label>\n" +
                                "    <input type=\"text\" id=\"sku\" name=\"sku\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+product.getSku()+"\">\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"description\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Description</label>\n" +
                                "    <input type=\"text\" id=\"description\" name=\"description\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+product.getDescription()+"\">\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"category\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Category</label>\n" +
                                "    <input type=\"text\" id=\"category\" name=\"category\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+product.getCategory()+"\">\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"price\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Price</label>\n" +
                                "    <input type=\"text\" id=\"price\" name=\"price\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+product.getPrice()+"\">\n" +
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
