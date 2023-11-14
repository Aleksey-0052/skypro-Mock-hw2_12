package pro.sky.skyproMockhw2_12.service;

import org.springframework.stereotype.Service;
import pro.sky.skyproMockhw2_12.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyproMockhw2_12.exceptions.EmployeeNotFoundException;
import pro.sky.skyproMockhw2_12.exceptions.EmployeeStorageIsFullException;
import pro.sky.skyproMockhw2_12.exceptions.InvalidInputException;
import pro.sky.skyproMockhw2_12.model.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    private static final Integer MAX_EMPLOYEES = 10;


    @Override
    public Employee addEmployee(String firstName, String lastName, Integer salary, Integer departmentId) {
        validateInput(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, salary, departmentId);

        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Сотрудник с такими именем и фамилией уже существует");
        } else if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышен лимит сотрудников");
        }

        employees.put(employee.getFullName(), employee);
        return employees.get(employee.getFullName());
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);

        Employee employee = new Employee(firstName, lastName);

        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник с такими именем и фамилией не найден");

        }

        return employees.remove(employee.getFullName());
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);

        Employee employee = new Employee(firstName, lastName);

        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник с такими именем и фамилией не найден");
        }

        return employees.get(employee.getFullName());
    }

    @Override
    public Collection<Employee> findAll() {

        return employees.values();
    }

    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException("Имя или фамилия сотрудника содержат недопустимые символы");
        }
    }

    @Override
    public void resetMapForTest() {

        this.employees.clear();
    }
    
}
