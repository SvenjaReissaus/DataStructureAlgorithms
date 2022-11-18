package me.aed.lab6;

import me.aed.shared.AbstractView;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashSet;
import java.util.Objects;

public final class Node<T> {
    private final T data;
    private Node<T> left;
    private Node<T> right;
    private final JButton addLeft = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/add.png"))));
    private final JButton addRight = new JButton(new ImageIcon(Objects.requireNonNull(getClass().getResource("/add.png"))));
    private final Dimension buttonSize = new Dimension(100, 30);

    public Node(final T data) {
        this.data = data;
    }

    public LinkedHashSet<T> traverse(final TraverseOrder order) {
        final LinkedHashSet<T> values = new LinkedHashSet<>();
        inOrderRecursive(order, values); // node@0x00000000
        return values;
    }

    private void inOrderRecursive(final TraverseOrder order, final LinkedHashSet<T> set) {
        if (order.equals(TraverseOrder.PRE_ORDER)) set.add(data);
        if (left != null) left.inOrderRecursive(order, set);
        if (order.equals(TraverseOrder.IN_ORDER)) set.add(data);
        if (right != null) right.inOrderRecursive(order, set);
        if (order.equals(TraverseOrder.POST_ORDER)) set.add(data);
    }

    public T get() {
        return data;
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
    public JPanel render(final AbstractView view) {
        addLeft.setHorizontalAlignment(SwingConstants.CENTER);
        addRight.setHorizontalAlignment(SwingConstants.CENTER);
        addLeft.setMaximumSize(buttonSize);
        addLeft.setSize(buttonSize);
        addRight.setMaximumSize(buttonSize);
        addRight.setSize(buttonSize);
        final JPanel panel = new JPanel(new GridLayout(1, 1));
        final JPanel actions = new JPanel(new GridLayout(1, 2));
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
            setLeft(new Node<>(input));
            leftPanel.setLayout(new GridLayout(leftPanel.getComponentCount() + 1, 1));
            leftPanel.add(left.render(view));
        });
        addRight.addActionListener(e -> {
            if (right != null) return;
            final T input = (T) JOptionPane.showInputDialog("Ingrese el valor del nodo");
            if (input == null) return;
            setRight(new Node<>(input));
            rightPanel.setLayout(new GridLayout(rightPanel.getComponentCount() + 1, 1));
            rightPanel.add(right.render(view));
        });
        view.pack();
        return panel;
    }
}
