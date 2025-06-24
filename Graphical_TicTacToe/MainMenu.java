import Graphical_TicTacToe.SoundManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenu extends JFrame { // Renamed from Main
    public MainMenu() {
        setTitle("Main Menu"); // Changed title
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10)); // Adjusted for game options only
        SoundManager sound = new SoundManager();


        JButton soloButton = new JButton("SOLO");
        JButton multiButton = new JButton("MULTIPLAYER");

        soloButton.addActionListener(e -> {
            dispose(); // Close MainMenu
            new TicTacToeAI(); // Open solo game
            sound.playSound("audio/click_TicTacToe.wav");
        });

        multiButton.addActionListener(e -> {
            dispose();
            new TicTacToeMultiplayer(); // Open multiplayer game
            sound.playSound("audio/click_TicTacToe.wav");
        });

        add(soloButton);
        add(multiButton);

        setVisible(true);
    }

    // The main method is now in AppLauncher.java and should be removed from here.
    /*
    public static void main(String[] args) {
        new MainMenu();
    }
    */
}