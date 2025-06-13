import javax.swing.*;
import java.awt.*;

public class TicTacToeAI extends JFrame {
    private GameBoard gameBoard;

    public TicTacToeAI() {
        setTitle("Tic Tac Toe - SOLO vs AI");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        gameBoard = new GameBoard();
        add(gameBoard);
        setVisible(true);
    }
}
