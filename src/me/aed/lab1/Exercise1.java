package me.aed.lab1;

import javax.swing.*;
import java.util.Objects;

import static me.aed.shared.Strings.error;
import static me.aed.shared.Strings.success;

public final class Exercise1 extends JFrame {
    final StudentFactory factory = new StudentFactory(new Student[10], 10);
    private JPanel contentPane;
    private JButton backButton;
    private JButton optionCreate;
    private JButton optionList;
    private JButton optionDelete;
    private JButton optionEdit;
    private JButton optionShow;

    public Exercise1() {
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

    /**
     * Determina la estructura de un estudiante
     *
     * @param name    El nombre del estudiante
     * @param courses Los cursos del estudiante
     * @param grades  El promedio de notas del estudiante
     */
    record Student(String name, int courses, double grades) {
        /**
         * Convertir el estudiante a un String.
         *
         * @return Retorna el estudiante con formato "Nombre: <name>, Cursos: <courses>, Notas: <grades>"
         */
        @Override
        public String toString() {
            return String.format(
                    "%-20s - %-5s%d - %-5s%.2f\n", name, "", courses, "", grades
            );
        }
    }

    record StudentFactory(Student[] students, int size) {
        static final String HEADER = String.format("%-14s - %s - %s\n", "Nombre", "Cursos", "Promedio");

        /**
         * Retorna la cantidad de estudiantes creados
         *
         * @return Retorna la cantidad de valores no nulos en el arreglo
         */
        int getSize() {
            int count = 0;
            for (int i = 0; i < size; i++) if (students[i] != null) count++;
            return count;
        }

        /**
         * Obtiene la siguiente posicion posible a utilizar en el arreglo
         *
         * @return Retorna la posicion posible a utilizar en el arreglo
         */
        int getNext() {
            for (int i = 0; i < size; i++)
                if (students[i] == null) return i;
            return -1;
        }

        /**
         * Crea una lista conteniendo los estudiantes como String
         */
        String list() {
            final StringBuilder sb = new StringBuilder(HEADER);
            for (Student student : students) if (student != null) sb.append(student);
            return sb.toString();
        }

        /**
         * Retorna una cadena con el nombre de los estudiantes
         *
         * @return Retorna una cadena con el nombre de los estudiantes
         */
        String[] listNames() {
            final StringBuilder sb = new StringBuilder();
            for (Student student : students) if (student != null) sb.append(String.format("%s\n", student.name));
            return sb.toString().split("\n");
        }

        public void delete() {
            if (getSize() == 0) {
                error("No hay estudiantes");
                return;
            }
            // Obtener el estudiante a eliminar
            final String target = (String) JOptionPane.showInputDialog(
                    null, "Seleccione el estudiante a eliminar", "Listado de Estudiantes",
                    JOptionPane.INFORMATION_MESSAGE, null, listNames(), null
            );
            if (target == null) return;
            // Eliminar el estudiante
            for (int i = 0; i < size; i++)
                // NPE fix
                if (students[i] != null && Objects.equals(students[i].name, target)) {
                    students[i] = null;
                    success("Se ha eliminado el estudiante");
                    break;
                }
        }

        public void modify() {
            if (getSize() == 0) {
                error("No hay estudiantes");
                return;
            }
            // Obtener el estudiante a modificar
            final String target = (String) JOptionPane.showInputDialog(
                    null, "Seleccione el estudiante a modificar", "Listado de Estudiantes",
                    JOptionPane.INFORMATION_MESSAGE, null, listNames(), null
            );
            if (target == null) return;
            // Modificar el estudiante
            for (int i = 0; i < size; i++)
                // NPE fix
                if (students[i] != null && Objects.equals(students[i].name, target)) {
                    final int studentCourses = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la cantidad de cursos"));
                    final double studentGrade = Double.parseDouble(JOptionPane.showInputDialog("Introduzca el promedio de notas total"));
                    students[i] = new Student(target, studentCourses, studentGrade); // Swap
                    success("Se ha modificado el estudiante");
                    break;
                }
        }

        /**
         * Lee un estudiante determinado
         */
        void read() {
            if (getSize() == 0) {
                error("No hay estudiantes");
                return;
            }
            // Obtener el estudiante a mostrar
            final String target = (String) JOptionPane.showInputDialog(
                    null, "Seleccione el estudiante a mostrar", "Listado de Estudiantes",
                    JOptionPane.INFORMATION_MESSAGE, null, listNames(), null
            );
            if (target == null) return;
            // Mostrar el estudiante
            for (int i = 0; i < size; i++)
                // NPE fix
                if (students[i] != null && Objects.equals(students[i].name, target)) {
                    JOptionPane.showMessageDialog(null, students[i], "Estudiante", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
        }

        void read_all() {
            // Si no hay estudiantes
            if (getSize() == 0) {
                error("No hay estudiantes");
                return;
            }
            // Muestra los estudiantes
            JOptionPane.showMessageDialog(null, list(), "Listado de Estudiantes", JOptionPane.INFORMATION_MESSAGE);
        }

        /**
         * Crea un estudiante en el arreglo
         */
        void create() {
            // Obtiene la siguiente posicion a utilizar en el arreglo
            final int next = getNext();
            // No hay mas espacio en el arreglo
            if (next == -1) {
                error("No se pueden crear mas estudiantes");
                return;
            }
            // Determina si se ha creado el estudiante.
            boolean created = false;
            try {
                final String studentName = JOptionPane.showInputDialog("Introduzca el nombre del estudiante");
                if (studentName == null) return;
                final int studentCourses = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la cantidad de cursos"));
                final double studentGrade = Double.parseDouble(JOptionPane.showInputDialog("Introduzca el promedio de notas total"));
                students[next] = new Student(studentName, studentCourses, studentGrade);
                created = true;
            } catch (NumberFormatException e) {
                error("Se debe proveer un valor numerico");
            } finally {
                if (created) success("Se ha creado el estudiante");
            }
        }
    }
}
