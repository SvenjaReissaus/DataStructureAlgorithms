package me.aed;

import javax.swing.*;

public class MainView extends JFrame {
    private JPanel contentPane;
    private JButton lab2Button;
    private JButton lab3Button;
    private JButton lab1Button;
    private JButton lab4Button;

    private JButton lab5Button;

    public MainView() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        pack();

        lab1Button.addActionListener(e -> {
            dispose();
            new me.aed.lab1.MenuView().setVisible(true);
        });
        lab2Button.addActionListener(e -> {
            dispose();
            new me.aed.lab2.MenuView().setVisible(true);
        });
        lab3Button.addActionListener(e -> {
            dispose();
            new me.aed.lab3.MenuView().setVisible(true);
        });
        lab4Button.addActionListener(e -> {
            dispose();
            new me.aed.lab4.MainView().setVisible(true);
        });
        lab5Button.addActionListener(e -> {
            dispose();
            new me.aed.lab5.MainView().setVisible(true);
        });

        // exitButton.addActionListener(e -> dispose());
    }
}
