package utils;

import java.nio.charset.*;
import java.security.*;

/**
 *
 * The HashPassword class provides a method for hashing passwords using the MD5 algorithm.
 * @author Ibrahim Anifowoshe (22450374).
 * @version 1.0
 */
public class HashPassword {

    /**
     * Hashes a given password using the MD5 algorithm.
     *
     * @param password the password to hash
     * @return the hashed password as a hexadecimal string
     */
    public static String hash(String password){
        String passwordHashed = null;
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes(StandardCharsets.UTF_8));

            byte[] passwordInByte = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : passwordInByte){
                stringBuilder.append(String.format("%02x", b));
            }
            passwordHashed = stringBuilder.toString();
        }catch (NoSuchAlgorithmException e){
            System.out.println(e.toString());
        }
        return passwordHashed;
    }
}
