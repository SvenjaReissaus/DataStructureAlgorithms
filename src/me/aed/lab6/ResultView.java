package me.aed.lab6;

import me.aed.shared.AbstractView;

import javax.swing.*;
import java.util.LinkedHashSet;

public final class ResultView<T> extends AbstractView {
    private JPanel contentPane;
    private JTextField postOrder;
    private JTextField inOrder;
    private JTextField preOrder;

    public ResultView(final Node<T> root) {
        super("Laboratorio 6", WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        pack();

        final LinkedHashSet<T> preOrderValues = root.traverse(TraverseOrder.PRE_ORDER);
        preOrder.setText(preOrderValues.toString());
        final LinkedHashSet<T> inOrderValues = root.traverse(TraverseOrder.IN_ORDER);
        inOrder.setText(inOrderValues.toString());
        final LinkedHashSet<T> postOrderValues = root.traverse(TraverseOrder.POST_ORDER);
        postOrder.setText(postOrderValues.toString());
    }
}
