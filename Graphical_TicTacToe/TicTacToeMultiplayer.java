import Graphical_TicTacToe.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeMultiplayer extends JFrame {
    private MultiplayerBoardPanel boardPanel;

    public TicTacToeMultiplayer() {
        setTitle("TicTacToe - MULTIPLAYER");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        boardPanel = new MultiplayerBoardPanel();
        add(boardPanel);

        setVisible(true);
    }
}
