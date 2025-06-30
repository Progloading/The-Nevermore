package main;

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

public class Poes_keys 
{
    static Logger logger = Logger.getLogger(GFG.class.getName());

    public String randomPasswordgen()
    {
        System.out.println("Generating Random password... ");
        int length = 11;
        String password = "ABCDEFGHIJKLMNOPQRSTUZWXYZ"
                        + "0123456789"
                        + "abcdefghijklmnopqrstuvwxyz"
                        + "$_&#-";
                        
        StringBuilder build = new StringBuilder(length);
        for(int i = 0; i < length; i++)
        {
            int indicator = (int) (password.length() * Math.random());
            build.append(password.charAt(indicator));
        } 
        return build.toString();
    }

    public void delete_acct(Connection conn, String role) throws SQLException
    {
        IngressMGMT access = new IngressMGMT();
        if(!access.hasPermission_ACCT(role, "DELETE_ACCT"))
        {
            System.out.println("ACCESS DENIED: insufficient privileges to accss this resource. ");
            return;
        }
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        String delete_query = "DELETE FROM sensitive_data WHERE web_addr=?";

        // Read input from user to specify record to delete
        System.out.print("Record to delete (url) : ");
        String uniform_resourse_locator = scan.nextLine();

        try {
            PreparedStatement data_stmt = conn.prepareStatement(delete_query);
            data_stmt.setString(1, uniform_resourse_locator);
            data_stmt.executeUpdate();
            data_stmt.close();
        } catch (Exception e) {
            logger.warning("Database operation failed.");
        }
    }

    public List<String> retrieveAll(Connection conn, String role) throws SQLException, InterruptedException
    {
        IngressMGMT access = new IngressMGMT();
        if(!access.hasPermission_ACCT(role, "READ_ALL"))
        {
            System.out.println("ACCESS DENIED: insufficent privileges to access this resource. ");
        }

        System.out.println("Retrieving data, stand by...\n"); TimeUnit.SECONDS.sleep(2);
                                        
        List<String> parse_data = new ArrayList<>(100);
        String retrieve_ALL_query = "SELECT * FROM sensitive_data";

        try 
        {
            PreparedStatement data_stmt = conn.prepareStatement(retrieve_ALL_query);
            ResultSet results = data_stmt.executeQuery();

            while(results.next())
            {
                parse_data.add(" ");
                parse_data.add(results.getString("id"));
                parse_data.add(results.getString("web_addr"));
                parse_data.add(results.getString("monogram"));
                parse_data.add(results.getString("signatory")); 
            }
        } catch (Exception e) {
            logger.warning("Database operation failed.");
        }

        return parse_data;

    }

