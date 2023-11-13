package pro.sky.skyproMockhw2_12.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.skyproMockhw2_12.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    @Autowired
    public DepartmentServiceImpl(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeWithMinSalaryByDepartment(Integer departmentId) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .min(Comparator.comparingInt(employee -> employee.getSalary()))
                .orElseThrow(() -> new NoSuchElementException("Not found employee"));
    }

    @Override
    public Employee findEmployeeWithMaxSalaryByDepartment(Integer departmentId) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new NoSuchElementException("Not found employee"));
    }

    @Override
    public int calculateTotalSalaryByDepartment(Integer departmentId) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Map<Integer, List<Employee>> printEmployeesByDepartment(Integer departmentId) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .collect (Collectors.groupingBy(Employee::getDepartmentId));
    }

    @Override
    public Map<Integer, List<Employee>> printAllEmployees() {
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
