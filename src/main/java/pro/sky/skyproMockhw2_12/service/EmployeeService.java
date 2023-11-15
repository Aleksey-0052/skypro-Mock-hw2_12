package pro.sky.skyproMockhw2_12.service;

import pro.sky.skyproMockhw2_12.model.Employee;

import java.util.Collection;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, Integer salary, Integer departmentId);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> findAll();

    void resetMapForTest();


}
