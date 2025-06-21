import Graphical_TicTacToe.SoundManager;

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
        SoundManager sound = new SoundManager();

        JButton soloButton = new JButton("SOLO");
        JButton multiButton = new JButton("MULTIPLAYER");

        soloButton.addActionListener(e -> {
            dispose(); // tutup menu
            new TicTacToeAI(); // buka game vs AI
            sound.playSound("audio/click_TicTacToe.wav");
        });

        multiButton.addActionListener(e -> {
            dispose();
            new TicTacToeMultiplayer();
            sound.playSound("audio/click_TicTacToe.wav");
        });

        add(soloButton);
        add(multiButton);

        setVisible(true);
    }

    public static void main(String[] args) {
        SoundManager sound = new SoundManager();
        sound.playBackground("audio/background_TicTacToe.wav");

        new Main();
    }
}





