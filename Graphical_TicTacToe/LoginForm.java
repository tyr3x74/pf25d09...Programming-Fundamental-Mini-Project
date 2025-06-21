import Graphical_TicTacToe.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private AuthFrame parentAuthFrame; // Reference to the parent AuthFrame

    public LoginForm(AuthFrame parentAuthFrame) {
        this.parentAuthFrame = parentAuthFrame;

        setTitle("Login");
        setSize(300, 200);
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
        setLayout(new GridLayout(4, 1, 10, 10));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back");

        loginButton.addActionListener(e -> performLogin());
        backButton.addActionListener(e -> {
            dispose(); // Close current form
            parentAuthFrame.setVisible(true); // Re-show the AuthFrame
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
        add(buttonPanel);

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

                new MainMenu(); // Open the main game menu
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) { // Catch broader exceptions for connection issues etc.
            JOptionPane.showMessageDialog(this, "An error occurred during login: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}