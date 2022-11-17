package me.aed;

import javax.swing.*;

public final class ArrayFactory {
    private int[] array;
    private SortMethods method;

    public int getSize() {
        return array != null ? array.length : 0;
    }

    public void setSize(final int size) {
        if (array != null) return;
        array = new int[size];
    }

    public boolean fillArray() {
        if (array == null) return false;
        for (int i = 0; i < array.length; i++) {
            final int value = parse(JOptionPane.showInputDialog("Ingrese el valor para el elemento " + i));
            if (value == -1) return false;
            array[i] = value;
        }
        return true;
    }

    public int[] getArray() {
        return array;
    }

    public int search(final int value) {
        final int[] sorted = sort();
        int first = 0, last = sorted.length - 1;
        int mid = (first + last) / 2;
        while (first <= last) {
            if (sorted[mid] < value) first = mid + 1;
            else if (sorted[mid] == value) return mid;
            else last = mid - 1;
            mid = (first + last) / 2;
        }
        return Integer.MIN_VALUE;
    }

    public void setMethod(final SortMethods method) {
        this.method = method;
    }

    public boolean isInitialized() {
        return array != null;
    }

    private int parse(final String value) {
        try {
            return Integer.parseInt(value);
        } catch (final NumberFormatException e) {
            return -1;
        }
    }

    public int[] sort() {
        if (array == null) return new int[]{};
        if (method == null) this.method = SortMethods.BubbleSort;
        return switch (method) {
            case BubbleSort -> bubbleSort();
            case InsertSort -> insertionSort();
            case SelectSort -> selectionSort();
            case ShellSort -> shellSort();
            case ShakeSort -> shakeSort();
        };
    }

    public int[] bubbleSort() {
        final int[] temp = array.clone();
        for (int i = 0; i < temp.length - 1; i++)
            for (int j = 0; j < temp.length - 1; j++)
                if (array[j] > temp[j + 1]) {
                    final int swap = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = swap;
                }
        return temp;
    }

    public int[] selectionSort() {
        final int[] temp = array.clone();
        for (int i = 0; i < temp.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < temp.length; j++)
                if (temp[j] < temp[min]) min = j;
            final int swap = temp[i];
            temp[i] = temp[min];
            temp[min] = swap;
        }
        return temp;
    }

    public int[] insertionSort() {
        final int[] temp = array.clone();
        for (int i = 1; i < temp.length; i++) {
            final int value = temp[i];
            int j = i - 1;
            while (j >= 0 && temp[j] > value) {
                temp[j + 1] = temp[j];
                j--;
            }
            temp[j + 1] = value;
        }
        return temp;
    }

    public int[] shakeSort() {
        final int[] temp = array.clone();
        int left = 0, right = temp.length - 1;
        while (left < right) {
            for (int i = left; i < right; i++)
                if (temp[i] > temp[i + 1]) {
                    final int swap = temp[i];
                    temp[i] = temp[i + 1];
                    temp[i + 1] = swap;
                }
            right--;
            for (int i = right; i > left; i--)
                if (temp[i] < temp[i - 1]) {
                    final int swap = temp[i];
                    temp[i] = temp[i - 1];
                    temp[i - 1] = swap;
                }
            left++;
        }
        return temp;
    }

    public int[] shellSort() {
        final int[] temp = array.clone();
        for (int gap = temp.length / 2; gap > 0; gap /= 2)
            for (int i = gap; i < temp.length; i++)
                for (int j = i - gap; j >= 0 && temp[j] > temp[j + gap]; j -= gap) {
                    final int swap = temp[j];
                    temp[j] = temp[j + gap];
                    temp[j + gap] = swap;
                }
        return temp;
    }
}
