
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.security.SecureRandom;
import java.util.logging.*;
import java.io.Console;
import java.util.NoSuchElementException;

/*
 * New Java Project built for security purposes. It is conincidently a
 * security tutorial to show best practices to be implemented as time
 * goes on. It is an iterative application for simple CRUD interactions The program will handle 
 * sensitive data and access that data from various throughput; access control. Other topics 
 * we've prepared to undertake are: Encryption, Authentication Best Practices, Input validation, and 
 * securely handling error messages. Honorable mention: class separation para control and access.
 * 
 ******** Developer: James Roberson
 ******** Tools: VScode, Git
 ******** Extensions: various java builds, and sql
 ******** Date: May 24, 2025 - <<< on-going >>>
 * 
 */


public class Gatekeeper
{
    static Logger logger = Logger.getLogger(GFG.class.getName());

    static String data_DB_URL = "";
    static String data_signatory = "";
    static String data_monogram = "";
    static Connection data_bond;

    public static List<String> retrieveAll(Connection conn) throws SQLException
    {

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

    public static void update_acct(Connection conn) throws SQLException
    {
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

    }

    public static List<String> retrieve_acct(Connection conn) throws SQLException
    {
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

    public static void delete_acct(Connection conn) throws SQLException
    {
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

    public static void insert_acct(Connection conn) throws SQLException
    {

        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        String insert_query = "INSERT INTO sensitive_data (web_addr, monogram, signatory) VALUES (?, ?, ?)";

        //Get new entry from user
        System.out.print("\n---> url addr: "); 
        String url_entry = scan.nextLine();
        System.out.print("---> password: "); 
        String password_entry = scan.nextLine();
        System.out.print("---> username: "); 
        String user_entry = scan.nextLine();
        System.out.println("\nLogging info... \n");
        

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

    public static String passTheSalt(int length)
    {
        // Generate a random character to pass into a byte array
        SecureRandom sr22 = new SecureRandom();
        byte[] sault = new byte[length];
        
        // Ingest until the desired length (Specified in main class)
        sr22.nextBytes(sault);
        return Base64.getEncoder().encodeToString(sault);
    }

    public static String hashStew(String password, String salt) throws NoSuchAlgorithmException
    {
        // Set crypto algo the hash func will use to structure resulting hash
        MessageDigest stomach = MessageDigest.getInstance("SHA-256");
        String hash_potatoes = salt + password; // Prepend salt
        
        byte[] hash = stomach.digest(hash_potatoes.getBytes());
        return Base64.getEncoder().encodeToString(hash); // Encode final string to pass back to main

    }

/* 
 * 
 * 
 * 
 * 
*/

    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, NoSuchElementException
    {
        // Use the main class to secure the hash for individual users (i.e. accounts
        // I've set up for this demo) but hashing is mainly conducted to instantiate 
        // new user passwords and is done with SHA-256 as shown below. 
                /*           
                *  try 
                * {
                        String quick_pass = "<insert>";
                        String salt = passTheSalt(16); //16 bytes == 128-bit salt ##recommended
                        String hash_ = hashStew(quick_pass, salt);

                        System.out.println("Salt: " + salt);
                        System.out.println("Hashed Password: " + hash_);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                */


        boolean keepGoing = false;
        Console console = System.console();

        if(console == null)
        {
            System.err.println("<<< No console available. Run in a terminal >>> ");
            return;
        }
        
        List<String> results = new ArrayList<>();
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        Properties props = new Properties();

            System.out.println("\n*****************************************************************\n");

            System.out.println(" Disclamier: ao[jdhvah sdkjvis ijsixkjij s0jviv0ksjv0kfjvijvkjvpk \n"
                             + " qajdvaonfokdnjn[lfmvnsoao aiodv svh vfsdcovisov vi iah vfsjiunvv \n"
                             + " a9 fijs fj fvi0si0jf i0viw9dvis  v s 9hvaxsvj9hs9hvsj vhvf9oisdd \n"
                             + " oa oksroigh ihokdfoivisho jhsojd sijsu dsvaijfrh oirdhj ioshvi d \n"
                             + " kohu eihssivhsai0v visrjv ijav 0irvoirvjasa9vharvih rvvsiusrhvri \n"
                             + " I will insert something here of grave importance eventaully my G ");

            System.out.println("\n*****************************************************************\n");
            System.out.println("\n//// Terminal Connection //// DB: ke93k*6h*v@456457/in/connection.reverb/nevermore_db");

              // Prompt for login
              System.out.print("\nEnter username: ");
              String signatore = scan.nextLine();
              char[] passwordChars = console.readPassword("Enter Password: ");
              String hanjock = new String(passwordChars);

                    // Pull resources
                    props.load(new FileInputStream("config.properties"));
            

                try 
                {
                    do
                    {
                        // Connect to authenticator database
                        String authenticator_url = props.getProperty("auth_url");
                        String authenticator_user = props.getProperty("auth_user");
                        String authenticator_pass = props.getProperty("auth_pass");
                        String sault = props.getProperty("main_sault");

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
                        String decoded = Gatekeeper.hashStew(hanjock, sault);
                            authenticator_stmt.setString(1, signatore);
                            authenticator_stmt.setString(2, decoded);
                        
                        
                        ResultSet authenticator_results = authenticator_stmt.executeQuery();
                        if(authenticator_results.next())
                        {

                                        // System.out.println("\n\t\t <<<Open Sessame>>> "); Quick check...
                            System.out.println("\t\t//Active Connection "); TimeUnit.SECONDS.sleep(1);
                            System.out.println("\n");

                            System.out.println("What would you like to do?\n");
                            System.out.println("0: Insert a record");
                            System.out.println("1: Retrieve record");
                            System.out.println("2: Update a record");
                            System.out.println("3: Retrieve (ALL) ");
                            System.out.println("4: Delete a record");
                            System.out.println("5: Generate a random passphrase\n");
                            System.out.print("Indicate by selecting a number (0-4): ");
                            int response = scan.nextInt();
                            switch(response)
                            {
                                case 0 :
                                    try
                                    {
                                        // Now, connect to sensitive information DB to execute desired functions
                                        data_DB_URL = props.getProperty("DB_URL");
                                        data_signatory = props.getProperty("data_user");
                                        data_monogram = props.getProperty("data_pass");
                                        data_bond = DriverManager.getConnection(data_DB_URL, data_signatory, data_monogram);

                                        System.out.println("\n######################################\n");
                                        System.out.println("Input Credentials --> ");
                            
                                        Gatekeeper.insert_acct(data_bond);
                                        System.out.println("\n\t <<< Entry added >>>");
                                        System.out.println("\n######################################\n");
                                
                                        data_bond.close();
                                    }catch (Exception e) {
                                        logger.warning("Database operation failed.");
                                    }
                                break;

                                case 1 :
                                    try
                                    {
                                        data_DB_URL = props.getProperty("DB_URL");
                                        data_signatory = props.getProperty("data_user");
                                        data_monogram = props.getProperty("data_pass");
                                        data_bond = DriverManager.getConnection(data_DB_URL, data_signatory, data_monogram);

                                        System.out.println("\n######################################\n");
                                        results = Gatekeeper.retrieve_acct(data_bond);

                                            System.out.println("\t" + "ID: " + results.get(0));
                                            System.out.println("\t" + "Location: " + results.get(1));
                                            System.out.println("\t" + "Access Key: " + results.get(2));
                                            System.out.println("\t" + "Handle: " + results.get(3));

                                        System.out.println("\n######################################\n");

                                        results.clear();
                                        data_bond.close();
                                    }catch (Exception e){
                                        logger.warning("Database operation failed.");
                                    }
                                break;

                                case 2 :
                                    try 
                                    {
                                        data_DB_URL = props.getProperty("DB_URL");
                                        data_signatory = props.getProperty("data_user");
                                        data_monogram = props.getProperty("data_pass");
                                        data_bond = DriverManager.getConnection(data_DB_URL, data_signatory, data_monogram);

                                        System.out.println("\n######################################\n");
                                        Gatekeeper.update_acct(data_bond);
                                        System.out.println("\n\t <<< Record Updated >>> ");
                                        System.out.println("\n######################################\n");
                                    } catch (Exception e) {
                                        logger.warning("Database operation failed.");
                                    }
                                break;

                                case 3 :
                                    try 
                                    {
                                        data_DB_URL = props.getProperty("DB_URL");
                                        data_signatory = props.getProperty("data_user");
                                        data_monogram = props.getProperty("data_pass");
                                        data_bond = DriverManager.getConnection(data_DB_URL, data_signatory, data_monogram);

                                        System.out.println("\n######################################\n");
                                        results = Gatekeeper.retrieveAll(data_bond);
                                        System.out.println("Retrieving data, stand by...\n"); TimeUnit.SECONDS.sleep(2);
                                        for(String s : results)
                                        {
                                            System.out.println("\t" + s);
                                        }
                                        System.out.println("\n######################################\n");
                                        results.clear();
                                        data_bond.close();
                                    } catch (Exception e) {
                                        logger.warning("Database operation failed.");
                                    }
                                break;

                                case 4 :
                                    try 
                                    {
                                        data_DB_URL = props.getProperty("DB_URL");
                                        data_signatory = props.getProperty("data_user");
                                        data_monogram = props.getProperty("data_pass");
                                        data_bond = DriverManager.getConnection(data_DB_URL, data_signatory, data_monogram);

                                        System.out.println("\n######################################\n");
                                        Gatekeeper.delete_acct(data_bond);
                                        System.out.println("\n\t <<< Record deleted >>>");
                                        System.out.println("\n######################################\n");
                                    } catch (Exception e) {
                                        logger.warning("Database operation failed.");
                                    }
                                break;

                                case 5 :

                                System.out.println("This is for case 5 - generate random key ");

                        }
                        } else {
                            System.out.println("Access Denied!\n"); TimeUnit.SECONDS.sleep(2);
                        }

                        System.out.print("\n Would you like to conduct more operations? y/n : ");
                        String quit = scan.next();
            
                            if(quit.equalsIgnoreCase("n"))
                            {  
                               keepGoing = false;
                            } else if(quit.equalsIgnoreCase("y"))
                            {
                                keepGoing = true;
                                TimeUnit.SECONDS.sleep(3);
                            } else {
                                System.out.println("\n<<< Qué .... >>> ");
                            }
                            
                    authenticator_results.close();
                    authenticator_stmt.close();
                    authenticator_bond.close();
                    } while(keepGoing);
                } catch (Exception e)
                {
                    logger.warning("Database operation failed.");
                }

    }
}



