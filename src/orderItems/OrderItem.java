package orderItems;


/**
 *
 * The OrderItem class represents an order item made by a user for a specific product and quantity.
 * It contains methods to set and get the orderItemId, userName, productId and quantity of the order item.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class OrderItem {
    private int orderItemId;
    private String userName;
    private int productId;
    private int quantity;

    /**
     * Constructs a new Order object with the given orderId, customerId, productId and quantity.
     *
     * @param orderItemId the unique identifier for the order
     * @param userName the unique identifier for the customer who made the order
     * @param productId the unique identifier for the product ordered
     * @param quantity the quantity of the product ordered
     */
    public OrderItem(int orderItemId, String userName, int productId, int quantity) {
        this.orderItemId = orderItemId;
        this.userName = userName;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Returns the unique identifier for the order item.
     *
     * @return the orderItemId
     */
    public int getOrderItemId() {
        return orderItemId;
    }

    /**
     * Returns the unique identifier for the user who made the order.
     *
     * @return the customerId
     */
    public String getUsername() {
        return userName;
    }

    /**
     * Returns the unique identifier for the product ordered.
     *
     * @return the productId
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Returns the quantity of the product ordered.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the unique identifier for the order item.
     *
     * @param orderItemId the orderId to set
     */
    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    /**
     * Sets the unique identifier for the user who made the order.
     *
     * @param userName the customerId to set
     */
    public void setUsername(String userName) {
        this.userName = userName;
    }

    /**
     * Sets the unique identifier for the product ordered.
     *
     * @param productId the productId to set
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Sets the quantity of the product ordered.
     *
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the Order object in the format " | orderId | customerId | productId | quantity |
     *
     * @return the string representation of the Order object
     */
    @Override
    public String toString() {
        return " | " + this.orderItemId + " | " + this.userName + " | " + this.productId + " | " + this.quantity + " | " ;
    }
}
