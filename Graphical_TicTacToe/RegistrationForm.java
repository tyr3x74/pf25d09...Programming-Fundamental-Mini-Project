import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegistrationForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private AuthFrame parentAuthFrame; // Reference to the parent AuthFrame

    public RegistrationForm(AuthFrame parentAuthFrame) {
        this.parentAuthFrame = parentAuthFrame;

        setTitle("Register");
        setSize(350, 250);
        // Dispose only, don't exit whole app when this form is closed
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Add a WindowListener to show the parentAuthFrame when this window is closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                parentAuthFrame.setVisible(true);
            }
        });
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField();
        add(confirmPasswordField);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");

        registerButton.addActionListener(e -> performRegistration());
        backButton.addActionListener(e -> {
            dispose(); // Close current form
            parentAuthFrame.setVisible(true); // Re-show the AuthFrame
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);
        add(buttonPanel);

        setVisible(true);
    }

    private void performRegistration() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Basic password strength check (optional, but good practice)
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UserDAO userDAO = new UserDAO();
        try {
            // Passing null for email for now, modify if you add an email field
            if (userDAO.registerUser(username, password, null)) {
                JOptionPane.showMessageDialog(this, "Registration Successful! Please login.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close RegistrationForm
                parentAuthFrame.setVisible(true); // Go back to AuthFrame for login
            } else {
                // UserDAO.registerUser already shows specific messages for duplicate username
                // A generic failure message here if no specific message was shown by DAO
                // For example, if database connection failed before specific check
                // JOptionPane.showMessageDialog(this, "Registration failed. Please try again.", "Registration Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "An error occurred during registration: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}