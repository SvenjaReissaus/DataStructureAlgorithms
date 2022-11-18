package me.aed.lab4;

import me.aed.shared.AbstractView;

import javax.swing.*;

public final class MainView extends AbstractView {
    private final Notations notations = new Notations();
    private final Queue queue = new Queue(10);
    private JPanel contentPane;
    private JComboBox options;
    private JButton continueButton;
    private JButton regresarButton;


    public MainView() {
        super("Laboratorio 4");
        setContentPane(contentPane);
        pack();

        continueButton.addActionListener(e -> {
            switch (options.getSelectedIndex()) {
                case 0 -> converter();
                case 1 -> piles();
                case 2 -> queue();
            }
        });

        regresarButton.addActionListener(e -> {
            dispose();
            new me.aed.MainView().setVisible(true);
        });
    }

    private void converter() {
        final String expression = JOptionPane.showInputDialog("Ingrese la expresion matematica");
        final String[] options = {"Prefijo", "Postfijo"};
        final int option = JOptionPane.showOptionDialog(null, "Seleccione la opcion", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        JOptionPane.showMessageDialog(null, expression == null ? "No se especifico una expresion" : String.format("El resultado es: %s", option == 0 ? notations.toPrefix(expression) : notations.toPostfix(expression)));
    }

    private void piles() {
        // create a array  to save the names
        // ask the user how many names he wants to save
        //create a array to save the names
        final String[] names = new String[parseNumber("Ingrese la cantidad de nombres que desea ingresar")];
        int index = 0;
        // show and ask the user the option
        final String[] options = {"Agregar", "Eliminar", "Mostrar", "Salir"};
        int option = -1;
        while ((option = JOptionPane.showOptionDialog(null, "Seleccione la opcion", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0])) != 3) {
            //if option is 0 then add a name
            //ask the user the name to add if the array is full show a message
            if (option == 0) if (index < names.length) {
                names[index++] = JOptionPane.showInputDialog("Ingrese el nombre");
                // show a message
                JOptionPane.showMessageDialog(null, "Nombre agregado");
            } else JOptionPane.showMessageDialog(null, "El arreglo esta lleno");
                //if option is 1 then delete a name
            else if (option == 1) {
                //ask the user the name
                final String name = JOptionPane.showInputDialog("Ingrese el nombre");
                // find the name in the array and if theres no name show a message
                // if the name is the last name added in the array then delete the name and show a message
                // if the name is not the last name added in the array then delete the name and show a message
                int position = -1;
                for (int i = 0; i < index; i++)
                    if (names[i] != null && names[i].equals(name)) {
                        if (i == index - 1) {
                            names[i] = null;
                            index--;
                            position = i;
                            JOptionPane.showMessageDialog(null, "Nombre eliminado");
                        }
                    }
                // find the name but is not the last name added in the array then show a message
                if (position != -1 && position != index - 1)
                    JOptionPane.showMessageDialog(null, "Nombre encontrado pero no es el ultimo agregado");

                if (position != -1) JOptionPane.showMessageDialog(null, "Nombre no encontrado");
            }
            //if option is 2 then show the names
            else if (option == 2) {
                //create a string to save the names
                final StringBuilder output = new StringBuilder();
                //add the names to the string
                for (final String name : names) output.append(String.format("%s\n", name));
                //show the names
                JOptionPane.showMessageDialog(null, output);
            }
        }
    }

    private void queue() {
        int option = -1;
        final String[] options = {"Agregar", "Eliminar", "Mostrar", "Salir"};
        while ((option = JOptionPane.showOptionDialog(null, "Seleccione la opcion", "Opciones", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0])) != 3) {
            if (option == 0) queue.enQueue();
            else if (option == 1) {
                final String name = queue.deQueue();
                if (name != null) JOptionPane.showMessageDialog(null, String.format("Nombre eliminado: %s", name));
                else JOptionPane.showMessageDialog(null, "La cola esta vacia");
            } else if (option == 2) queue.displayQueue();
        }
    }

    private int parseNumber(final String message) {
        try {
            return Integer.parseInt(JOptionPane.showInputDialog(message));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El valor ingresado no es un número");
            return parseNumber(message);
        }
    }
}

