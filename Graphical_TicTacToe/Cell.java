import Graphical_TicTacToe.SoundManager;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cell extends JPanel {
    public int row, col;
    public Seed content;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.content = Seed.EMPTY;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SoundManager.playSound("audio/click_TicTacToe.wav");
            }
        });
    }
}
