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
            board.cells[row][col].content = currentPlayer;
            buttons[row][col].setText(currentPlayer == Seed.CROSS ? "X" : "O");

            if (board.hasWon(currentPlayer, row, col)) {
                String winner = currentPlayer == Seed.CROSS ? "X" : "O";
                JOptionPane.showMessageDialog(this, winner + " wins!");
                resetGame();
            } else if (board.isDraw()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetGame();
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
}
