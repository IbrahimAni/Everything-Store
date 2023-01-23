package customers;

/**
 * The Address class is used to model an address.
 * It has private fields for house number, address line 1, address line 2, country, and post code.
 * A constructor is provided to initialize these fields.
 * Getters and Setters are provided for each field
 * It also provides a toString() method to convert the address object to string representation {@link #toString()}
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class Address {
    private String house;
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String postCode;

    /**

     Initializes the address with the provided house, addressLine1, addressLine2, country and postCode.
     @param house the house number or name.
     @param addressLine1 the first line of the address.
     @param addressLine2 the second line of the address.
     @param country the country of the address.
     @param postCode the postal code of the address.
     */
    public Address(String house, String addressLine1, String addressLine2, String country, String postCode) {
        this.house = house;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.country = country;
        this.postCode = postCode;
    }

    /**
     * Returns the house number of the address
     * @return house number
     */
    public String getHouse() {
        return house;
    }

    /**
     * Sets the house number of the address
     * @param house the house number or name.
     */
    public void setHouse(String house) {
        this.house = house;
    }

    /**
     * Returns the first line of the address
     * @return address line 1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the first line of the address
     * @param addressLine1 the first line of the address.
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Returns the second line of the address
     * @return address line 2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the second line of the address
     * @param addressLine2 the second line of the address.
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Returns the country of the address
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the address
     * @param country the country of the address.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Returns the post code of the address
     * @return post code
     */
    public String getPostCode() {
        return postCode;
    }

    /**
     * Sets the post code of the address
     * @param postCode the postal code of the address.
     */
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * Returns a string representation of the address object in the format: " | house | addressLine1 | addressLine2 | country | postCode | "
     * @return the string representation of the address object
     */
    @Override
    public String toString() {
        return " | " + this.house + " | " + this.addressLine1 + " | " + this.addressLine2 + " | " + this.country + " | " + this.postCode;
    }
}
