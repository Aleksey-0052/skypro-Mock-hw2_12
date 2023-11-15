package pro.sky.skyproMockhw2_12.service;

import pro.sky.skyproMockhw2_12.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee findEmployeeWithMinSalaryByDepartment(Integer departmentId);

    Employee findEmployeeWithMaxSalaryByDepartment(Integer departmentId);

    int calculateTotalSalaryByDepartment(Integer departmentId);

    Map<Integer, List<Employee>> printEmployeesByDepartment(Integer departmentId);

    Map<Integer, List<Employee>> printAllEmployees();
}
