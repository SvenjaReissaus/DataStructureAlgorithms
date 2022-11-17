package me.aed.lab6;

import me.aed.shared.AbstractView;

import javax.swing.*;
import java.awt.*;

public final class MainView extends AbstractView {
    private final Node<String> root = new Node<>("Arbol", null);
    private JPanel contentPane;
    private JButton back;
    private JPanel tree;

    public MainView() {
        super();
        setContentPane(contentPane);
        pack();

        back.addActionListener(e -> {
            dispose();
            new me.aed.MainView().setVisible(true);
        });
        tree.setLayout(new GridLayout(1, 1));
        tree.add(root.render());
    }
}
