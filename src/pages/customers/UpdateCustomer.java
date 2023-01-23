package pages.customers;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import cookies.*;
import customers.*;
import utils.*;

import java.util.ArrayList;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Map;

/**
 *
 * The {@code UpdateCustomer} class is a {@code HttpHandler} that handles the HTTP request for updating a customer's details.
 * The class implements the {@code handle} method which is called when the server receives a request to update a customer's details.
 * <p>
 * The {@code handle} method retrieves the customer's details from the database, and displays them in a form for the user to update.
 * Once the user submits the form, the details are sent to the {@code ProcessUpdateCustomerHandler} class for processing and updating the customer's details in the database.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class UpdateCustomer implements HttpHandler{

    /**
     *
     * @param he the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException if an I/O error occurs
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

        CustomerDAO customerDAO = new CustomerDAO();
        String userName = Cookies.readCookies().get(0);
        String password = HashPassword.hash(Cookies.readCookies().get(1));
        String authentication = Cookies.readCookies().get(2);

        if (authentication.equals("loggedIn")) {
            try {
                int itemsInCart = Util.itemsInCart();
                // get the customer's details before we delete from the Database
                Customer customer = customerDAO.getCustomer(id);

                out.write(
                        "<html>" +
                                "<head>" +
                                Util.headTag() +
                                "</head>" +
                                "<body>" +
                                Util.navBar(userName, password, authentication, itemsInCart) +
                                "<div class=\"flex justify-between\">" +
                                "   <h4 class=\"ml-6 text-2xl font-bold dark:text-white\">Update Customer</h4>" +
                                "   <div class=\"mr-4 flex justify-end\">" +
                                "       <a href=\"/customers/add\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Add Customer</a>\n" +
                                "       <a href=\"/customers/find\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Find Customer</a>\n" +
                                "   </div>" +
                                "</div>" +
                                "<form class=\"ml-6 mr-6\" method=\"get\" action=\"/customers/processUpdateCustomerHandler\">\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"id\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Customer ID</label>\n" +
                                "    <input type=\"text\" id=\"id\" name=\"id\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+customer.getCustomerID()+"\" >\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"firstName\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Firstname</label>\n" +
                                "    <input type=\"text\" id=\"firstName\" name=\"firstName\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+customer.getFirstName()+"\">\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"lastName\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Last Name</label>\n" +
                                "    <input type=\"text\" id=\"lastName\" name=\"lastName\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+customer.getLastName()+"\">\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"house\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">House Number</label>\n" +
                                "    <input type=\"text\" id=\"house\" name=\"house\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+customer.getAddress().getHouse() +"\">\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"addressLine1\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Address Line 1</label>\n" +
                                "    <input type=\"text\" id=\"addressLine1\" name=\"addressLine1\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+customer.getAddress().getAddressLine1()+"\">\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"addressLine2\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Address Line 2</label>\n" +
                                "    <input type=\"text\" id=\"addressLine2\" name=\"addressLine2\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+customer.getAddress().getAddressLine2()+"\">\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"country\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Country</label>\n" +
                                "    <input type=\"text\" id=\"country\" name=\"country\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+customer.getAddress().getCountry()+"\">\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"postCode\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Postcode</label>\n" +
                                "    <input type=\"text\" id=\"postCode\" name=\"postCode\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+customer.getAddress().getPostCode()+"\">\n" +
                                "  </div>\n" +
                                "  <div class=\"mb-6\">\n" +
                                "    <label for=\"telephoneNumber\" class=\"block mb-2 text-sm font-medium text-gray-900 dark:text-white\">Telephone No.</label>\n" +
                                "    <input type=\"text\" id=\"telephoneNumber\" name=\"telephoneNumber\" class=\"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500\" value=\""+customer.getTelephone()+"\">\n" +
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
