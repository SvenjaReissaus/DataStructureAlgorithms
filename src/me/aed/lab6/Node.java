package me.aed.lab6;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public final class Node<T> {
    private T data;
    private Node<T> left;
    private Node<T> right;
    private JButton addLeft = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/add.png"))));;
    private JButton addRight = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/add.png"))));
    private final Node<T> parent;

    public Node(final T data, final Node<T> parent) {
        this.data = data;
        this.parent = parent;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public T get() {
        return data;
    }

    public void set(final T data) {
        this.data = data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setLeft(final Node<T> node) {
        left = node;
        addLeft.setText((String) node.get());
        addLeft.setIcon(null);
        addLeft.setContentAreaFilled(false);
        addLeft.setBorderPainted(false);
        addLeft.setFocusPainted(false);
    }

    public void setRight(final Node<T> node) {
        right = node;
        addRight.setText((String) node.get());
        addRight.setIcon(null);
        addRight.setContentAreaFilled(false);
        addRight.setBorderPainted(false);
        addRight.setFocusPainted(false);
    }
    public JPanel render() {
        final JPanel panel = new JPanel(new GridLayout(1, 1));
        final JPanel actions = new JPanel(new GridLayout(1, 2));
        addLeft.setHorizontalAlignment(SwingConstants.CENTER);
        addRight.setHorizontalAlignment(SwingConstants.CENTER);
        final JPanel leftPanel = new JPanel(new GridLayout(1, 1));
        final JPanel rightPanel = new JPanel(new GridLayout(1, 1));
        leftPanel.add(addLeft);
        rightPanel.add(addRight);
        actions.add(leftPanel);
        actions.add(rightPanel);
        panel.add(actions);
        addLeft.addActionListener(e -> {
            if (left != null) return;
            final T input = (T) JOptionPane.showInputDialog("Ingrese el valor del nodo");
            if (input == null) return;
            setLeft(new Node<>(input,  this));
            leftPanel.setLayout(new GridLayout(leftPanel.getComponentCount() + 1, 1));
            leftPanel.add(left.render());
        });
        addRight.addActionListener(e -> {
            if (right != null) return;
            final T input = (T) JOptionPane.showInputDialog("Ingrese el valor del nodo");
            if (input == null) return;
            setRight(new Node<>(input, this));
            rightPanel.setLayout(new GridLayout(rightPanel.getComponentCount() + 1, 1));
            rightPanel.add(right.render());
        });
        return panel;
    }
}
