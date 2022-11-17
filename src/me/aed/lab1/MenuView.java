package me.aed.lab1;

import me.aed.shared.AbstractView;

import javax.swing.*;

public final class MenuView extends AbstractView { // extends AbstractView es la magia
    private JPanel contentPane;
    private JButton ex1Button;
    private JButton ex2Button;
    private JButton ex3Button;
    private JButton ex4Button;
    private JButton backButton;
    private JLabel resumen;

    public MenuView() {
        super("Laboratorio 1"); // podemos reemplazar 3 lineas de codigo por un super(), incluso incluir un titulo para la vista super("Mi titulo")
        setContentPane(contentPane);
        pack();

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
        // addTooltip(variable que contineue al boton que se va a observar, variable que contineue el JLabel que se va a actualizar, texto como string);
        addTooltip(ex1Button, resumen, "En una escuela se tienen que almacenar en arreglos los siguientes datos");
        addTooltip(ex2Button, resumen, "No se que sigue");
    }
}
