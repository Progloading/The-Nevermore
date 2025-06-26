package main; // Package for runtime functions

// Exceptions y SQL conn Manager ###########
import java.sql.Connection;
import java.sql.ResultSet;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Console;
import java.util.NoSuchElementException;

// UTIL Imports ########################
import java.util.Scanner;
import java.util.Properties;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

// Package Imports ############
import db.DBConnections;





/*
 * New Java Project built for security purposes. It is conincidently a
 * security tutorial to show best practices to be implemented as time
 * goes on. It is an iterative application for simple CRUD interactions The program will handle 
 * sensitive data and access that data from various throughput; access control. Other topics 
 * we've prepared to undertake are: Encryption, Authentication Best Practices, Input validation, and 
 * securely handling error messages. Honorable mention: class separation para control and access.
 * 
 ******** Developer: James Roberson
 ******** Tools: Java kit, SQL, Git
 ******** Extensions: various java builds, and sql
 ******** Date: May 24, 2025 - <<< on-going >>>
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */


public class Gatekeeper
{
    // Be sure to create the class GFG
    static Logger logger = Logger.getLogger(GFG.class.getName());
    static Connection POE; 


    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException, NoSuchElementException
    {
        /*  Use the main class to secure the hash for individual users (i.e. accounts
            I've set up for this demo) but hashing is mainly conducted to instantiate 
            new user passwords, and as for this demo, it is done with SHA-256 as shown below. 
            Bcrypt is the production level we're working up to >>>
                           
                 try 
                 {
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
        
        // Objects/structures List ##################################
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        Properties props = new Properties();
        DBConnections bonnect = new DBConnections();
        Poes_keys key = new Poes_keys();
        List<String> results = new ArrayList<>();

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
                        ResultSet authenticator_results = bonnect.auth_db_conn(signatore, hanjock);
                        System.out.println("\t\t// Active Connection "); TimeUnit.SECONDS.sleep(1);


                        if(authenticator_results.next())
                        {
                            String role = authenticator_results.getString("role");
            
                            System.out.println("\nWhat would you like to do?\n");
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
                                        POE = bonnect.secretdbConnect();
                                        
                                        System.out.println("\n######################################\n");
                                        
                                        System.out.println("Input Credentials --> ");
                                        key.insert_acct(POE, role);
                                        System.out.println("\n\t <<< Entry added >>>");
                                        
                                        System.out.println("\n######################################\n");
                                
                                    } catch (Exception e) {
                                        logger.warning("Database operation failed.");
                                    }
                                break;

                                case 1 :
                                    try
                                    {
                                        POE = bonnect.secretdbConnect();

                                        System.out.println("\n######################################\n");                                    
                                        results = key.retrieve_acct(POE, role);

                                            System.out.println("\t" + "ID: " + results.get(0));
                                            System.out.println("\t" + "Location: " + results.get(1));
                                            System.out.println("\t" + "Access Key: " + results.get(2));
                                            System.out.println("\t" + "Handle: " + results.get(3));
                                       
                                        System.out.println("\n######################################\n");
                                        results.clear();
                                    
                                    } catch (Exception e) {
                                        logger.warning("Database operation failed.");
                                    }
                                break;

                                case 2 :
                                    try 
                                    {
                                        POE = bonnect.secretdbConnect();

                                        System.out.println("\n######################################\n");
                                        
                                        key.update_acct(POE, role);
                                        System.out.println("\n\t <<< Record Updated >>> ");
                                        
                                        System.out.println("\n######################################\n");                                  
                                    
                                    } catch (Exception e) {
                                        logger.warning("Database operation failed.");
                                    }
                                break;

                                case 3 :
                                    try 
                                    {
                                        POE = bonnect.secretdbConnect();

                                        System.out.println("\n######################################\n");
                                        
                                        results = key.retrieveAll(POE, role);
                                        System.out.println("Retrieving data, stand by...\n"); TimeUnit.SECONDS.sleep(2);
                                        for(String s : results)
                                        {
                                            System.out.println("\t" + s);
                                        }
                                        
                                        System.out.println("\n######################################\n");
                                        results.clear();
                                    
                                    } catch (Exception e) {
                                        logger.warning("Database operation failed.");
                                    }
                                break;

                                case 4 :
                                    try 
                                    {
                                        POE = bonnect.secretdbConnect();
        
                                        System.out.println("\n######################################\n");
                                        
                                        key.delete_acct(POE, role);
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
                            
                    } while(keepGoing);
                } catch (Exception e)
                {
                    logger.warning("Database operation failed.");
                }

    }
}



