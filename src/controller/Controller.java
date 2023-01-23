package controller;

import com.sun.net.httpserver.*;
import customers.*;
import pages.*;
import pages.cart.*;
import pages.customers.*;
import pages.login.*;
import pages.products.*;
import pages.admins.*;
import products.*;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * The Controller class is responsible for managing the interactions between the user and the system.
 * It allows for the user to access different menus for managing products, customers, and the web server.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class Controller {

    /**
     * The main menu function which allows the user to select different options for managing products, customers, and the web server.
     * @throws SQLException if an error occurs while interacting with the database
     * @throws IOException if an error occurs while reading input from the user
     */
    public static void menu() throws SQLException, IOException {
        Scanner input = new Scanner(System.in);
        String selection;
        do {
            System.out.println("_______________________");
            System.out.println("The Everything Store");
            System.out.println("Choose from these options");
            System.out.println("_________________________");
            System.out.println("[1] Run Product Menu");
            System.out.println("[2] Run Customer Menu");
            System.out.println("[3] Run Web Menu");
            System.out.println("[99] Exit\n");

            selection = input.nextLine();

            switch (selection) {
                case "1":
                    Controller.runProducts();
                    break;
                case "2":
                    Controller.runCustomer();
                    break;
                case "3":
                    Controller.webServer();
                    break;
                case "99":
                    System.out.println("Exiting...");
                    break;
                default:
            }
        } while (!selection.equals("99"));
        input.close();
    }

    /**
     * Runs the product management menu, which allows the user to list all products, list a specific product, add a new product, update an existing product, and delete a product.
     * @throws SQLException if an error occurs while interacting with the database
     * @throws IOException if an error occurs while reading input from the user
     */
    public static void runProducts() throws SQLException, IOException {
        Scanner input = new Scanner(System.in);
        String selection;
        ProductDAO productDAO = new ProductDAO();
        do {
            System.out.println("_________________________");
            System.out.println("The Everything Store");
            System.out.println("Choose from these options");
            System.out.println("___________________________");

            System.out.println("[1] List all Products");
            System.out.println("[2] List a Product");
            System.out.println("[3] Add a Product");
            System.out.println("[4] Update a Product");
            System.out.println("[5] Delete a Product");
            System.out.println("[#] Main Menu");
            System.out.println("[99] Exit\n");

            selection = input.nextLine();

            switch (selection) {
                case "1":
                    System.out.println(productDAO.getAllProducts());
                    break;
                case "2":
                    System.out.println("Enter ID of the Product you wish to find: ");
                    int prod_id = input.nextInt();
                    System.out.println(productDAO.getProduct(prod_id));
                    break;
                case "3":
                    System.out.println("Enter sku: ");
                    String sku = input.nextLine();

                    System.out.println("Enter Description: ");
                    String description = input.nextLine();

                    System.out.println("Enter Category: ");
                    String category = input.nextLine();

                    System.out.println("Enter Price: ");
                    int price = input.nextInt();
                    input.nextLine();

                    Product product = new Product(0, sku, description, category, price);
                    productDAO.addProduct(product);
                    System.out.println("Product Added");
                    break;
                case "4":

                    System.out.println("Enter the ID of the Product you wish to update");
                    int upDateId = input.nextInt();
                    input.nextLine();

                    System.out.println("Enter New sku: ");
                    String newSku = input.nextLine();

                    System.out.println("Enter New Description: ");
                    String newDescription = input.nextLine();

                    System.out.println("Enter New Category: ");
                    String newCategory = input.nextLine();

                    System.out.println("Enter New Price: ");
                    int newPrice = input.nextInt();
                    input.nextLine();

                    Product newProduct = new Product(upDateId, newSku, newDescription, newCategory, newPrice);
                    productDAO.updateProduct(newProduct);
                    System.out.println("Product Updated");
                    break;
                case "5":
                    System.out.println("Enter the Product ID you wish to delete");
                    int del_Id = input.nextInt();
                    productDAO.deleteProduct(del_Id);
                    System.out.println("Product Deleted");
                    break;
                case "#":
                    Controller.menu();
                    break;
                default:
            }
        } while (!selection.equals("99"));

        input.close();
    }

    /**
     * Runs the customer management menu, which allows the user to list all customers, list a specific customer, add a new customer, update an existing customer, and delete a customer.
     * @throws SQLException if an error occurs while interacting with the database
     * @throws IOException if an error occurs while reading input from the user
     */
    public static void runCustomer() throws SQLException, IOException {
        Scanner input = new Scanner(System.in);
        String selection;
        CustomerDAO customerDAO = new CustomerDAO();
        do {
            System.out.println("_______________________");
            System.out.println("The Everything Store");
            System.out.println("Choose from these options");
            System.out.println("_________________________");
            System.out.println("[1] List all Customers");
            System.out.println("[2] List a Customer");
            System.out.println("[3] Add a Customer");
            System.out.println("[4] Update a Customer Detail");
            System.out.println("[5] Delete a Customer");
            System.out.println("[#] Main Menu");
            System.out.println("[99] Exit\n");

            selection = input.nextLine();

            switch (selection) {
                case "1":
                    System.out.println(customerDAO.getAllCustomers());
                    break;
                case "2":
                    System.out.println("Enter the customerID of the customer you wish to find: ");
                    int customerID = input.nextInt();
                    System.out.println(customerDAO.getCustomer(customerID));
                    break;
                case "3":
                    System.out.println("Enter First Name: ");
                    String firstName = input.nextLine();

                    System.out.println("Enter Last Name: ");
                    String lastName = input.nextLine();

                    System.out.println("Enter House Number: ");
                    String houseNumber = input.nextLine();

                    System.out.println("Enter Customer's Address Line 1: ");
                    String addressLine1 = input.nextLine();

                    System.out.println("Enter Customer's Address Line 2: ");
                    String addressLine2 = input.nextLine();

                    System.out.println("Enter Country: ");
                    String country = input.nextLine();

                    System.out.println("Enter Post Code: ");
                    String postCode = input.nextLine();

                    System.out.println("Enter Telephone Number: ");
                    String telephoneNumber = input.nextLine();

                    Address address = new Address(houseNumber, addressLine1, addressLine2, country, postCode);
                    Customer customer = new Customer(0, firstName, lastName, address, telephoneNumber);
                    customerDAO.addCustomer(customer);
                    System.out.println("Customer Added");
                    break;
                case "4":
                    System.out.println("Enter the customerID of the customer you wish to update: ");
                    int customerIDNew = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter New First Name: ");
                    String firstNameNew = input.nextLine();

                    System.out.println("Enter New Last Name: ");
                    String lastNameNew = input.nextLine();

                    System.out.println("Enter New House Number: ");
                    String houseNumberNew = input.nextLine();

                    System.out.println("Enter New Customer's Address Line 1: ");
                    String addressLine1New = input.nextLine();

                    System.out.println("Enter New Customer's Address Line 2: ");
                    String addressLine2New = input.nextLine();

                    System.out.println("Enter New Country: ");
                    String countryNew = input.nextLine();

                    System.out.println("Enter New Post Code: ");
                    String postCodeNew = input.nextLine();

                    System.out.println("Enter New Telephone Number: ");
                    String telephoneNumberNew = input.nextLine();;

                    Address addressNew = new Address(houseNumberNew, addressLine1New, addressLine2New, countryNew, postCodeNew);
                    Customer customerNew = new Customer(customerIDNew, firstNameNew, lastNameNew, addressNew, telephoneNumberNew);
                    customerDAO.updateCustomer(customerNew);
                    System.out.println("Customer Updated");
                    break;
                case "5":
                    System.out.println("Enter the customerID of the customer you wish to delete: ");
                    int customerIDDelete = input.nextInt();
                    input.nextLine();
                    customerDAO.deleteCustomer(customerIDDelete);
                    System.out.println("Customer Deleted");
                    break;
                case "#":
                    Controller.menu();
                    break;
                case "99":
                    System.out.println("Exiting...");
                    break;
                default:
            }
        } while (!selection.equals("99"));
        input.close();
    }

    /**
     * Runs the web server, which allows users to interact with the store through a web browser.
     * @throws IOException if an error occurs while setting up or running the server
     */
    public static void webServer() throws IOException {
        final int PORT = 8080;

        System.out.println("Starting Web Server...");

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT),0);

        //Login Page
        server.createContext("/login", new Login());
        server.createContext("/login/processLoginHandler", new ProcessLoginHandler());

        //Landing Page
        server.createContext("/", new Landing() );

        //Product Page
        server.createContext("/products", new DisplayAllProducts() );
        server.createContext("/products/update", new UpdateProduct() );
        server.createContext("/products/processUpdateProductHandler", new ProcessUpdateProductHandler() );
        server.createContext("/products/delete", new ProcessDeleteProductHandler() );
        server.createContext("/products/add", new AddProduct());
        server.createContext("/products/processAddProductHandler", new ProcessAddProductHandler());
        server.createContext("/products/find", new SearchProduct());
        server.createContext("/products/find/category", new SearchProductCategory());
        server.createContext("/products/processSearchProductByCategoryHandler", new ProcessSearchProductByCategoryHandler());
        server.createContext("/products/processSearchProductHandler", new ProcessSearchProductHandler());
        server.createContext("/products/add-to-cart", new AddToCart());
        server.createContext("/products/cart", new Cart());
        server.createContext("/products/cart/delete-item", new ItemRemovedFromCart());

        //Customer Page
        server.createContext("/customers", new DisplayAllCustomers() );
        server.createContext("/customers/update", new UpdateCustomer() );
        server.createContext("/customers/processUpdateCustomerHandler", new ProcessUpdateCustomerHandler() );
        server.createContext("/customers/delete", new ProcessDeleteCustomerHandler() );
        server.createContext("/customers/add", new AddCustomer());
        server.createContext("/customers/processAddCustomerHandler", new ProcessAddCustomerHandler());
        server.createContext("/customers/find", new SearchCustomer());
        server.createContext("/customers/processSearchPCustomerHandler", new ProcessSearchCustomerHandler());

        //Admin Page
        server.createContext("/admins", new DisplayAllAdmin() );
        server.createContext("/admins/update", new UpdateAdmin() );
        server.createContext("/admins/processUpdateAdminHandler", new ProcessUpdateAdminHandler() );
        server.createContext("/admins/delete", new ProcessDeleteAdminHandler() );
        server.createContext("/admins/add", new AddAdmin());
        server.createContext("/admins/processAddAdminHandler", new ProcessAddAdminHandler());
        server.createContext("/admins/find", new SearchAdmin());
        server.createContext("/admins/processSearchAdminHandler", new ProcessSearchAdminHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("The server is listening on port " + PORT);
    }

}

