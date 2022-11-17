package me.aed.lab2;

import me.aed.SortMethods;

import javax.swing.*;
import java.util.Arrays;

import static me.aed.lab2.MenuView.factory;

public class SortView extends JFrame {
    private JPanel contentPane;
    private JButton backButton;
    private JButton sortButton;
    private JRadioButton bubbleSort;
    private JRadioButton shakeSort;
    private JRadioButton insertSort;
    private JRadioButton selectSort;
    private JRadioButton shellSort;
    private JLabel sorted;
    private JButton unsortedButton;

    public SortView() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(contentPane);

        backButton.addActionListener(e -> {
            dispose();
            new MenuView().setVisible(true);
        });

        final ButtonGroup group = new ButtonGroup();
        for (final JRadioButton button : new JRadioButton[]{bubbleSort, shakeSort, insertSort, selectSort, shellSort})
            group.add(button);
        sortButton.addActionListener(e -> {
            if (bubbleSort.isSelected()) factory.setMethod(SortMethods.BubbleSort);
            else if (shakeSort.isSelected()) factory.setMethod(SortMethods.ShakeSort);
            else if (insertSort.isSelected()) factory.setMethod(SortMethods.InsertSort);
            else if (selectSort.isSelected()) factory.setMethod(SortMethods.SelectSort);
            else if (shellSort.isSelected()) factory.setMethod(SortMethods.ShellSort);

            final int[] array = factory.sort();
            sorted.setText("El arreglo ordenado es: " + Arrays.toString(array));
            sorted.setVisible(true);
        });
        unsortedButton.addActionListener(e -> {
            sorted.setText("El arreglo desordenado es: " + Arrays.toString(factory.getArray()));
            sorted.setVisible(true);
        });
        pack();
    }
}
