package products;

import java.sql.*;
import java.util.*;

/**
 *
 * The ProductDAO class represents a Data Access Object for products.
 * It provides methods for connecting to a SQLite database and performing CRUD operations on products.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class ProductDAO {

    /**
     * Constructs a new ProductDAO object.
     */
    public ProductDAO() {}

    /**
     * Connects to a SQLite database.
     *
     * @return a Connection object representing the database connection
     */
    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            String dbURL = "jdbc:sqlite:database.sqlite";
            dbConnection = DriverManager.getConnection(dbURL);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    /**
     * Retrieves all products from the database and returns them in an ArrayList.
     *
     * @return an ArrayList of Product objects representing all products in the database
     * @throws SQLException if a database error occurs
     */
    public ArrayList<Product> getAllProducts() throws SQLException {
        System.out.println("Retrieving all Products ...");
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM products;";
        ArrayList<Product> products = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery = " + query);
            result = statement.executeQuery(query); // Execute SQL query and record response to string
            while (result.next()) {

                int id = result.getInt("id");
                String sku = result.getString("sku");
                String description = result.getString("description");
                String category = result.getString("category");
                int price = result.getInt("price");
                products.add(new Product(id,sku,description,category,price));
            }
        } catch(Exception e) {
            System.out.println("get all products: "+e);
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return products;
    }

    /**
     * Retrieves a single product from the database with the given id and returns it as a Product object.
     *
     * @param id the id of the product to retrieve
     * @return the Product object representing the product with the given id
     * @throws SQLException if a database error occurs
     */
    public Product getProduct(int id) throws SQLException {

        Product product = null;
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;

        String query = "SELECT * FROM products WHERE id =" + id + ";";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery: " + query);
            // execute SQL query
            result = statement.executeQuery(query);

            while (result.next()) {


                int prod_id = result.getInt("id");
                String sku = result.getString("sku");
                String description = result.getString("description");
                String category = result.getString("category");
                int price = result.getInt("price");
                product = new Product(prod_id,sku,description,category,price);

            }
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return product;
    }

     /** Deletes a single product from the database with the given id.
     *
     * @param id the id of the product to delete
     * @return true if the product was successfully deleted, false otherwise
     * @throws SQLException if a database error occurs
     */
    public Boolean deleteProduct(int id) throws SQLException {
        System.out.println("Deleting product");
        Connection dbConnection = null;
        Statement statement = null;
        int result = 0;
        String query = "DELETE FROM products WHERE id = " + id + ";";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(query);
            // execute SQL query
            result = statement.executeUpdate(query);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }

    /** Updates a single product in the database with the given Product Object.
     *
     * @param product thr updated product information
     * @return true if the product was successfully updated, false otherwise
     * @throws SQLException if a database error occurs
     */
    public Boolean updateProduct(Product product) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String query = "UPDATE products SET sku = '"+ product.getSku() +"', description = '"+ product.getDescription()+"', category = '"+ product.getCategory()+"', price = "+ product.getPrice()+" WHERE id = "+ product.getId()+"; ";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(query);
            // execute SQL update
            statement.executeUpdate(query);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;

        } finally {

            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return true;
    }

    /** Adds a new product to the database with the given Product Object.
     *
     * @param product the new product to add
     * @return true if the customer was successfully added, false otherwise
     * @throws SQLException if a database error occurs
     */
    public boolean addProduct(Product product) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;

        String add = "INSERT INTO products (sku, description, category, price) VALUES ('"+product.getSku()+"','"+product.getDescription()+"','"+product.getCategory()+"',"+product.getPrice()+ ");";
        boolean ok = false;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println(add);
            // execute SQL query
            statement.execute(add);
            ok = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        return ok;
    }

    /**
     * Retrieves all products from the database and returns them in an ArrayList.
     *
     * @return an ArrayList of Product objects representing all products in the database
     * @throws SQLException if a database error occurs
     */
    public ArrayList<Product> getProductByCategory(String search) throws SQLException {
        System.out.println("Retrieving all Products ...");
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM products WHERE category = '" + search + "';";
        ArrayList<Product> products = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery = " + query);
            result = statement.executeQuery(query); // Execute SQL query and record response to string
            while (result.next()) {

                int id = result.getInt("id");
                String sku = result.getString("sku");
                String description = result.getString("description");
                String category = result.getString("category");
                int price = result.getInt("price");
                products.add(new Product(id,sku,description,category,price));
            }
        } catch(Exception e) {
            System.out.println("get all products: "+e);
        } finally {
            if (result != null) {
                result.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return products;
    }

}
