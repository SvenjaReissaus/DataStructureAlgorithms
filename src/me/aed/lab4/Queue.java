package me.aed.lab4;

import javax.swing.*;

public final class Queue {

    // Declaring the class variables.
    private int size;
    private int front = -1, rear = -1;

    // Declaring array list of integer type.
    private String[] queue;

    // Constructor
    Queue(final int size) {
        this.size = size;
        this.queue = new String[size];
    }

    // Method to insert a new element in the queue.
    public void enQueue() {

        // Condition if queue is full.
        if ((front == 0 && rear == size - 1) ||
                (rear == (front - 1) % (size - 1))) {
            JOptionPane.showMessageDialog(null, "La cola esta llena");
            return;
        }
        final String data = JOptionPane.showInputDialog("Ingrese los datos a imprimir");
        // condition for empty queue.
        if (front == -1) {
            front = 0;
            rear = 0;
            queue[rear] = data;
        } else if (rear == size - 1 && front != 0) {
            rear = 0;
            queue[rear] = data;
        } else {
            rear = (rear + 1);
            queue[rear] = data;
        }
    }

    // Function to dequeue an element
// form th queue.
    public String deQueue() {
        // Condition for empty queue.
        if (front == -1) {
            JOptionPane.showMessageDialog(null, "La cola esta vacia");

            // Return null in case of empty queue
            return null;
        }

        final String temp = queue[front];

        // Condition for only one element
        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (front == size - 1) {
            front = 0;
        } else {
            front = front + 1;
        }

        // Returns the dequeued element
        return temp;
    }

    // Method to display the elements of queue
    public void displayQueue() {

        // Condition for empty queue.
        if (front == -1) {
            JOptionPane.showMessageDialog(null, "La cola esta vacia");
            return;
        }

        // If rear has not crossed the max size
        // or queue rear is still greater then
        // front.
        JOptionPane.showMessageDialog(null, "Los elementos en cola son");

        if (rear >= front) {

            // Loop to print elements from
            // front to rear.
            final StringBuilder sb = new StringBuilder();
            for (int i = front; i <= rear; i++) sb.append(queue[i]).append(" ");
            JOptionPane.showMessageDialog(null, sb.toString());
        }

        // If rear crossed the max index and
        // indexing has started in loop
        else {

            // Loop for printing elements from
            // front to max size or last index
            for (int i = front; i < size; i++) JOptionPane.showMessageDialog(null, queue[i]);

            // Loop for printing elements from
            // 0th index till rear position
            for (int i = 0; i <= rear; i++) JOptionPane.showMessageDialog(null, queue[i]);

        }
    }
}
