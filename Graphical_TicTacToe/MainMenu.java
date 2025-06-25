// In MainMenu.java
import Graphical_TicTacToe.SoundManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JFrame {
    private String loggedInUsername;

    public MainMenu(String username) {
        this.loggedInUsername = username;

        setTitle("Tic Tac Toe - Main Menu"); // General title
        setSize(500, 350); // Slightly larger for better presentation
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // --- New Layout and Styling ---
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(220, 230, 240)); // Light blue-grey background

        // Welcome Label (instead of just title bar)
        JLabel welcomeLabel = new JLabel("Welcome, " + loggedInUsername + "!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 28)); // Game-like font
        welcomeLabel.setForeground(new Color(30, 60, 90)); // Dark blue text
        welcomeLabel.setBorder(new EmptyBorder(0, 0, 20, 0)); // Space below label
        mainPanel.add(welcomeLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Stack buttons vertically
        buttonPanel.setBorder(new EmptyBorder(10, 50, 10, 50)); // Horizontal padding for buttons
        buttonPanel.setBackground(mainPanel.getBackground());

        // Solo Button
        JButton soloButton = new JButton("Play Solo (vs AI)");
        soloButton.setFont(new Font("Verdana", Font.BOLD, 20));
        soloButton.setBackground(new Color(80, 180, 100)); // Greenish for "go"
        soloButton.setForeground(Color.WHITE);
        soloButton.setFocusPainted(false);
        soloButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        soloButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button horizontally
        soloButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, soloButton.getPreferredSize().height)); // Make it fill width
        soloButton.addActionListener(e -> {
            dispose();
            new TicTacToeAI(loggedInUsername);
            SoundManager.playSound("audio/click_TicTacToe.wav");
        });
        buttonPanel.add(soloButton);
        buttonPanel.add(Box.createVerticalStrut(20)); // Vertical space between buttons

        // Multiplayer Button
        JButton multiButton = new JButton("Play Multiplayer");
        multiButton.setFont(new Font("Verdana", Font.BOLD, 20));
        multiButton.setBackground(new Color(180, 80, 80)); // Reddish for multiplayer (distinct from solo)
        multiButton.setForeground(Color.WHITE);
        multiButton.setFocusPainted(false);
        multiButton.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        multiButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        multiButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, multiButton.getPreferredSize().height));
        multiButton.addActionListener(e -> {
            dispose();
            new TicTacToeMultiplayer(loggedInUsername);
            SoundManager.playSound("audio/click_TicTacToe.wav");
        });
        buttonPanel.add(multiButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel); // Add the main panel to the frame
        setVisible(true);
    }

    // Original constructor (can be removed if all logins go through LoginForm)
    public MainMenu() {
        this(null);
    }
}