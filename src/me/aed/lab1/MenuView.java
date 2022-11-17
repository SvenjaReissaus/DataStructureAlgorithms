package me.aed.lab1;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class MenuView extends JFrame {
    private JPanel contentPane;
    private JButton ex1Button;
    private JButton ex2Button;
    private JButton ex3Button;
    private JButton ex4Button;
    private JButton backButton;
    private JLabel resumen;
    private JLabel resumen2;

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
        ex1Button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
            public void mouseEntered(MouseEvent e) {
                resumen.setText("En una escuela se tienen que almacenar en arreglos los siguientes datos");
                resumen2.setText("de los alumnos: nombre, edad, sexo, promedio de calificaciones, número de materias");
            }
        });
    }
}
