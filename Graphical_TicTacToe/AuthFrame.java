// AuthFrame.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AuthFrame extends JFrame {

    public AuthFrame() {
        setTitle("Welcome - Login / Register");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Exits the application if this window is closed
        setLocationRelativeTo(null); // Center the window
        setLayout(new GridLayout(2, 1, 10, 10)); // 2 rows, 1 column, with gaps

        JButton loginButton = new JButton("LOGIN");
        JButton registerButton = new JButton("REGISTER");

        // Action listener for Login button
        loginButton.addActionListener(e -> {
            dispose(); // Close AuthFrame
            new LoginForm(this); // Open LoginForm, passing reference to AuthFrame
        });

        // Action listener for Register button
        registerButton.addActionListener(e -> {
            dispose(); // Close AuthFrame
            new RegistrationForm(this); // Open RegistrationForm, passing reference to AuthFrame
        });

        add(loginButton);
        add(registerButton);

        setVisible(true); // Make the frame visible
    }
}