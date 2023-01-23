package pages.customers;

import java.io.OutputStream;
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
 * This class handles the HTTP request for updating a customer's information.
 * It retrieves the customer's information from the URL's query parameters and uses the CustomerDAO to update the customer's information in the database.
 * It then sends an HTTP response with a message indicating that the customer's information has been updated, along with the updated customer's information in a table.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class ProcessUpdateCustomerHandler implements HttpHandler{

    /**
    * Handles the HTTP request for updating a customer's information.
    * Retrieves the customer's information from the URL's query parameters and uses the CustomerDAO to update the customer's information in the database.
    * Sends an HTTP response with a message indicating that the customer's information has been updated, along with the updated customer's information in a table.
    * @param  he the HttpExchange object that handles the request and response* @throws IOException if an I/O error occurs
    */
    public void handle(HttpExchange he) throws IOException {

        System.out.println("ProcessUpdateCustomer Called");
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(he.getResponseBody() ));

        // Get param from URL
        Map <String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        // print the params for debugging
        System.out.println(params);

        //get ID number


        CustomerDAO customerDAO = new CustomerDAO();

        System.out.println("about to get data");

        String firstName = params.get("firstName");
        String lastname = params.get("lastName");
        String house = params.get("house");
        String addressLine1 = params.get("addressLine1");
        String addressLine2 = params.get("addressLine2");
        String country = params.get("country");
        String postCode = params.get("postCode");
        String telephone = params.get("telephoneNumber");
        int customerID = Integer.parseInt(params.get("id"));


        System.out.println("about to update customer"); // Debugging message
        Customer customer = new Customer(customerID, firstName, lastname, new Address(house, addressLine1, addressLine2, country, postCode), telephone);
        System.out.println("customers.Customer to Update" + customer);

        String userName = Cookies.readCookies().get(0);
        String password = HashPassword.hash(Cookies.readCookies().get(1));
        String authentication = Cookies.readCookies().get(2);

        if (authentication.equals("loggedIn")) {
            try {
                int itemsInCart = Util.itemsInCart();
                customerDAO.updateCustomer(customer); // update to database


                out.write(
                        "<html>" +
                                "<head>" +
                                Util.headTag() +
                                "</head>" +
                                "<body>" +
                                Util.navBar(userName, password, authentication, itemsInCart) +
                                "<div class=\"flex justify-between\">"+
                                "   <h4 class=\"ml-6 text-2xl font-bold dark:text-white\">Customers Updated</h4>"+
                                "   <div class=\"mr-4 flex justify-end\">"+
                                "       <a href=\"/customers/add\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Add Customer</a>\n" +
                                "       <a href=\"/customers/find\" class=\"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-small rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800\">Find Customer</a>\n" +
                                "   </div>"+
                                "</div>"+
                                "<div class=\"relative overflow-x-auto shadow-md sm:rounded-lg\">\n" +
                                "    <table class=\"w-full text-sm text-left text-gray-500 dark:text-gray-400\">\n" +
                                "        <thead class=\"text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400\">\n" +
                                "            <tr>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Customer ID\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Firstname\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Last Name\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    House No.\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Address Line 1\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Address Line 2\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Country\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Postcode\n" +
                                "                </th>\n" +
                                "                <th scope=\"col\" class=\"px-6 py-3\">\n" +
                                "                    Telephone No.\n" +
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
                                    "                    " + customer.getCustomerID() + "\n" +
                                    "                </th>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    " + customer.getFirstName() + "\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    " + customer.getLastName() + "\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    " + customer.getAddress().getHouse() + "\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    " + customer.getAddress().getAddressLine1() + "\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    " + customer.getAddress().getAddressLine2() + "\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    " +customer.getAddress().getCountry() + "\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    " + customer.getAddress().getPostCode() + "\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    " + customer.getTelephone() + "\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    <a href=\"/customers/update?id=" + customer.getCustomerID() + "\" class=\"font-medium text-blue-600 dark:text-blue-500 hover:underline\">Update</a>\n" +
                                    "                </td>\n" +
                                    "                <td class=\"px-6 py-4\">\n" +
                                    "                    <a href=\"/customers/delete?id=" + customer.getCustomerID() + "\" class=\"font-medium text-blue-600 dark:text-blue-500 hover:underline\">Delete</a>\n" +
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
