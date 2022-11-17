package me.aed.lab2;

import me.aed.MainView;

import javax.swing.*;

import static me.aed.lab2.MenuView.factory;

public class SearchView extends JFrame {
    private JPanel contentPane;
    private JButton searchButton;
    private JTextField searchField;
    private JButton backButton;
    private JLabel found;
    private JLabel notFound;

    public SearchView() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(contentPane);

        backButton.addActionListener(e -> {
            dispose();
            new MenuView().setVisible(true);
        });
        searchButton.addActionListener(e -> {
            final int value = Integer.parseInt(searchField.getText());
            final int index = factory.search(value);
            if (index == Integer.MIN_VALUE) {
                notFound.setVisible(true);
                found.setVisible(false);
            } else {
                notFound.setVisible(false);
                found.setText("El valor " + value + " se encuentra en la posición " + index);
                found.setVisible(true);
            }
        });
        pack();
    }

    public static final class Main {

        public static void main(final String... args) {
            final MainView window = new MainView();
            window.setVisible(true);
        }
    }
}
