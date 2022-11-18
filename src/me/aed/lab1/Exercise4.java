package me.aed.lab1;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

import static me.aed.shared.Strings.error;
import static me.aed.shared.Strings.success;

public final class Exercise4 extends JFrame {
    final EmployeeFactory factory = new EmployeeFactory(new Employee[10], 10);
    private JPanel contentPane;
    private JButton backButton;
    private JButton optionCreate;
    private JButton optionList;
    private JButton optionDelete;
    private JButton optionEdit;
    private JButton optionShow;

    public Exercise4() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setContentPane(contentPane);

        pack();
        setLocationRelativeTo(null);

        backButton.addActionListener(e -> {
            dispose();
            new MenuView().setVisible(true);
        });

        optionCreate.addActionListener(e -> factory.create());
        optionList.addActionListener(e -> factory.read_all());
        optionDelete.addActionListener(e -> factory.delete());
        optionEdit.addActionListener(e -> factory.modify());
        optionShow.addActionListener(e -> factory.read());

        pack();
    }

    record Employee(String name, String address, int age, String sex, int years) {


        @Override
        public String toString() {
            return String.format("%-10s - %-10s - %-5d - %-5s - %-5s ", name, address, age, sex, years);
        }
        //Nombre, Dirección, Edad, Sexo, Años de antigüedad
    }

    record EmployeeFactory(Employee[] employees, int size) {
        static final String HEADER = String.format("%-14s - %s - %s - %s - %s\n", "Nombre", "Direccion", "Edad", "Sexo", "Años de antigüedad");

        int getSize() {
            int count = 0;
            for (int i = 0; i < size; i++) if (employees[i] != null) count++;
            return count;
        }

        int getNext() {
            for (int i = 0; i < size; i++)
                if (employees[i] == null) return i;
            return -1;
        }

        String list() {
            final StringBuilder sb = new StringBuilder(HEADER);
            for (Employee employee : employees) if (employee != null) sb.append(employee);
            return sb.toString();
        }

        String[] listNames() {
            final StringBuilder sb = new StringBuilder();
            for (Employee employee : employees) if (employee != null) sb.append(String.format("%s\n", employee.name));
            return sb.toString().split("\n");
        }

        public void delete() {
            if (getSize() == 0) {
                error("No hay empleados");
                return;
            }
            // Obtener el empleado a eliminar
            final String target = (String) JOptionPane.showInputDialog(
                    null, "Seleccione el empleado a eliminar", "Listado de Empleados",
                    JOptionPane.INFORMATION_MESSAGE, null, listNames(), null
            );
            if (target == null) return;
            // Eliminar el empleado
            for (int i = 0; i < size; i++)
                // NPE fix
                if (employees[i] != null && Objects.equals(employees[i].name, target)) {
                    employees[i] = null;
                    success("Se ha eliminado el empleado");
                    break;
                }
            sort();
        }

        // Ordenar los empleados
        public void sort() {
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    if (employees[j].name.compareToIgnoreCase(employees[i].name) > 0)
                        Collections.swap(Arrays.asList(employees), i, j);
        }

        public void modify() {
            if (getSize() == 0) {
                error("No hay empleados");
                return;
            }
            // Obtener el empleado a modificar
            final String target = (String) JOptionPane.showInputDialog(
                    null, "Seleccione el empleado a modificar", "Listado de Empleados",
                    JOptionPane.INFORMATION_MESSAGE, null, listNames(), null
            );
            if (target == null) return;
            // Modificar el empleado
            for (int i = 0; i < size; i++)
                // NPE fix
                if (employees[i] != null && Objects.equals(employees[i].name, target)) {
                    final int age = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la edad del empleado"));
                    employees[i] = new Employee(employees[i].name, employees[i].address,employees[i].age, employees[i].sex, employees[i].years); // Swap
                    success("Se ha modificado el empleado");
                    break;
                }
            sort();
        }

        /**
         * Lee un empleado determinado
         */
        void read() {
            if (getSize() == 0) {
                error("No hay empleados");
                return;
            }
            // Obtener el empleado a mostrar
            final String target = (String) JOptionPane.showInputDialog(
                    null, "Seleccione el empleado a mostrar", "Listado de Empleados",
                    JOptionPane.INFORMATION_MESSAGE, null, listNames(), null
            );
            if (target == null) return;
            // Mostrar el empleado
            for (int i = 0; i < size; i++)
                // NPE fix
                if (employees[i] != null && Objects.equals(employees[i].name, target)) {
                    JOptionPane.showMessageDialog(null, employees[i], "empleado", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
        }

        void read_all() {
            // Si no hay employees
            if (getSize() == 0) {
                error("No hay employees");
                return;
            }
            // Muestra los employees
            JOptionPane.showMessageDialog(null, list(), "Listado de employees", JOptionPane.INFORMATION_MESSAGE);
        }

        /**
         * Crea un empleado en el arreglo
         */
        void create() {
            // Obtiene la siguiente posicion a utilizar en el arreglo
            final int next = getNext();
            // No hay mas espacio en el arreglo
            if (next == -1) {
                error("No se pueden crear mas employees");
                return;
            }
            // Determina si se ha creado el empleado.
            boolean created = false;
            try {
                final String name = JOptionPane.showInputDialog("Introduzca el nombre del empleado");
                if (name == null) return;
                final String address = JOptionPane.showInputDialog("Introduzca la direccion del empleado");
                if (address == null) return;
                final int age = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el saldo del empleado"));
                if (age == 0) return;
                final String sex = JOptionPane.showInputDialog("Introduzca el sexo del empleado");
                final int years = Integer.parseInt(JOptionPane.showInputDialog("Introduzca los años de antiguedad del empleado"));
                employees[next] = new Employee(name, address, age, sex, years);
                created = true;
            } catch (NumberFormatException e) {
                error("Se debe proveer un valor numerico");
            } finally {
                if (created) success("Se ha creado el empleado");
            }
            sort();
        }
    }

}
