package utils;

import cookies.*;
import orderItems.*;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.net.URLDecoder;

/**
 *
 * The Util class provides a method for converting a request string to a HashMap.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class Util {

    /**
     * Converts a request string to a HashMap, where the key-value pairs are separated by and "AND" operator.
     *
     * @param request the request string to convert
     * @return the resulting HashMap
     */
    public static HashMap<String, String> requestStringToMap(String request) {
        HashMap<String, String> map = new HashMap<String, String>();
        String[] pairs = request.split("&");
        for (int i = 0; i < pairs.length; i++) {
            String pair = pairs[i];

            try {
                String key = pair.split("=")[0];
                key = URLDecoder.decode(key, "UTF-8");

                String value = pair.split("=")[1];
                value = URLDecoder.decode(value, "UTF-8");

                map.put(key, value);
            } catch (UnsupportedEncodingException e) {
                System.err.println(e.getMessage());
            }

        }
        return map;
    }

    /**
     *
     * The headTag method returns a string containing the head tags of an HTML document, including the title, bootstrap, flowbite and tailwind css.
     * @return it in a html format
     */
    public static String headTag(){
        String headTag =
                "<title>Everything Store</title> "+
                "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                "<link href=\"https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.2/flowbite.min.css\"  rel=\"stylesheet\" />\n"+
                "<script src=\"https://cdn.tailwindcss.com\"></script>\n";
        return headTag;
    }

/**
 *
 * The forResponsiveness method returns a string containing a script tag linking to flowbite javascript for responsiveness of an HTML document.
 * @return it in a html format
 */
    public static String forResponsiveness(){
        String javaScript = "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/flowbite/1.6.2/flowbite.min.js\"></script>\n";

        return  javaScript;
    }

