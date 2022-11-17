package me.aed.lab1;

import javax.swing.*;
import java.util.Objects;

import static me.aed.shared.Strings.error;
import static me.aed.shared.Strings.success;

public final class Exercise3 extends JFrame {
    final EmployeeFactory factory = new EmployeeFactory(new Employee[10], 10);
    private JButton backButton;
    private JButton optionCreate;
    private JButton optionList;
    private JButton optionDelete;
    private JButton optionEdit;
    private JButton optionShow;
    private JPanel contentPane;

    public Exercise3() {
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

    record Employee(String name, String sex, int age) {
        @Override
        public String toString() {
            return String.format("%s - %s - %d", name, sex, age);
        }
    }

    record EmployeeFactory(Employee[] employees, int size) {
        static final String HEADER = String.format("%-14s - %s - %s\n", "Nombre", "Cursos", "Promedio");

        /**
         * Retorna la cantidad de employees creados
         *
         * @return Retorna la cantidad de valores no nulos en el arreglo
         */
        int getSize() {
            int count = 0;
            for (int i = 0; i < size; i++) if (employees[i] != null) count++;
            return count;
        }

        /**
         * Obtiene la siguiente posicion posible a utilizar en el arreglo
         *
         * @return Retorna la posicion posible a utilizar en el arreglo
         */
        int getNext() {
            for (int i = 0; i < size; i++)
                if (employees[i] == null) return i;
            return -1;
        }

        /**
         * Crea una lista conteniendo los employees como String
         */
        String list() {
            final StringBuilder sb = new StringBuilder(HEADER);
            for (Employee employee : employees) if (employee != null) sb.append(employee);
            return sb.toString();
        }

        /**
         * Retorna una cadena con el nombre de los employees
         *
         * @return Retorna una cadena con el nombre de los employees
         */
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
                    employees[i] = new Employee(employees[i].name, employees[i].sex, age); // Swap
                    success("Se ha modificado el empleado");
                    break;
                }
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
                final String clientName = JOptionPane.showInputDialog("Introduzca el nombre del empleado");
                if (clientName == null) return;
                final String clientSex = JOptionPane.showInputDialog("Introduzca el telefono del empleado");
                if (clientSex == null) return;
                final int age = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el saldo del empleado"));
                employees[next] = new Employee(clientName, clientSex, age);
                created = true;
            } catch (NumberFormatException e) {
                error("Se debe proveer un valor numerico");
            } finally {
                if (created) success("Se ha creado el empleado");
            }
        }
    }
}
