package me.aed.lab1;

import javax.swing.*;

public final class MenuView extends JFrame {
    private JPanel contentPane;
    private JButton ex1Button;
    private JButton ex2Button;
    private JButton ex3Button;
    private JButton ex4Button;
    private JButton backButton;

    public MenuView() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(contentPane);

        backButton.addActionListener(e -> {
            dispose();
            new me.aed.MainView().setVisible(true);
        });

        ex1Button.addActionListener(e -> {
            dispose();
            new Exercise1().setVisible(true);
        });

        ex2Button.addActionListener(e -> {
            dispose();
            new Exercise2().setVisible(true);
        });

        ex3Button.addActionListener(e -> {
            dispose();
            new Exercise3().setVisible(true);
        });

        ex4Button.addActionListener(e -> {
            dispose();
            new Exercise4().setVisible(true);
        });

        pack();
    }
}
