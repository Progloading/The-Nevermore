package AccessControl;

public class IngressMGMT 
{
    // Permissions logic to retrieve ACCT information (sensitive db)
    public static final String INSERT_ACCT = "INSERT_ACCT";
    public static final String DELETE_ACCT = "DELETE_ACCT";
    public static final String UPDATE_ACCT = "UPDATE_ACCT";
    public static final String READ_ALL = "READ_ALL";
    public static final String READ_SOME = "READ_SOME";
    public static final String RANDOM_PASS = "RANDOM_PASS";
    
    // Permissions logic to query USER information (secure db). It's not actually used, but logically, it'll play.
    public static final String CREATE_USER = "CREATE_USER";
    public static final String DELETE_USER = "DELETE_USER";
    public static final String UPDATE_USER = "UPDATE_USER";
    
    

    public boolean hasPermission_ACCT(String role, String permission)
    {
        switch(role.toLowerCase())
        {
            case "admin":
                return true; // admin has all permissions
            case "user":
                // Reading is fundamental; fun, to mental. Will implement a function to toggle between users dynamically
                return permission.equals(READ_ALL) || permission.equals(READ_SOME) || permission.equals(RANDOM_PASS);
            case "guest":
                return permission.equals(RANDOM_PASS); // because what else could you possibly NEEEED!
            default:
                return false;
        }

    }

    public boolean hasPermission_USER(String role, String permission)
    {
        switch(role.toLowerCase())
        {
            // admin has complete control while others have no control
            case "admin":
                return true;
            default:
                return false;
                
        }

    }
    
}
