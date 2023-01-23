package orderItems;

import java.sql.*;
import java.util.*;

/**
 *
 * The OrderItemDAO class provides methods to interact with an SQLite database of Order items.
 * It can retrieve all order items, a single orderItem by id, delete an orderItem, and update an orderItem.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class OrderItemDAO {
    /**
     * Constructs a new OrderItemDAO object.
     */
    public OrderItemDAO() {}

    /**
     * Connects to the SQLite database.
     *
     * @return a Connection object for the database connection
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
     * Retrieves all orderItems from the database and returns them as an ArrayList of OrderItems objects.
     *
     * @return an ArrayList of OrderItem objects representing all order items in the database
     * @throws SQLException if a database error occurs
     */
    public ArrayList<OrderItem> getAllOrderItems() throws SQLException {
        System.out.println("Retrieving all Orders items ...");
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;
        String query = "SELECT * FROM orderItems;";
        ArrayList<OrderItem> orderItems = new ArrayList<>();

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery = " + query);
            result = statement.executeQuery(query); // Execute SQL query and record response to string
            while (result.next()) {

                int orderItemId = result.getInt("orderItemId");
                String userName = result.getString("user");
                int productId = result.getInt("product");
                int quantity = result.getInt("quantity");

                orderItems.add(new OrderItem(orderItemId, userName, productId, quantity));
            }
        } catch(Exception e) {
            System.out.println("get all orderItems: "+e);
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
        return orderItems;
    }

    /**
     * Retrieves a single orderItem from the database by its id and returns it as an OrderItem object.
     *
     * @param orderItemId the unique identifier of the order to retrieve
     * @return an Order object representing the order with the given id
     * @throws SQLException if a database error occurs
     */
    public OrderItem getOrderItem(int orderItemId) throws SQLException {

        OrderItem orderItem = null;
        Connection dbConnection = null;
        Statement statement = null;
        ResultSet result = null;

        String query = "SELECT * FROM orderItems WHERE orderItemId =" + orderItemId + ";";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            System.out.println("DBQuery: " + query);
            // execute SQL query
            result = statement.executeQuery(query);

            while (result.next()) {


                int orderItemIdIdentifier = result.getInt("orderItemId");
                String userName = result.getString("user");
                int productId = result.getInt("product");
                int quantity = result.getInt("quantity");

                orderItem = new OrderItem(orderItemIdIdentifier, userName, productId, quantity);

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
        return orderItem;
    }

    /**
     * Retrieves a single order from the database by its id and returns it as an Order object.
     *
     * @param id the unique identifier of the order to retrieve
     * @return an Order object representing the order with the given id
     * @throws SQLException if a database error occurs
     */
    public Boolean deleteOrderItem(int id) throws SQLException {
        System.out.println("Deleting order items");
        Connection dbConnection = null;
        Statement statement = null;
        int result = 0;
        String query = "DELETE FROM orderItems WHERE orderItemId = " + id + ";";
        System.out.println(query);
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

    /**
     * Updates an orderItem in the database by its id.
     *
     * @param orderItemId the unique identifier of the order item to update
     * @param quantity the new quantity of the order
     * @return true if the update is successful, false otherwise
     * @throws SQLException if a database error occurs
     */
    public Boolean updateOrderItem(int orderItemId, int quantity) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String query = "UPDATE orderItems SET quantity = "+ quantity +" WHERE orderItemId = "+ orderItemId +"; ";

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

    /**
     * Adds a new order to the database
     *
     * @param orderItem the object of the order that the user wants to add to the database
     * @return true if the addition is successful, false otherwise
     * @throws SQLException if a database error occurs
     */
    public boolean addOrderItem(OrderItem orderItem) throws SQLException{
        Connection dbConnection = null;
        Statement statement = null;

        String add = "INSERT INTO orderItems (user, product, quantity) VALUES ('"+ orderItem.getUsername() + "', " + orderItem.getProductId() + ", " + orderItem.getQuantity()+ ");";
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
}
