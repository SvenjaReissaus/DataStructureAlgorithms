package me.aed;

import me.aed.shared.AbstractView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView extends AbstractView {
    private JPanel contentPane;
    private JButton lab2Button;
    private JButton lab3Button;
    private JButton lab1Button;
    private JButton lab4Button;

    private JButton lab5Button;
    private JButton laboratorio6Button;
    private JButton salirButton;
    private JLabel resumen;
    private JLabel resumen2;

    public MainView() {
        super("Algortimos y Estructuras de Datos");
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

        salirButton.addActionListener(e -> dispose());
        addTooltip(lab1Button, resumen, "Implementación de actualizaciones (Inserción, Eliminación y Modificación");
        addTooltip(lab2Button, resumen, "Métodos de ordenación y Búsqueda");
        addTooltip(lab3Button, resumen, "Pilas y Colas");
        addTooltip(lab4Button, resumen, "Recursividad");
        addTooltip(lab5Button, resumen, "Array List");
        addTooltip(laboratorio6Button, resumen, "");
    }
}
