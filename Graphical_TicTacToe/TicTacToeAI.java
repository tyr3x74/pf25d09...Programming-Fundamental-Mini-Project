// TicTacToeAI.java
import Graphical_TicTacToe.SoundManager;

import javax.swing.*;
import java.awt.*;

public class TicTacToeAI extends JFrame {
    private GameBoard gameBoard;
    private String loggedInUsername; // Field to store the logged-in username for display

    // Constructor now accepts the logged-in username
    public TicTacToeAI(String username) {
        this.loggedInUsername = username;

        setTitle("Tic Tac Toe - SOLO vs AI (" + loggedInUsername + ")"); // Update title
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Pass the logged-in username to the GameBoard constructor for display
        gameBoard = new GameBoard(loggedInUsername);
        add(gameBoard);
        setVisible(true);
    }
}
