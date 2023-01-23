package pages.login;

import java.io.OutputStreamWriter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import cookies.*;
import admins.*;
import utils.*;

import java.util.*;
import java.sql.SQLException;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * The ProcessLoginHandler class is responsible for handling user login requests.
 * It takes the user's username and password from the GET request parameters,
 * then attempts to login the user by validating the credentials with the UserDAO class.
 *  If the login is successful, it sets a cookie and redirects the user to the main page with links to various pages.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class ProcessLoginHandler implements HttpHandler{

    /**
    * This method handles the login request, it reads the username and password from the GET request parameters,
    * and then validates the credentials with the UserDAO class. If the login is successful, it sets a cookie and
    * redirects the user to the main page with links to various pages.
    * @param he, it takes HttpExchange object to handle the request and response
    * @throws IOException if an I/O error occurs
    */
    public void handle(HttpExchange he) throws IOException {
        he.sendResponseHeaders(200,0);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(he.getResponseBody() ));

        UserDAO userDAO = new UserDAO();

        // Get param from URL
        Map<String,String> params = Util.requestStringToMap
                (he.getRequestURI().getQuery());

        String userName = params.get("userName");
        String password = params.get("password");

//        int itemsInCart;
//        if(Cookies.readCookies() == Cookies.readCookies()){
//            itemsInCart = Cookies.readCart();
//        }else {
//            itemsInCart = 0;
//        }
        Cookies.cookies(userName, password, "loggedIn");
        String authentication = Cookies.readCookies().get(2);


        if ("loggedIn".equals(authentication)) {
            try {
                int itemsInCart = Util.itemsInCart();
                userDAO.login(userName, password);
                    out.write(

                            "<html>" +
                                    "<head> " +
                                    Util.headTag()+
                                    "</head>" +
                                    "<body>" +
                                    Util.navBar(userName, password, authentication, itemsInCart)+
                                    "\n" +
                                    "<h2 class=\"mb-2 ml-24 text-lg font-semibold text-gray-900 dark:text-white\">Welcome to Everything Store. 2022 - OOP Assessment </h2>\n" +
                                    "<h4 class=\"mb-2 ml-24 text-lg font-semibold text-gray-900 dark:text-white\">This project is sample data to help with the testing.</h4>\n"+
                                    "\n" +
                                    "<h4 class=\"mb-2 ml-24 text-lg font-semibold text-gray-900 dark:text-white\">Functionalities implemented in the project</h4>\n" +
                                    "<ul class=\"max-w-md ml-24 space-y-1 text-gray-500 list-inside dark:text-gray-400\">\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Product Class fully implemented and tested\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Customer Class fully implemented and tested including the address class\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Menu option to list all customers in the system\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Menu option to add a customer to the system\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Menu option to find a customer by ID\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Menu option to update a customer\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Menu option to delete a customer from the system\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Menu option to list all products in the system\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Menu option to add a product to the system\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Menu option to find a product by ID\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Menu option to update a product by ID\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Menu option to delete a product by ID\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Web page to list all customers in the system\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Web function to add a customer to the system\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Web function to edit a customer in the system\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Web function to delete a customer from the system\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Wb function to list all products in the system\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Web function on to add and edit a product in the system\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Web function to delete a product\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Shopping Cart\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Multiple Product types\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Search description and/or filter for products\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        Unit Testing\n" +
                                    "    </li>\n" +
                                    "    <li class=\"flex items-center\">\n" +
                                    "        <svg class=\"w-4 h-4 mr-1.5 text-green-500 dark:text-green-400 flex-shrink-0\" fill=\"currentColor\" viewBox=\"0 0 20 20\" xmlns=\"http://www.w3.org/2000/svg\"><path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z\" clip-rule=\"evenodd\"></path></svg>\n" +
                                    "        JavaDoc\n" +
                                    "    </li>\n" +
                                    "</ul>\n"+
                                    Util.forResponsiveness()+
                                    "</body>" +
                                    "</html>");

            }catch(SQLException se){
                System.out.println(se.getMessage());
            }
            out.close();
        }
    }

}