// AppLauncher.java
import Graphical_TicTacToe.SoundManager;

import javax.swing.*;

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

        try {
            // Option 1: System Look and Feel (looks native to OS)
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Option 2: Nimbus Look and Feel (clean, cross-platform, built-in)
             UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

            // Option 3: FlatLaf (Highly recommended for modern, flat design)
            // Requires adding the FlatLaf dependency to your project (e.g., via Maven/Gradle or JAR)
            // FlatLaf.install(); // For FlatLaf Dark, Light, etc.
            // UIManager.setLookAndFeel(new FlatLightLaf()); // Example FlatLaf theme
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Could not set Look and Feel: " + e.getMessage());
        }

        SoundManager.playSound("audio/background_TicTacToe.wav");


        // Ensure the Swing UI is created and updated on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {

            new AuthFrame(); // Start the authentication process
        });
    }
}