package AccessControl;

public class IngressMGMT 
{
    public static final String INSERT = "INSERT";
    public static final String DELETE = "DELETE";
    public static final String UPDATE = "UPDATE";
    public static final String READ1 = "READ1"; // retrieveAll()
    public static final String READ2 = "READ2"; // retireve_acct()
    public static final String RANDOM_PASS = "RANDOM_PASS";

    public boolean hasPermission(String role, String permission)
    {
        switch(role.toLowerCase())
        {
            case "admin":
                return true; // admin has all permissions
            case "user":
                return permission.equals("READ1") || permission.equals("READ2") || permission.equals("UPDATE") || permission.equals("RANDOM_PASS");
            case "guest":
                return permission.equals("READ1") || permission.equals("RANDOM_PASS");
            default:
                return false;
        }

    }
    
}
