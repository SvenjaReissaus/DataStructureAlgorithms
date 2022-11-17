package me.aed.lab4;

import java.util.Stack;

public final class Notations {
    private StringBuilder convert(final String source, final boolean prefix) {
        final StringBuilder result = new StringBuilder();
        final Stack<Character> stack = new Stack<>();
        final char[] charsExp = source.toCharArray();
        if (prefix)
            for (int i = 0; i < charsExp.length; i++) {
                if (charsExp[i] == '(') {
                    charsExp[i] = ')';
                    i++;
                } else if (charsExp[i] == ')') {
                    charsExp[i] = '(';
                    i++;
                }
            }
        for (char c : charsExp) {

            //check if char is operator or operand
            if (precedence(c) > 0) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) result.append(stack.pop());
                stack.push(c);
            } else if (c == ')') {
                char x = stack.pop();
                while (x != '(') {
                    result.append(x);
                    x = stack.pop();
                }
            } else if (c == '(') stack.push(c);
            else result.append(c);
        }

        for (int i = 0; i <= stack.size(); i++) result.append(stack.pop());
        return result;
    }

    private int precedence(char c) {
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> -1;
        };
    }

    public StringBuilder toPostfix(final String expression) {
        return convert(expression, false);
    }

    public StringBuilder toPrefix(final String expression) {
        return convert(new StringBuilder(expression).reverse().toString(), true).reverse();
    }
}
