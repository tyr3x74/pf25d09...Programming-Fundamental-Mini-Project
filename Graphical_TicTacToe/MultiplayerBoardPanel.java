// MultiplayerBoardPanel.java
import Graphical_TicTacToe.SoundManager;

import javax.swing.*;
import java.awt.*;

public class MultiplayerBoardPanel extends JPanel {
    private JButton[][] buttons = new JButton[3][3];
    private Board board;
    private Seed currentPlayer;

    private String loggedInUsername; // To display the username of Player X
    private int playerXScore; // Temporary score for Player X (usually the logged-in user)
    private int playerOScore; // Temporary score for Player O
    private JLabel scoreLabel; // To display the current score

    // Constructor now accepts the logged-in username
    public MultiplayerBoardPanel(String username) {
        this.loggedInUsername = username;
        this.playerXScore = 0; // Initialize temporary score for Player X
        this.playerOScore = 0; // Initialize temporary score for Player O

        board = new Board();
        board.init();
        currentPlayer = Seed.CROSS; // Player 'X' starts

        setLayout(new BorderLayout()); // Use BorderLayout to place score label at top

        // Initialize score label to show both player X and O scores
        scoreLabel = new JLabel("Player X (" + loggedInUsername + ") Score: " + playerXScore + " | Player O Score: " + playerOScore, JLabel.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(scoreLabel, BorderLayout.NORTH); // Add score label to the top

        JPanel boardPanel = new JPanel(new GridLayout(3, 3)); // Panel for the game buttons
        add(boardPanel, BorderLayout.CENTER); // Add board panel to the center

        initButtons(boardPanel); // Pass boardPanel to initButtons
    }

    private void initButtons(JPanel boardPanel) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 60));
                final int r = row, c = col;

                buttons[row][col].addActionListener(e -> handleMove(r, c));
                boardPanel.add(buttons[row][col]); // Add buttons to the boardPanel
            }
        }
    }

    private void handleMove(int row, int col) {
        if (board.cells[row][col].content == Seed.EMPTY) {
            SoundManager.playSound("audio/click_TicTacToe.wav");
            board.cells[row][col].content = currentPlayer;
            buttons[row][col].setText(currentPlayer == Seed.CROSS ? "X" : "O");

            if (board.hasWon(currentPlayer, row, col)) {
                SoundManager.playSound("audio/win_TicTacToe.wav");
                String winnerChar = (currentPlayer == Seed.CROSS) ? "X" : "O";
                String message;
                if (currentPlayer == Seed.CROSS) { // Player X wins
                    playerXScore++; // Increment temporary score for player X
                    message = "Player X (" + loggedInUsername + ") wins!";
                } else { // Player O wins
                    playerOScore++; // Increment temporary score for player O
                    message = "Player O wins! Their current session score: " + playerOScore;
                }
                updateScoreDisplay(); // Update the score label
                JOptionPane.showMessageDialog(this, message);
                Confirm();
            } else if (board.isDraw()) {
                SoundManager.playSound("audio/game_over_TicTacToe.wav");
                JOptionPane.showMessageDialog(this, "It's a draw!");
                Confirm();
            } else {
                togglePlayer();
            }
        }
    }

    private void togglePlayer() {
        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
    }

    private void updateScoreDisplay() {
        scoreLabel.setText("Player X (" + loggedInUsername + ") Score: " + playerXScore + " | Player O Score: " + playerOScore);
    }

    private void resetGame() {
        board.init();
        currentPlayer = Seed.CROSS; // Player X starts
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        // Scores remain for the current session.
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
            // Reset scores when user decides not to play again and returns to main menu
            playerXScore = 0;
            playerOScore = 0;
            // Jalankan kembali Main menu secara aman
            SwingUtilities.invokeLater(() -> new MainMenu(loggedInUsername)); // Pass username back
        }
    }
}
