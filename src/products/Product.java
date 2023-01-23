package products;

/**
 *
 * The Product class represents a product with an id, sku, description, category, and price.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class Product {
    private int id;
    private String sku;
    private String description;
    private String category;
    private int price;

    /**
     * Constructs a new Product object with the given id, sku, description, category, and price.
     *
     * @param id the id of the product
     * @param sku the SKU of the product
     * @param description the description of the product
     * @param category the category of the product
     * @param price the price of the product
     */
    public Product(int id, String sku, String description, String category, int price) {
        this.id = id;
        this.sku = sku;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    /**
     * Returns the id of the product.
     *
     * @return the id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the product.
     *
     * @param id the new id of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the SKU of the product.
     *
     * @return the SKU of the product
     */
    public String getSku() {
        return sku;
    }

    /**
     * Sets the SKU of the product.
     *
     * @param sku the new SKU of the product
     */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
     * Returns the description of the product.
     *
     * @return the description of the product
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the product.
     *
     * @param description the new description of the product
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the category of the product.
     *
     * @return the category of the product
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the product.
     *
     * @param category the new category of the product
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the new price of the product
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the Product object in the format " | id | sku | description | category | price | ".
     *
     * @return the string representation of the Product object
     */
    @Override
    public String toString() {
        return " | " + this.id + " | " + this.sku + " | " + this.description + " | " + this.category + " | " + this.price + " | ";
    }
}
