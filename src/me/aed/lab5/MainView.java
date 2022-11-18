package me.aed.lab5;

import me.aed.shared.AbstractView;
import me.aed.shared.Strings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public final class MainView extends AbstractView {
    private final ArrayListFactory<Student> students = new ArrayListFactory<>();
    private final EmployeeListFactory employees = new EmployeeListFactory();
    private final ArrayListFactory<Integer> numbers = new ArrayListFactory<>();
    private final ArrayListFactory<String> strings = new ArrayListFactory<>();
    private int current = 0;
    private JPanel contentPane;
    private JButton exercise1;
    private JButton exercise2;
    private JButton exercise3;
    private JButton exercise4;
    private JButton exercise5;
    private JButton addList;
    private JList list1;
    private JPanel operationalPane;
    private JButton cleanList;
    private JButton exitList;
    private JList<Object> list2;
    private JList list3;
    private JList idList;
    private JLabel listHeader1;
    private JLabel listHeader2;
    private JLabel listHeader3;
    private JLabel flatValues;
    private JLabel flatList1;
    private JLabel flatList2;
    private JLabel flatValues1;
    private JLabel flatValues2;
    private JPanel flats;
    private JPanel actions;
    private JList lifoList;
    private JPanel lifoPane;
    private JButton regresarButton;
    private JLabel resumen;

    public MainView() {
        super("Laboratorio 5");
        setContentPane(contentPane);
        //make it resizeble
        setResizable(true);
        pack();
        setLocationRelativeTo(null);
        exercise1.addActionListener(e -> clear(1));
        exercise2.addActionListener(e -> clear(2));
        exercise3.addActionListener(e -> clear(3));
        exercise4.addActionListener(e -> clear(4));
        exercise5.addActionListener(e -> clear(5));
        cleanList.addActionListener(e -> {
            if (current == 0) return;
            if (current == 1) students.clean();
            else if (current == 2 || current == 5) numbers.clean();
            else if (current == 3) employees.clean();
            else if (current == 4) strings.removeLast();
            render();
        });
        addList.addActionListener(e -> {
            if (current == 0) return;
            if (current == 1) students.add(new Student(
                    JOptionPane.showInputDialog("Introduzca el nombre del alumno"),
                    JOptionPane.showInputDialog("Introduzca la carrera del alumno"),
                    JOptionPane.showInputDialog("Introduzca el carnet del alumno")
            ));
            else if (current == 2) numbers.add(Strings.parseInt("Introduzca un numero entero"));
            else if (current == 3) employees.add(new Employee(
                    JOptionPane.showInputDialog("Introduzca el nombre del empleado"),
                    JOptionPane.showInputDialog("Introduzca el numero del empleado"),
                    Double.parseDouble(JOptionPane.showInputDialog("Introduzca el salario del empleado"))
            ));
            else if (current == 4) strings.add(JOptionPane.showInputDialog("Introduzca una cadena de texto"));

            else if (current == 5)
                numbers.add(Strings.parseInt("Introduzca un numero entero"));
            render();
        });
        exitList.addActionListener(e -> clear(0));
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new me.aed.MainView().setVisible(true);
            }
        });
        exercise1.addMouseListener(new MouseAdapter() {
           //use is on the button, show message in resumen
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                resumen.setText("Escribir un programa que llene una lista de estudiantes de segundo año que llevan matemática básica ");
            }
        });

        exercise2.addMouseListener(new MouseAdapter() {
            //use is on the button, show message in resumen
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                resumen.setText("Escriba un programa que, dada una lista que contiene números enteros, la divida en dos listas independientes ");
            }
        });

        exercise3.addMouseListener(new MouseAdapter() {
            //use is on the button, show message in resumen
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                resumen.setText("Una empresa necesita almacenar el nombre, numero de empleado y salario de un grupo de empleados y calcular la nómina total ");
            }
        });
        exercise4.addMouseListener(new MouseAdapter() {
            //use is on the button, show message in resumen
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                resumen.setText("Escribir un programa utilizando ArrayList que represente una pila ");
            }
        });
        exercise5.addMouseListener(new MouseAdapter() {
            //use is on the button, show message in resumen
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                resumen.setText("Escribir un programa que cree una lista de números positivos y que de esta lista cree dos más");
            }
        });
    }

    private void render() {
        if (current == 1 || current == 3) {
            final int size = current == 1 ? students.get().size() : employees.get().size();
            if (size == 0) {
                list1.setListData(new String[0]);
                list2.setListData(new String[0]);
                list3.setListData(new String[0]);
                idList.setListData(new String[0]);
                return;
            }
            final Object[] data = new Object[size];
            final Object[] data1 = new Object[size];
            final Object[] data2 = new Object[size];
            final Object[] data3 = new Object[size];
            if (current == 1) for (int i = 0; i < size; i++) {
                final Student student = students.get().get(i);
                data[i] = i + 1;
                data1[i] = student.name();
                data2[i] = student.major();
                data3[i] = student.id();
            }
            else for (int i = 0; i < size; i++) {
                final Employee employee = employees.get().get(i);
                data[i] = i + 1;
                data1[i] = employee.name();
                data2[i] = employee.code();
                data3[i] = employee.salary();
            }
            idList.setListData(data);
            list1.setListData(data1);
            list2.setListData(data2);
            list3.setListData(data3);
        } else if (current == 2 || current == 5) {
            flatValues.setText(Arrays.toString(numbers.get().toArray()));
            final ArrayList<Integer> values1 = new ArrayList<>();
            final ArrayList<Integer> values2 = new ArrayList<>();
            for (final int value : numbers.get())
                if (current == 2) if (value >= 0) values1.add(value);
                else values2.add(value);
                else if (current == 5) if (value >= 10) values1.add(value);
                else values2.add(value);
            flatValues1.setText(Arrays.toString(values1.toArray()));
            flatValues2.setText(Arrays.toString(values2.toArray()));
        } else if (current == 4) {
            list1.setListData(strings.get().toArray());
        }

    }

    private void clear(final int next) {
        students.clean();
        employees.clean();
        numbers.clean();
        strings.clean();
        exercise1.setEnabled(next == 1 || next == 0);
        exercise2.setEnabled(next == 2 || next == 0);
        exercise3.setEnabled(next == 3 || next == 0);
        exercise4.setEnabled(next == 4 || next == 0);
        exercise5.setEnabled(next == 5 || next == 0);
        current = next;
        listHeader1.setText("Nombre");
        cleanList.setText(current == 4 ? "Eliminar" : "Vaciar");
        if (current == 1) {
            listHeader2.setText("Carrera");
            listHeader3.setText("Carnet");
        } else if (current == 2) {
            flatList1.setText("Lista de Positivos");
            flatList2.setText("Lista de Negativos");
        } else if (current == 3) {
            listHeader2.setText("Numero");
            listHeader3.setText("Salario");
        } else if (current == 4) {
            listHeader1.setText("Pila");
            listHeader2.setText("");
            listHeader3.setText("");
        } else if (current == 5) {
            flatList1.setText("Mayores a 10");
            flatList2.setText("Menores a 10");
        } else {
            listHeader2.setText("");
            listHeader3.setText("");
            flatList1.setText("");
            flatList2.setText("");
        }
        lifoList.setVisible(next == 4);
        operationalPane.setVisible(next == 1 || next == 3 || next == 4);
        flats.setVisible(next == 2 || next == 5);
        actions.setVisible(next != 0);
        render();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
