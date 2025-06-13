import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
        }
        if (!checkGameOver('O')) {
            playerTurn = true;
        }
    }

    private boolean checkGameOver(char player) {
        if (GameUtils.hasWon(board, player)) {
            JOptionPane.showMessageDialog(this, player + " wins!");
            Confirm();
            return true;
        } else if (GameUtils.isDraw(board)) {
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
        int response = JOptionPane.showConfirmDialog(
                this,
                "Ingin bermain lagi?",
                "Game Selesai",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            // Tutup window game
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }

            // Jalankan kembali Main menu secara aman
            SwingUtilities.invokeLater(() -> Main.main(new String[]{}));
        }
    }
}
