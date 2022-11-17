package me.aed.lab1;

import javax.swing.*;
import java.util.Objects;

import static me.aed.shared.Strings.error;
import static me.aed.shared.Strings.success;

public final class Exercise2 extends JFrame {
    final ClientFactory factory = new ClientFactory(new Client[10], 10);
    private JPanel contentPane;
    private JButton backButton;
    private JButton optionCreate;
    private JButton optionList;
    private JButton optionDelete;
    private JButton optionEdit;
    private JButton optionShow;

    public Exercise2() {
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(contentPane);

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

    record Client(String name, String phone, double balance, boolean owes) {
        /**
         * Retorna la informacion del cliente.
         *
         * @return String con la informacion del cliente.
         */
        @Override
        public String toString() {
            return String.format("%s %s %.2f %s\n", name, phone, balance, owes ? "Si" : "No");
        }
    }

    record ClientFactory(Client[] clients, int size) {
        static final String HEADER = String.format("%-14s - %s - %s\n", "Nombre", "Cursos", "Promedio");

        /**
         * Retorna la cantidad de clientes creados
         *
         * @return Retorna la cantidad de valores no nulos en el arreglo
         */
        int getSize() {
            int count = 0;
            for (int i = 0; i < size; i++) if (clients[i] != null) count++;
            return count;
        }

        /**
         * Obtiene la siguiente posicion posible a utilizar en el arreglo
         *
         * @return Retorna la posicion posible a utilizar en el arreglo
         */
        int getNext() {
            for (int i = 0; i < size; i++)
                if (clients[i] == null) return i;
            return -1;
        }

        /**
         * Crea una lista conteniendo los clientes como String
         */
        String list() {
            final StringBuilder sb = new StringBuilder(HEADER);
            for (Client client : clients) if (client != null) sb.append(client);
            return sb.toString();
        }

        /**
         * Retorna una cadena con el nombre de los clientes
         *
         * @return Retorna una cadena con el nombre de los clientes
         */
        String[] listNames() {
            final StringBuilder sb = new StringBuilder();
            for (Client client : clients) if (client != null) sb.append(String.format("%s\n", client.name));
            return sb.toString().split("\n");
        }

        public void delete() {
            if (getSize() == 0) {
                error("No hay clientes");
                return;
            }
            // Obtener el cliente a eliminar
            final String target = (String) JOptionPane.showInputDialog(
                    null, "Seleccione el cliente a eliminar", "Listado de Clientes",
                    JOptionPane.INFORMATION_MESSAGE, null, listNames(), null
            );
            if (target == null) return;
            // Eliminar el cliente
            for (int i = 0; i < size; i++)
                // NPE fix
                if (clients[i] != null && Objects.equals(clients[i].name, target)) {
                    clients[i] = null;
                    success("Se ha eliminado el cliente");
                    break;
                }
        }

        public void modify() {
            if (getSize() == 0) {
                error("No hay clientes");
                return;
            }
            // Obtener el cliente a modificar
            final String target = (String) JOptionPane.showInputDialog(
                    null, "Seleccione el cliente a modificar", "Listado de Clientes",
                    JOptionPane.INFORMATION_MESSAGE, null, listNames(), null
            );
            if (target == null) return;
            // Modificar el cliente
            for (int i = 0; i < size; i++)
                // NPE fix
                if (clients[i] != null && Objects.equals(clients[i].name, target)) {
                    final boolean owes = JOptionPane.showConfirmDialog(
                            null, "¿El cliente debe?", "Modificar cliente",
                            JOptionPane.YES_NO_OPTION
                    ) == JOptionPane.YES_OPTION;
                    clients[i] = new Client(clients[i].name, clients[i].phone, clients[i].balance, owes); // Swap
                    success("Se ha modificado el cliente");
                    break;
                }
        }

        /**
         * Lee un cliente determinado
         */
        void read() {
            if (getSize() == 0) {
                error("No hay clientes");
                return;
            }
            // Obtener el cliente a mostrar
            final String target = (String) JOptionPane.showInputDialog(
                    null, "Seleccione el cliente a mostrar", "Listado de Clientes",
                    JOptionPane.INFORMATION_MESSAGE, null, listNames(), null
            );
            if (target == null) return;
            // Mostrar el cliente
            for (int i = 0; i < size; i++)
                // NPE fix
                if (clients[i] != null && Objects.equals(clients[i].name, target)) {
                    JOptionPane.showMessageDialog(null, clients[i], "Cliente", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
        }

        void read_all() {
            // Si no hay clientes
            if (getSize() == 0) {
                error("No hay clientes");
                return;
            }
            // Muestra los clientes
            JOptionPane.showMessageDialog(null, list(), "Listado de Clientes", JOptionPane.INFORMATION_MESSAGE);
        }

        /**
         * Crea un cliente en el arreglo
         */
        void create() {
            // Obtiene la siguiente posicion a utilizar en el arreglo
            final int next = getNext();
            // No hay mas espacio en el arreglo
            if (next == -1) {
                error("No se pueden crear mas clientes");
                return;
            }
            // Determina si se ha creado el cliente.
            boolean created = false;
            try {
                final String clientName = JOptionPane.showInputDialog("Introduzca el nombre del cliente");
                if (clientName == null) return;
                final String clientPhone = JOptionPane.showInputDialog("Introduzca el telefono del cliente");
                if (clientPhone == null) return;
                final double clientBalance = Double.parseDouble(JOptionPane.showInputDialog("Introduzca el saldo del cliente"));
                final boolean owes = JOptionPane.showConfirmDialog(null, "¿El cliente debe?", "Crear cliente", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                clients[next] = new Client(clientName, clientPhone, clientBalance, owes);
                created = true;
            } catch (NumberFormatException e) {
                error("Se debe proveer un valor numerico");
            } finally {
                if (created) success("Se ha creado el cliente");
            }
        }
    }
}
