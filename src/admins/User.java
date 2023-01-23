package admins;

/**
 *
 * The User class represents a user in the system with a unique userId, userName, and password.
 *
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class User {
    /**
     * The unique userId of the user.
     */
    private int userId;

    /**
     * The userName of the user.
     */
    private String userName;

    /**
     * The password of the user.
     */
    private String password;

    /**
     * Constructs a new User object with the specified userId, userName, and password.
     *
     * @param userId the unique userId of the user
     * @param userName the userName of the user
     * @param password the password of the user
     */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Returns the userId of the user.
     *
     * @return the userId of the user
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the userId of the user.
     *
     * @param userId the userId of the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Returns the userName of the user.
     *
     * @return the userName of the user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the userName of the user.
     *
     * @param userName the userName of the user
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Returns the password of the user.
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the password of the user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the User object in the format: " | userId | userName | password | "
     *
     * @return a string representation of the User object
     */
    @Override
    public String toString() {
        return " | " + this.userId + " | " + this.userName + " | " + this.password + " | ";
    }
}
