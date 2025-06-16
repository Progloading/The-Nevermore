package db;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hashlings.hashIT;


public class DBConnections {

    //pull resources
    public static Properties poe = new Properties();


    public ResultSet auth_db_conn(String signatore, String hanjock) throws FileNotFoundException, IOException, SQLException, InterruptedException, NoSuchAlgorithmException
    {
        poe.load(new FileInputStream("config.properties"));
        hashIT hashing = new hashIT();

        // Connect to authenticator database
        String authenticator_url = poe.getProperty("auth_url");
        String authenticator_user = poe.getProperty("auth_user");
        String authenticator_pass = poe.getProperty("auth_pass");
        String sault = poe.getProperty("main_sault"); //admin access INSERT INTO authenticator_credentials_locale (id, auth_usr, auth_SALT, auth_pass)

        Connection authenticator_bond = DriverManager.getConnection(authenticator_url, authenticator_user, authenticator_pass);
        System.out.println("\nProcessing... \n"); TimeUnit.SECONDS.sleep(3);

        // Set parameters based on given input
        String authenticator_query = "SELECT * FROM authenticator_credentials_locale WHERE auth_usr = ? AND auth_pass = ?";
        PreparedStatement authenticator_stmt = authenticator_bond.prepareStatement(authenticator_query);
                        
        // Decode hashed password and set parameters
        // Salt can be ingested using ResultSet but to save time I've decided to 
        // and use a variable.
        // <<< CAVEAT: code must be modified upon switching user accounts >>>
        //      ## Temporary Fix: if-statment to toggle between the accounts dynamically
        
        String decoded = hashing.hashStew(hanjock, sault);
            authenticator_stmt.setString(1, signatore);
            authenticator_stmt.setString(2, decoded);
                        
                        
        ResultSet authenticator_results = authenticator_stmt.executeQuery();

        return authenticator_results;
    }
    
}