/**
  *
  *The cart method returns a string containing the HTML for a cart button with a badge showing the number of items in the cart.
 * @param cart the numbers of items added to the cart
 * @return the cart in a html format
  */
    public static String cart(int cart){
        String basket =
                "<li class=\"font-sans block mt-0 lg:inline-block lg:mt-0 lg:ml-6 align-top text-black hover:text-gray-700\">\n" +
                "  <a href=\"/products/cart\" role=\"button\" class=\"relative flex\">\n" +
                "    <svg class=\"flex-1 w-8 h-8 fill-current\" viewbox=\"0 0 24 24\">\n" +
                "      <path d=\"M17,18C15.89,18 15,18.89 15,20A2,2 0 0,0 17,22A2,2 0 0,0 19,20C19,18.89 18.1,18 17,18M1,2V4H3L6.6,11.59L5.24,14.04C5.09,14.32 5,14.65 5,15A2,2 0 0,0 7,17H19V15H7.42A0.25,0.25 0 0,1 7.17,14.75C7.17,14.7 7.18,14.66 7.2,14.63L8.1,13H15.55C16.3,13 16.96,12.58 17.3,11.97L20.88,5.5C20.95,5.34 21,5.17 21,5A1,1 0 0,0 20,4H5.21L4.27,2M7,18C5.89,18 5,18.89 5,20A2,2 0 0,0 7,22A2,2 0 0,0 9,20C9,18.89 8.1,18 7,18Z\"/>\n" +
                "      </svg>\n" +
                "      <span class=\"absolute right-0 top-0 rounded-full bg-red-600 w-4 h-4 top right p-0 m-0 text-white font-mono text-sm  leading-tight text-center\">"+ cart +"\n" +
                "    </span>\n" +
                "  </a>\n" +
                "</li>";

        return basket;
    }

    /**
     *
     * The navBar method returns a string containing the HTML for a navigation bar that changes its options depending on whether the user is logged in or not.
     *
     * @param userName a string representing the username used for login
     * @param password a string representing the password used for login
     * @param auth a string representing the user's current authentication status
     * @param cart an int representing the number of items in the cart
     * @return a string containing the HTML for a navigation bar
     */
    public static String navBar(String userName, String password, String auth, int cart) {


        String avatar = (auth.equals("loggedIn"))? "<span class=\"block text-sm text-gray-900 dark:text-white\">Admin</span>\n" : "<a href=\"#\" class=\"block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white\"></a>\n";
        String home = (auth.equals("loggedIn"))? "<a href=\"/login/processLoginHandler?userName="+userName+"&password="+password+"\"  class=\"block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700\">Home</a>\n" : "<a href=\"/\"  class=\"block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent dark:border-gray-700\">Home</a>\n";
        String product = (auth.equals("loggedIn"))? "<a href=\"/products\" class=\"block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white\">Products</a>\n" : "<a href=\"/login\" class=\"block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white\">Products</a>\n";
        String customer = (auth.equals("loggedIn"))? "<a href=\"/customers\" class=\"block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white\">Customers</a>\n" : "<a href=\"/login\" class=\"block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white\">Customers</a>\n";
        String admin = (auth.equals("loggedIn"))? "<a href=\"/admins\" class=\"block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white\">Admin</a>\n" : "<a href=\"/login\" class=\"block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white\">Admin</a>\n";

        String ifElse = (auth.equals("loggedIn"))? "<a href=\"/login\" class=\"block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white\">Sign Out</a>\n" : "<a href=\"/login\" class=\"block px-4 py-2 text-sm text-gray-700 hover:bg-gray-100 dark:hover:bg-gray-600 dark:text-gray-200 dark:hover:text-white\">Login</a>\n";

        String navbar = "<nav class=\"bg-white border-gray-200 px-2 sm:px-4 py-2.5 rounded dark:bg-gray-900\">\n" +
                "  <div class=\"container flex flex-wrap items-center justify-between mx-auto\">\n" +
                "  <a href=\"#\" class=\"flex items-center\">\n" +
                "      <img src=\"https://i.ibb.co/WHfNvdn/online-Store.png\" class=\"h-6 mr-3 sm:h-9\" alt=\"Everything Store\" />\n" +
                "      <span class=\"self-center text-xl font-semibold whitespace-nowrap dark:text-white\">Everything Store</span>\n" +
                "  </a>\n" +
                "  <div class=\"flex items-center md:order-2\">\n" +
                "      <button type=\"button\" class=\"flex mr-3 text-sm bg-gray-800 rounded-full md:mr-0 focus:ring-4 focus:ring-gray-300 dark:focus:ring-gray-600\" id=\"user-menu-button\" aria-expanded=\"false\" data-dropdown-toggle=\"user-dropdown\" data-dropdown-placement=\"bottom\">\n" +
                "        <span class=\"sr-only\">Open user menu</span>\n" +
                "        <img class=\"w-8 h-8 rounded-full\" src=\"/docs/images/people/profile-picture-3.jpg\" alt=\"user photo\">\n" +
                "      </button>\n" +
                "      <!-- Dropdown menu -->\n" +
                "      <div class=\"z-50 hidden my-4 text-base list-none bg-white divide-y divide-gray-100 rounded shadow dark:bg-gray-700 dark:divide-gray-600\" id=\"user-dropdown\">\n" +
                "        <div class=\"px-4 py-3\">\n" +
                            avatar +
                "        </div>\n" +
                "        <ul class=\"py-1\" aria-labelledby=\"user-menu-button\">\n" +
                "          <li>\n"
                + ifElse +
                "          </li>\n" +
                "        </ul>\n" +
                "      </div>\n" +
                "      <button data-collapse-toggle=\"mobile-menu-2\" type=\"button\" class=\"inline-flex items-center p-2 ml-1 text-sm text-gray-500 rounded-lg md:hidden hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-gray-200 dark:text-gray-400 dark:hover:bg-gray-700 dark:focus:ring-gray-600\" aria-controls=\"mobile-menu-2\" aria-expanded=\"false\">\n" +
                "        <span class=\"sr-only\">Open main menu</span>\n" +
                "        <svg class=\"w-6 h-6\" aria-hidden=\"true\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M3 5a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 15a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z\" clip-rule=\"evenodd\"></path></svg>\n" +
                "    </button>\n" +
                "  </div>\n" +
                "  <div class=\"items-center justify-between hidden w-full md:flex md:w-auto md:order-1\" id=\"mobile-menu-2\">\n" +
                "    <ul class=\"flex flex-col p-4 mt-4 border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-gray-900 dark:border-gray-700\">\n" +
                "      <li>\n" +
                        home +
                "      </li>\n" +
                "      <li>\n" +
                        product+
                "      </li>\n" +
                "      <li>\n" +
                        customer+
                "      </li>\n" +
                "      <li>\n" +
                        admin+
                "      </li>\n" +
                Util.cart(cart)+
                "    </ul>\n" +
                "  </div>\n" +
                "  </div>\n" +
                "</nav>\n";
        return navbar;
    }

    /**
     *
     * This method returns the number of items in a shopping cart.
     * @return the number of items in the cart
     */
    public static int itemsInCart() throws SQLException {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        int itemsInCart = 0;
        itemsInCart = orderItemDAO.getAllOrderItems().size();

        return itemsInCart;
    }
}
