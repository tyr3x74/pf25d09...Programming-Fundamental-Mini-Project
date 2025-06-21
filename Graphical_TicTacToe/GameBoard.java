import Graphical_TicTacToe.SoundManager;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    private JButton[][] buttons = new JButton[3][3];
    private char[][] board = new char[3][3];
    private boolean playerTurn = true;
    private AIPlayer aiPlayer = new AIPlayer();

    public GameBoard() {
        setLayout(new GridLayout(3, 3));
        initBoard();
    }

    private void initBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 60));
                add(buttons[row][col]);

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
            JOptionPane.showMessageDialog(this, player + " wins!");
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

    private void resetGame() {
        removeAll();
        revalidate();
        repaint();
        playerTurn = true;
        board = new char[3][3];
        initBoard();
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

            // Jalankan kembali Main menu secara aman
            SwingUtilities.invokeLater(() -> new MainMenu()); // Changed from Main.main
        }
    }
}
