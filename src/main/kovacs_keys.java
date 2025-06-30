package main;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

import AccessControl.IngressMGMT;
import hashlings.hashIT;

public class kovacs_keys 
{
        static Logger logger = Logger.getLogger(GFG.class.getName());

    ///////////////////////////////////////////// ==> RBAC implementation on ( USERS )

     public void the_creation(Connection conn, String role) throws NoSuchAlgorithmException
    {
        hashIT key = new hashIT();
        IngressMGMT access = new IngressMGMT();
        if(!access.hasPermission_USER(role, "CREATE_USER"))
        {
            System.out.println("ACCESS DENIED: insufficient privileges to access this resource. ");
            return;
        }

        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        String create_query = "INSERT INTO authenticator_credentials_locale (auth_usr, auth_SALT, auth_pass, role) VALUES (?, ?, ?, ?)";

        System.out.print("Enter User: ");
        String auth_user = scan.nextLine();
        System.out.print("Enter password: ");
        String auth_pass = scan.nextLine();
        System.out.print("Enter role: ");
        String auth_role = scan.nextLine();

        String sault = key.passTheSalt(16);
        String hash_ = key.hashStew(auth_pass, sault);

        try
        {   TimeUnit.SECONDS.sleep(2);

            PreparedStatement data_stmt = conn.prepareStatement(create_query);

            data_stmt.setString(1, auth_user);
            data_stmt.setString(2, sault);
            data_stmt.setString(3, hash_);
            data_stmt.setString(3, auth_role);
            data_stmt.executeUpdate();
            
            data_stmt.close();
        } catch (Exception e){
            logger.warning("Database operation failed.");
        }

    }

    public void the_deletion(Connection conn, String role) throws SQLException
    {
        IngressMGMT access = new IngressMGMT();
        if(!access.hasPermission_USER(role, "DELETE_USER"))
        {
            System.out.println("ACCESS DENIED: insufficient privileges to accss this resource. ");
            return;
        }
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        String delete_query = "DELETE FROM authenticator_credentials_locale WHERE auth_usr=?";

        // Read input from user to specify record to delete
        System.out.print("Record to delete (usr) : ");
        String unimportant_sys_res = scan.nextLine();

        try {
            PreparedStatement data_stmt = conn.prepareStatement(delete_query);
            data_stmt.setString(1, unimportant_sys_res);
            data_stmt.executeUpdate();
            data_stmt.close();
        } catch (Exception e) {
            logger.warning("Database operation failed.");
        }
    }
    
}
