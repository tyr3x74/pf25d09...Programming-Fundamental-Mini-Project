// MainMenu.java
import Graphical_TicTacToe.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame {
    private String loggedInUsername; // Field to store the logged-in username

    // Constructor now accepts the logged-in username for display
    public MainMenu(String username) {
        this.loggedInUsername = username; // Store the username

        setTitle("Main Menu - Logged in as: " + loggedInUsername); // Update title
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10));
        SoundManager sound = new SoundManager();



        JButton soloButton = new JButton("SOLO");
        JButton multiButton = new JButton("MULTIPLAYER");

        soloButton.addActionListener(e -> {
            dispose(); // Close MainMenu
            // Pass the logged-in username to the TicTacToeAI game frame
            new TicTacToeAI(loggedInUsername); // Open solo game
            sound.playSound("audio/click_TicTacToe.wav");
        });

        multiButton.addActionListener(e -> {
            dispose();
            // Pass the logged-in username to the TicTacToeMultiplayer game frame
            new TicTacToeMultiplayer(loggedInUsername); // Open multiplayer game
            sound.playSound("audio/click_TicTacToe.wav");
        });

        add(soloButton);
        add(multiButton);

        setVisible(true);
    }

    // Original constructor without username (can be removed if all logins go through LoginForm)
    public MainMenu() {
        this(null); // Call the new constructor with a null username for display if no login occurred
    }
}
