package customers;

/**
 *
 * The Customer class represents a customer and contains their personal information such as their name, address, and telephone number.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private Address address;
    private String telephone;

    /**
     * Constructor for the Customer class.
     *
     * @param customerID   a unique identifier for the customer
     * @param firstName    the customer's first name
     * @param lastName     the customer's last name
     * @param address      the customer's address
     * @param telephone    the customer's telephone number
     */
    public Customer(int customerID, String firstName, String lastName, Address address, String telephone) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.telephone = telephone;
    }

    /**
     * Returns the customer's ID.
     *
     * @return  the customer's ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Sets the customer's ID.
     *
     * @param customerID   the customer's ID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Returns the customer's first name.
     *
     * @return  the customer's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the customer's first name.
     *
     * @param firstName    the customer's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the customer's last name.
     *
     * @return  the customer's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the customer's last name.
     *
     * @param lastName the customer's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the customer's address.
     *
     * @return  the customer's address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the customer's address.
     *
     * @param address the customer's address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns the customer's telephone number.
     *
     * @return  the customer's telephone number
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Sets the customer's telephone number.
     *
     * @param telephone the customer's telephone number
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Returns a representation of the customer's information in the format of " | customerID | firstName | lastName | address | telephone".
     *
     * @return a String representation of the customer's information
     */
    @Override
    public String toString() {
        return " | " + this.getCustomerID() + " | " + this.getFirstName() + " | " + this.getLastName() + " | " + this.getAddress() + " | " + this.getTelephone();
    }
}
