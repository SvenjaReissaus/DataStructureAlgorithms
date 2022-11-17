package me.aed.shared;

import javax.swing.*;

public final class Strings {

    /**
     * Muestra un mensaje de error
     *
     * @param message El mensaje a mostrar
     */
    public static void error(final String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje informatico
     *
     * @param message El mensaje a mostrar
     */
    public static void success(final String message) {
        JOptionPane.showMessageDialog(null, message, "Exito!", JOptionPane.INFORMATION_MESSAGE);
    }

    public static int parseInt() {
        return parseInt("Ingrese un numero");
    }

    public static int parseInt(final String message) {
        try {
            return Integer.parseInt(JOptionPane.showInputDialog(message));
        } catch (final NumberFormatException e) {
            return parseInt(message);
        }
    }
}
