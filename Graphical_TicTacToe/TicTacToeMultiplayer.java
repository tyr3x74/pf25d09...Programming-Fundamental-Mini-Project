// TicTacToeMultiplayer.java
import Graphical_TicTacToe.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeMultiplayer extends JFrame {
    private MultiplayerBoardPanel boardPanel;
    private String loggedInUsername; // Field to store the logged-in username for display

    // Constructor now accepts the logged-in username
    public TicTacToeMultiplayer(String username) {
        this.loggedInUsername = username;

        setTitle("TicTacToe - MULTIPLAYER (Player X: " + loggedInUsername + ")"); // Indicate who is player X
        setSize(650, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Pass the logged-in username to the MultiplayerBoardPanel constructor for display
        boardPanel = new MultiplayerBoardPanel(loggedInUsername);
        add(boardPanel);

        setVisible(true);
    }
}
