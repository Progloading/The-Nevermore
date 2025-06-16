package hashlings;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class hashIT {
    public static String passTheSalt(int length)
    {
        // Generate a random character to pass into a byte array
        SecureRandom sr22 = new SecureRandom();
        byte[] sault = new byte[length];
        
        // Ingest until the desired length (Specified in main class)
        sr22.nextBytes(sault);
        return Base64.getEncoder().encodeToString(sault);
    }

    public String hashStew(String password, String salt) throws NoSuchAlgorithmException
    {
        // Set crypto algo the hash func will use to structure resulting hash
        MessageDigest stomach = MessageDigest.getInstance("SHA-256");
        String hash_potatoes = salt + password; // Prepend salt
        
        byte[] hash = stomach.digest(hash_potatoes.getBytes());
        return Base64.getEncoder().encodeToString(hash); // Encode final string to pass back to main

    }
}
