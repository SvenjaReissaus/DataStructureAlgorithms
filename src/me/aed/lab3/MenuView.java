package me.aed.lab3;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MenuView extends JFrame {
    private JPanel contentPane;
    private JComboBox option;
    private JButton backButton;
    private JButton continueButton;
    private JLabel result;
    private JLabel original;
    private JPanel operationState;

    public MenuView() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);

        backButton.addActionListener(e -> {
            dispose();
            new me.aed.MainView().setVisible(true);
        });

        continueButton.addActionListener(e -> {
            switch (option.getSelectedIndex()) {
                case 0 -> numbers();
                case 1 -> words();
                case 2 -> product();
                case 3 -> characters();
                case 4 -> account();
                case 5 -> sequence();
            }
        });

        pack();
    }

    private void numbers() {
        int size = parseNumber("Ingrese el tamaño del arreglo");
        final int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = parseNumber(String.format("Ingrese el número %d", i + 1));
        original.setText(Arrays.toString(array));
        final int[] copy = Arrays.copyOf(array, size);
        for (int index = 0; size > 0; size--, index++)
            array[size - 1] = copy[index];
        result.setText(Arrays.toString(array));
        operationState.setVisible(true);
    }

    private void words() {
        final String word = JOptionPane.showInputDialog("Ingrese la palabra");
        original.setText(word);
        final StringBuilder builder = new StringBuilder();
        for (int i = word.length(); i > 0; i--)
            builder.append(word.charAt(i - 1));
        result.setText(builder.toString());
        operationState.setVisible(true);
    }

    private void product() {
        final int size = parseNumber("Ingrese el tamaño del arreglo");
        final int[] array = new int[size];
        for (int i = 0; i < size; i++)
            array[i] = parseNumber(String.format("Ingrese el número %d", i + 1));
        original.setText(Arrays.toString(array));
        int product = 1;
        for (int i : array)
            product *= i;
        result.setText(String.valueOf(product));
        operationState.setVisible(true);
    }

    private void characters() {
        final String word = JOptionPane.showInputDialog("Ingrese la frase");
        final char character = JOptionPane.showInputDialog("Ingrese el caracter a buscar").charAt(0);
        original.setText(word);
        int count = 0;
        for (int i = 0; i < word.length(); i++)
            if (word.charAt(i) == character) count++;
        result.setText(String.format("Se encontraron %d resultados.", count));
        operationState.setVisible(true);
    }

    private void account() {
        final StringBuilder output = new StringBuilder(String.format("%-10s %-10s %-10s\n", "Año", "Cta. Pedro", "Cta. Juan"));
        final double pedro = 400, juan = 500;
        final double pedroTaxes = 4, juanTaxes = 2.3;
        double pedroAccount = pedro, juanAccount = juan;
        for (int year = 2021; ; year++, pedroAccount += pedroAccount * (pedroTaxes / 100), juanAccount += juanAccount * (juanTaxes / 100)) {
            output.append(String.format("%-10s %-10s %-10s\n", year, String.format("%.2f", pedroAccount), String.format("%.2f", juanAccount)));
            if (pedroAccount > juanAccount) break;
        }
        JOptionPane.showMessageDialog(null, output);
    }

    private void sequence() {
        int number = -1;
        while (number < 1) number = parseNumber("Ingrese un numero mayor a 1");
        original.setText(String.valueOf(number));
        final ArrayList<Integer> sequence = new ArrayList<>();
        number = number % 2 == 0 ? number / 2 : number * 3 + 1;
        while (number != 1) sequence.add(number /= 2);
        result.setText(sequence.toString());
        operationState.setVisible(true);


    }

    private int parseNumber() {
        return parseNumber("Ingrese un número");
    }

    private int parseNumber(final String message) {
        try {
            return Integer.parseInt(JOptionPane.showInputDialog(message));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado no es un número");
            return parseNumber();
        }
    }
}
