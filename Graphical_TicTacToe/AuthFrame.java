// In AuthFrame.java
// ... imports ...

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class AuthFrame extends JFrame {

    public AuthFrame() {
        setTitle("Tic Tac Toe - Welcome");
        setSize(400, 300); // Slightly larger
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25)); // Padding
        mainPanel.setBackground(new Color(230, 240, 250)); // Light background

        JLabel welcomeTitle = new JLabel("Tic Tac Toe", JLabel.CENTER);
        welcomeTitle.setFont(new Font("Impact", Font.BOLD, 36)); // Impactful font for game title
        welcomeTitle.setForeground(new Color(40, 80, 120));
        mainPanel.add(welcomeTitle, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Vertical stack
        buttonPanel.setBackground(mainPanel.getBackground());
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0)); // Space below title

        JButton loginButton = new JButton("LOGIN");
        JButton registerButton = new JButton("REGISTER");

        // Apply consistent button styling
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        Dimension buttonSize = new Dimension(200, 50); // Fixed size for consistency

        styleButton(loginButton, buttonFont, new Color(70, 130, 180), Color.WHITE, buttonSize);
        styleButton(registerButton, buttonFont, new Color(120, 180, 120), Color.WHITE, buttonSize);

        loginButton.addActionListener(e -> {
            dispose();
            new LoginForm(this);
        });

        registerButton.addActionListener(e -> {
            dispose();
            new RegistrationForm(this);
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(Box.createVerticalStrut(15)); // Space between buttons
        buttonPanel.add(registerButton);

        // Center the button panel within the main panel
        JPanel centerPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for centering
        centerPanel.setBackground(mainPanel.getBackground());
        centerPanel.add(buttonPanel);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    // Helper method to apply consistent button styling
    private void styleButton(JButton button, Font font, Color bgColor, Color fgColor, Dimension size) {
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Internal padding
        button.setAlignmentX(Component.CENTER_ALIGNMENT); // Center text/content in button
        button.setPreferredSize(size); // Set preferred size
        button.setMinimumSize(size);   // Ensure it doesn't shrink too much
        button.setMaximumSize(size);   // Ensure it doesn't grow too much
    }
}