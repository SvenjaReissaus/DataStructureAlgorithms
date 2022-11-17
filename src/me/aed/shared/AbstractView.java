package me.aed.shared;

import me.aed.shared.annotations.Number;
import me.aed.shared.annotations.Required;
import me.aed.shared.annotations.StringLength;
import me.aed.shared.annotations.ValidationFor;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

public abstract class AbstractView extends JFrame {

    protected Icon editIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/edit.png")));
    protected Icon deleteIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/delete.png")));

    public AbstractView() {
        this(null, WindowConstants.EXIT_ON_CLOSE);
    }

    public AbstractView(final String title) {
        this(title, WindowConstants.EXIT_ON_CLOSE);
    }

    public AbstractView(final String title, int EXIT_ACTION) {
        setTitle(title);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ACTION);
        setLocationRelativeTo(null);
    }

    protected void addTooltip(final JButton source, final JLabel target, final String tooltip) {
        // grab maximum 30 characters from tooltip
        final String trimmed = tooltip.trim().subSequence(0, Math.min(tooltip.length(), 38)) + "...";
        source.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                target.setText(trimmed);
            }

            public void focusLost(FocusEvent evt) {
                target.setText("");
            }
        });
        source.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                target.setText(trimmed);
            }

            public void mouseExited(MouseEvent evt) {
                target.setText("");
            }
        });
    }

    protected boolean valid(Class<? extends AbstractView> clazz) {
        final HashMap<String, String> errors = new HashMap<>();
        int count = 0;
        for (final Field field : clazz.getDeclaredFields()) {
            //check if field is accessible
            if (!field.canAccess(this)) continue;
            try {
                // get field value
                if (!(field.get(this) instanceof final JComponent component)) continue;
                if (component instanceof final JTextField text) {
                    if (field.isAnnotationPresent(Required.class) && text.getText().isEmpty()) {
                        errors.put(field.getName(), "Este campo es requerido");
                        count++;
                    } else if (field.isAnnotationPresent(StringLength.class))
                        if (text.getText().length() < field.getAnnotation(StringLength.class).min()) {
                            errors.put(field.getName(), "Este campo debe tener al menos " + field.getAnnotation(StringLength.class).min() + " caracteres");
                            count++;
                        } else if (text.getText().length() > field.getAnnotation(StringLength.class).max()) {
                            errors.put(field.getName(), "Este campo debe tener como máximo " + field.getAnnotation(StringLength.class).max() + " caracteres");
                            count++;
                        } else if (field.isAnnotationPresent(Number.class))
                            if (!isNumber(text.getText())) {
                                errors.put(field.getName(), "Este campo debe ser un número");
                                count++;
                            } else if (Integer.parseInt(text.getText()) < field.getAnnotation(Number.class).min()) {
                                errors.put(field.getName(), "Este campo debe ser mayor o igual a " + field.getAnnotation(Number.class).min());
                                count++;
                            } else if (Integer.parseInt(text.getText()) > field.getAnnotation(Number.class).max()) {
                                errors.put(field.getName(), "Este campo debe ser menor o igual a " + field.getAnnotation(Number.class).max());
                                count++;
                            }
                } else if (component instanceof final JLabel label && field.isAnnotationPresent(ValidationFor.class))
                    label.setText(errors.getOrDefault(field.getAnnotation(ValidationFor.class).name(), ""));
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return count == 0;
    }

    protected boolean isNumber(final String source) {
        try {
            Integer.parseInt(source);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }
}
