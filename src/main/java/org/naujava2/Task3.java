package org.naujava2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Task3 {
    public static void main(String[] args) {


        try (FileOutputStream fos = new FileOutputStream("Task3Output.txt")){
            ArrayList<Employee> employees = new ArrayList<>();
            createEmployees(employees);
            employees.stream()
                    .sorted(Comparator.comparing(Employee::getSalary))
                    .forEach(e -> {
                        try {
                            fos.write((e + "\n").getBytes());
                        } catch (IOException ex) {
                            System.out.println(ex.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createEmployees(ArrayList<Employee> employees) {
        String[] names = {"John Doe", "Jane Doe", "Mary Doe", "Bob Doe", "John Smith"};
        int[] ages = { 45, 54, 19, 90, 76 };
        String[] departments = {"IT", "CEO", "IT", "Office", "Manager"};
        Double[] salaries = {12345.0, 20430.0, 12344.0, 44444.0, 50430.0};
        for (int i = 0; i < names.length; i++) {
            Employee employee = new Employee();
            employee.setFullName(names[i]);
            employee.setAge(ages[i]);
            employee.setDepartment(departments[i]);
            employee.setSalary(salaries[i]);
            employees.add(employee);
        }
    }
}
