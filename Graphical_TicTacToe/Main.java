import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    public Main() {
        setTitle("Main Menu");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton soloButton = new JButton("SOLO");
        JButton multiButton = new JButton("MULTIPLAYER");

        soloButton.addActionListener(e -> {
            dispose(); // tutup menu
            new TicTacToeAI(); // buka game vs AI
        });

        multiButton.addActionListener(e -> {
            dispose();
            new TicTacToeMultiplayer();
        });

        add(soloButton);
        add(multiButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
