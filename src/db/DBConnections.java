package db;

// Exception Imports ############################
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

// File io y load Imports ############
import java.util.Properties;
import java.io.FileInputStream;

// SQL Imports #########################
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


// Latency added for production level effect #####
import java.util.concurrent.TimeUnit;

import hashlings.hashIT;


public class DBConnections {

    // Pull resources
    public static Properties poe = new Properties();
    public static hashIT hashing = new hashIT();


    public ResultSet auth_db_conn(String signatore, String hanjock) throws FileNotFoundException, IOException, SQLException, InterruptedException, NoSuchAlgorithmException
    {
        ResultSet authenticator_results;

        // Pull resources for connection
            poe.load(new FileInputStream("config.properties"));
        

        // Connect to authenticator database using the account settings that have been set up
            String authenticator_url = poe.getProperty("auth_url");
            String authenticator_user = poe.getProperty("auth_user");
            String authenticator_pass = poe.getProperty("auth_pass");
            String sault = poe.getProperty("main_sault"); 

        Connection authenticator_bond = DriverManager.getConnection(authenticator_url, authenticator_user, authenticator_pass); TimeUnit.SECONDS.sleep(3);
        System.out.println("\nProcessing... \n");

        // Set parameters based on given input
        String authenticator_query = "SELECT * FROM authenticator_credentials_locale WHERE auth_usr = ? AND auth_pass = ?";
        PreparedStatement authenticator_stmt = authenticator_bond.prepareStatement(authenticator_query);
                        
        
        // Decode hashed password and set parameters
        // Salt can be ingested using ResultSet but to save time I've decided to 
        // use a variable.
        // <<< CAVEAT: code must be modified upon switching user accounts >>>
        //      ## Temporary Fix: if-statment to toggle between accounts dynamically
        
                String decoded = hashing.hashStew(hanjock, sault);
                    authenticator_stmt.setString(1, signatore);
                    authenticator_stmt.setString(2, decoded);
                                   
        
        authenticator_results = authenticator_stmt.executeQuery();

        return authenticator_results;
    }

    public Connection secretdbConnect() throws SQLException
    {
        String data_DB_URL = poe.getProperty("DB_URL");
        String data_signatory = poe.getProperty("data_user");
        String data_monogram = poe.getProperty("data_pass");

        try 
        {
            Connection data_bond = DriverManager.getConnection(data_DB_URL, data_signatory, data_monogram); 
            return data_bond;
        
        } catch (SQLException e) {
            System.err.println("ERROR: " + e.getMessage());
            throw e;
        }
    }
    
}
