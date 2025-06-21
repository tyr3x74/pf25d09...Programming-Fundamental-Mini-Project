// AppLauncher.java
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // --- IMPORTANT: CONFIGURE YOUR AIVEN CLOUD DATABASE DETAILS HERE ---
        // Replace these placeholder values with your actual Aiven connection credentials.
        String dbHost = "mysql-22b2418e-mzakynaufalbasuki-08c0.j.aivencloud.com"; // e.g., "my-mysql-service-abcd.aivencloud.com"
        String dbPort = "17851"; // e.g., "12345"
        String dbName = "defaultdb"; // e.g., "defaultdb" or your specific database name
        String dbUser = "avnadmin"; // e.g., "avnadmin"
        String dbPass = "AVNS_-CeYHlZTj8awQ3MJS1L"; // Your Aiven service password

        // Set the connection parameters for the Connect class
        Connect.setConnectionParams(dbHost, dbPort, dbName, dbUser, dbPass);

        // Ensure the Swing UI is created and updated on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {

            new AuthFrame(); // Start the authentication process
        });
    }
}