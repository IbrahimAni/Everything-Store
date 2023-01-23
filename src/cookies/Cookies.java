package cookies;

import java.io.*;
import java.util.*;

/**
 +
 * The Cookies class provides methods to create and read cookies on the system.
 * The {@link #cookies(String, String, String)} method accepts a username and password as input and writes them to a file called "cookies.txt" in the current directory.
 * The {@link #readCookies()} method reads the "cookies.txt" file and returns the username and password as an ArrayList of strings.
 * These cookies can be used for authentication or to store user-specific information.
 * This class can be used to store or retrieve the user's information when they login and maintain the session for the user.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class Cookies {

    /**

     Creates a cookie file containing user credentials.
     @param username the username to be stored in the cookie file.
     @param password the password to be stored in the cookie file.
     @param auth the authentication status to be stored in the cookie file.
     @throws IOException if an error occurs while writing to the cookie file.
     */
    public static void cookies(String username, String password, String auth) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("cookies.txt"));
        out.write(username);
        out.write("\n" + password);
        out.write("\n" + auth);
        out.close();
    }

    /**
    * reads the cookies and returns the credentials
    * @return ArrayList contains user credentials
    * @throws IOException if an error occurs while reading the file
    */
    public static ArrayList<String> readCookies() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("cookies.txt"));
        ArrayList<String> adminDetails = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null){
            adminDetails.add(line);
        }
        return adminDetails;
    }

    /**

     Adds an item to the user's shopping cart.
     @param item the item to be added to the cart.
     @throws IOException if an error occurs while writing to the cart file.
     */
    public static void addToCart(int item) throws IOException{
        BufferedWriter out = new BufferedWriter(new FileWriter("cart.txt", false));

        out.write(Integer.toString(item));
        out.newLine();
        out.close();
    }

    /**

     Retrieves the item from the user's shopping cart.
     @return the item stored in the cart.
     @throws IOException if an error occurs while reading the cart file.
     */

    public static int readCart() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader("cart.txt"));
        int item = Integer.parseInt(reader.readLine());
        reader.close();
        return item;
    }

}