    public void update_acct(Connection conn, String role) throws SQLException
    {
        IngressMGMT access = new IngressMGMT();
        if(!access.hasPermission_ACCT(role, "UPDATE_ACCT"))
        {
            System.out.println("ACCESS DENIED: insufficient privileges to access this resource. ");
            return;
        }
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        String update_query_addr = "UPDATE sensitive_data SET web_addr=? WHERE web_addr=?";
        String update_query_monogram = "UPDATE sensitive_data SET monogram=? WHERE web_addr=?";
        String update_query_signatory = "UPDATE sensitive_data SET signatory=? WHERE web_addr=?";
        System.out.print("Enter the URL of the account you want to update: ");
        String address = scan.nextLine();


        System.out.println("\nResources pulled... \n");
        System.out.println("\nWhich would you like to update?\n");
        System.out.println("\t[0] URL/");
        System.out.println("\t[1] Access Key");
        System.out.println("\t[2] User Handle \n");
        System.out.print("Indicate by selecting a number: ");
        int response = scan.nextInt();

        @SuppressWarnings("resource")
        Scanner scanII = new Scanner(System.in);

            if(response == 0)
            {
                System.out.print("Enter new web address: ");
                String lil_webby = scanII.nextLine();

                try 
                {
                    PreparedStatement data_stmtI = conn.prepareStatement(update_query_addr);
                    data_stmtI.setString(1, lil_webby);
                    data_stmtI.setString(2, address);
                    data_stmtI.executeUpdate();
                } catch (Exception e) {
                    logger.warning("Database operation failed.");
                }
            }else if(response == 1)
            {
                System.out.print("Enter new key: ");
                String money_money_money_moneyyy = scanII.nextLine();

                try 
                {
                    PreparedStatement data_stmtII = conn.prepareStatement(update_query_monogram);
                    data_stmtII.setString(1, money_money_money_moneyyy);
                    data_stmtII.setString(2, address);
                    data_stmtII.executeUpdate();
                } catch (Exception e) {
                    logger.warning("Database operation failed.");
                }

            }else if(response == 2)
            {
                System.out.print("Enter new Handle: ");
                String handy_dandy = scanII.nextLine();

                try 
                {
                    PreparedStatement data_stmtIII = conn.prepareStatement(update_query_signatory);
                    data_stmtIII.setString(1, handy_dandy);
                    data_stmtIII.setString(2, address);
                    data_stmtIII.executeUpdate();
                } catch (Exception e) {
                    logger.warning("Database operation failed.");
                }

            }else{
                System.out.println("Incorrect response.");
            }
            System.out.println("\n\t <<< Record Updated >>> ");

    }

    public List<String> retrieve_acct(Connection conn, String role) throws SQLException
    {
        IngressMGMT access = new IngressMGMT();
        if(!access.hasPermission_ACCT(role, "READ_SOME"))
        {
            System.out.println("ACCESS DENIED: insufficient privileges to access this resource. ");
        }
        List<String> parse_data = new ArrayList<>(100);
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        String retrieve_query = "SELECT * FROM sensitive_data WHERE web_addr=?";

        System.out.print("Enter the URL you want to retrieve: ");
        String retrieval = scan.nextLine();

        try { TimeUnit.SECONDS.sleep(2);
            
            System.out.println("\nRetrieving... \n");

            PreparedStatement data_stmt = conn.prepareStatement(retrieve_query);
            data_stmt.setString(1, retrieval);
            ResultSet results = data_stmt.executeQuery();

            while(results.next())
            {
                parse_data.add(results.getString("id"));
                parse_data.add(results.getString("web_addr"));
                parse_data.add(results.getString("monogram"));
                parse_data.add(results.getString("signatory"));
            }

        results.close();
        data_stmt.close();
        } catch (Exception e) {
            logger.warning("Database operation failed.");
        }

        return parse_data;

    }
    
    public void insert_acct(Connection conn, String role) throws SQLException
    {
        IngressMGMT access = new IngressMGMT();

        if(!access.hasPermission_ACCT(role, "INSERT_ACCT"))
        {
            System.out.println("ACCESS DENIED: insufficient privileges to access this resource. ");
            return;
        }
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        String insert_query = "INSERT INTO sensitive_data (web_addr, monogram, signatory) VALUES (?, ?, ?)";

        System.out.println("Input Credentials --> ");

        //Get new entry from user
        System.out.print("\n---> url addr: "); 
        String url_entry = scan.nextLine();
        System.out.print("---> password: "); 
        String password_entry = scan.nextLine();
        System.out.print("---> username: "); 
        String user_entry = scan.nextLine();
        System.out.println("\nLogging info... \n");
        System.out.println("\n\t <<< Entry added >>>");
        

        try
        {   TimeUnit.SECONDS.sleep(2);

            PreparedStatement data_stmt = conn.prepareStatement(insert_query);

            data_stmt.setString(1, url_entry);
            data_stmt.setString(2, password_entry);
            data_stmt.setString(3, user_entry);
            data_stmt.executeUpdate();
            
            data_stmt.close();
        } catch (Exception e){
            logger.warning("Database operation failed.");
        }

    }
}
