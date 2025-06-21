import Graphical_TicTacToe.SoundManager;

import javax.swing.*;
import java.awt.*;

public class MultiplayerBoardPanel extends JPanel {
    private JButton[][] buttons = new JButton[3][3];
    private Board board;
    private Seed currentPlayer;

    public MultiplayerBoardPanel() {
        board = new Board();
        board.init();
        currentPlayer = Seed.CROSS;
        setLayout(new GridLayout(3, 3));
        initButtons();
    }

    private void initButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.BOLD, 60));
                final int r = row, c = col;

                buttons[row][col].addActionListener(e -> handleMove(r, c));
                add(buttons[row][col]);
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
                String winner = currentPlayer == Seed.CROSS ? "X" : "O";
                JOptionPane.showMessageDialog(this, winner + " wins!");
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

    private void resetGame() {
        board.init();
        currentPlayer = Seed.CROSS;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
    }
    private void Confirm() {
        int response = JOptionPane.showConfirmDialog(
                this,
                "Ingin bermain lagi?",
                "Game Selesai",
                JOptionPane.YES_NO_OPTION
        );

        if (response == JOptionPane.YES_OPTION) {
            SoundManager.playSound("audio/playagain_TicTacToe.wav");
            resetGame();
        } else {
            // Tutup window game
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }

            // Jalankan kembali Main menu secara aman
            SwingUtilities.invokeLater(() -> new MainMenu());
        }
    }
}
