package me.aed;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView extends JFrame {
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
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setContentPane(contentPane);
        pack();
        setLocationRelativeTo(null);

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


        //show info
        // if mouse is on button show info

        // exitButton.addActionListener(e -> dispose());
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //close everything
                dispose();

            }
        });
        lab1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
            public void mouseEntered(MouseEvent e) {
                //show info in resumen
                resumen.setText("Implementación de actualizaciones (Inserción, Eliminación y Modificación");
                resumen2.setText("en arreglos unidimensionales desordenados y ordenados.");

            }
        });
        lab2Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
            public void mouseEntered(MouseEvent e) {
                //show info in resumen
                resumen.setText("Métodos de ordenación y Búsqueda");
                resumen2.setText("");

            }
        });
        lab3Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
            public void mouseEntered(MouseEvent e) {
                //show info in resumen
                resumen.setText("PILAS Y COLAS");
                resumen2.setText("");

            }
        });
        lab4Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
            public void mouseEntered(MouseEvent e) {
                //show info in resumen
                resumen.setText("Recursividad ");
                resumen2.setText("");

            }
        });
        lab5Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
            public void mouseEntered(MouseEvent e) {
                //show info in resumen
                resumen.setText("Array List ");
                resumen2.setText("");

            }

        });
    }
}
