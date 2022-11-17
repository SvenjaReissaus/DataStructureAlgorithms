package me.aed.lab5;

public final class EmployeeListFactory extends ArrayListFactory<Employee> {
    public double calculatePayroll() {
        return list.stream().map(Employee::salary).reduce(0D, Double::sum);
    }
}
