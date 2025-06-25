
// In LoginForm.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder; // For padding

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private AuthFrame parentAuthFrame;

    public LoginForm(AuthFrame parentAuthFrame) {
        this.parentAuthFrame = parentAuthFrame;

        setTitle("Login to Tic Tac Toe"); // More descriptive title
        setSize(400, 250); // Slightly larger
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentAuthFrame.setVisible(true);
            }
        });
        setLocationRelativeTo(null);

        // --- New Layout and Styling ---
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Overall padding
        mainPanel.setBackground(new Color(240, 240, 240)); // Light grey background

        // Title Label
        JLabel titleLabel = new JLabel("Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 50)); // Dark grey text
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form Panel (using GridBagLayout for alignment)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(255, 255, 255)); // White background for form area
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1), // Thin grey border
                new EmptyBorder(15, 15, 15, 15) // Internal padding
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(userLabel, gbc);

        usernameField = new JTextField(20); // Set preferred column width
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180))); // Custom border
        gbc.gridx = 1; gbc.gridy = 0; gbc.weightx = 1.0;
        formPanel.add(usernameField, gbc);

        // Password
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        gbc.gridx = 1; gbc.gridy = 1; gbc.weightx = 1.0;
        formPanel.add(passwordField, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0)); // Centered with gaps
        buttonPanel.setBackground(mainPanel.getBackground()); // Match main panel background

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(70, 130, 180)); // Steel blue
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setFocusPainted(false); // Remove border on focus
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25)); // Padding inside button
        loginButton.addActionListener(e -> performLogin());

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.setBackground(new Color(150, 150, 150)); // Grey
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        backButton.addActionListener(e -> {
            dispose();
            parentAuthFrame.setVisible(true);
        });

        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel); // Add the main panel to the frame
        setVisible(true);
    }


    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword()); // Get password as String

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter username and password.", "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserDAO userDAO = new UserDAO();
        try {
            if (userDAO.loginUser(username, password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close LoginForm
                parentAuthFrame.dispose(); // Close AuthFrame permanently

                // Pass the logged-in username to the MainMenu for display purposes
                new MainMenu(username); // Open the main game menu
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) { // Catch broader exceptions for connection issues etc.
            JOptionPane.showMessageDialog(this, "An error occurred during login: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
