package me.aed.lab2;

import me.aed.ArrayFactory;
import me.aed.MainView;

import javax.swing.*;

public class MenuView extends JFrame {
    private JPanel contentPane;
    private JButton backButton;
    private JButton searchButton;
    private JButton sortButton;
    private JTextField sizeField;
    private JButton continueButton;

    public MenuView() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);

        if (factory.isInitialized()) {
            sizeField.setText(String.valueOf(factory.getSize()));
            sizeField.setEditable(false);
            continueButton.setEnabled(false);
            sortButton.setEnabled(true);
            searchButton.setEnabled(true);
        }

        sizeField.addActionListener(e -> {
            if (factory.isInitialized()) return;
            final int size = Integer.parseInt(sizeField.getText());
            continueButton.setEnabled(size > 0);
            factory.setSize(size);
            sizeField.setEditable(false);
            getRootPane().setDefaultButton(continueButton);
        });
        continueButton.addActionListener(e -> {
            if (!factory.fillArray()) return;
            continueButton.setEnabled(false);
            sortButton.setEnabled(true);
            searchButton.setEnabled(true);
        });

        backButton.addActionListener(e -> {
            dispose();
            new MainView().setVisible(true);
        });

        sortButton.addActionListener(e -> {
            dispose();
            new SortView().setVisible(true);
        });

        searchButton.addActionListener(e -> {
            dispose();
            new SearchView().setVisible(true);
        });

        pack();
    }

    // Static cause dependency injection is not working ):
    public static ArrayFactory factory = new ArrayFactory();
}
