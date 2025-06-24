// GameBoard.java
import Graphical_TicTacToe.SoundManager;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private JButton[][] buttons = new JButton[3][3];
    private char[][] board = new char[3][3];
    private boolean playerTurn = true;
    private AIPlayer aiPlayer = new AIPlayer();

    private String loggedInUsername; // To display the username, not for score persistence
    private int playerScore; // Temporary score for the human player (X)
    private JLabel scoreLabel; // To display the current score

    // Constructor now accepts the logged-in username
    public GameBoard(String username) {
        this.loggedInUsername = username;
        this.playerScore = 0; // Initialize temporary score for this game session

        setLayout(new BorderLayout()); // Use BorderLayout to place score label at top

        // Initialize score label
        scoreLabel = new JLabel(loggedInUsername + "'s Score: " + playerScore, JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(scoreLabel, BorderLayout.NORTH); // Add score label to the top

        JPanel boardPanel = new JPanel(new GridLayout(3, 3)); // Panel for the game buttons
        add(boardPanel, BorderLayout.CENTER); // Add board panel to the center

        initBoard(boardPanel); // Pass boardPanel to initBoard
    }

    private void initBoard(JPanel boardPanel) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 60));
                boardPanel.add(buttons[row][col]); // Add buttons to the boardPanel

                final int r = row, c = col;
                buttons[row][col].addActionListener(e -> playerMove(r, c));
            }
        }
    }

    private void playerMove(int row, int col) {
        if (playerTurn && board[row][col] == ' ') {
            buttons[row][col].setText("X");
            board[row][col] = 'X';
            playerTurn = false;
            SoundManager.playSound("audio/click_TicTacToe.wav");
            if (!checkGameOver('X')) {
                aiMove();
            }
        }
    }

    private void aiMove() {
        int[] move = aiPlayer.bestMove(board);
        if (move[0] != -1) {
            board[move[0]][move[1]] = 'O';
            buttons[move[0]][move[1]].setText("O");

            SoundManager.playSound("audio/click_TicTacToe.wav");
        }
        if (!checkGameOver('O')) {
            playerTurn = true;
        }
    }

    private boolean checkGameOver(char player) {
        if (GameUtils.hasWon(board, player)) {
            SoundManager.playSound("audio/win_TicTacToe.wav");
            String message;
            if (player == 'X') {
                playerScore++; // Increment temporary score for human player
                updateScoreDisplay(); // Update the score label
                message = loggedInUsername + " wins!";
            } else {
                message = "AI wins! You lost.";
            }
            JOptionPane.showMessageDialog(this, message);
            Confirm();
            return true;
        } else if (GameUtils.isDraw(board)) {
            SoundManager.playSound("audio/game_over_TicTacToe.wav");
            JOptionPane.showMessageDialog(this, "It's a draw!");
            Confirm();
            return true;
        }
        return false;
    }

    private void updateScoreDisplay() {
        scoreLabel.setText(loggedInUsername + "'s Score: " + playerScore);
    }

    private void resetGame() {
        // Clear board state
        board = new char[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText(""); // Clear button text
                board[row][col] = ' '; // Reset internal board state
            }
        }
        playerTurn = true; // Reset turn to player X
        // playerScore remains for the current session.
        updateScoreDisplay(); // Update score display
        revalidate();
        repaint();
    }

    private void Confirm() {
        SoundManager.playSound("audio/playagain_TicTacToe.wav");
        int response = JOptionPane.showConfirmDialog(
                this,
                "Ingin bermain lagi?",
                "Game Selesai",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            SoundManager.playSound(("audio/playagain_TicTacToe.wav"));
            resetGame();
        } else {
            // Tutup window game
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
            // Reset score when user decides not to play again and returns to main menu
            playerScore = 0; // Score resets here
            // Jalankan kembali Main menu secara aman
            SwingUtilities.invokeLater(() -> new MainMenu(loggedInUsername)); // Pass username back
        }
    }
}
