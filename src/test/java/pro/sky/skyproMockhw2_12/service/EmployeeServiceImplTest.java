package pro.sky.skyproMockhw2_12.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.skyproMockhw2_12.exceptions.EmployeeAlreadyAddedException;
import pro.sky.skyproMockhw2_12.exceptions.EmployeeNotFoundException;
import pro.sky.skyproMockhw2_12.exceptions.EmployeeStorageIsFullException;
import pro.sky.skyproMockhw2_12.exceptions.InvalidInputException;
import pro.sky.skyproMockhw2_12.model.Employee;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {

         employeeService.resetMapForTest();
    }

    @Test
    void addEmployee_CorrectParameters_Success() {

        //Employee expected = new Employee("Иван", "Иванов", 50000, 1);
        String expectedFirstName = "Иван";
        String expectedLastName = "Иванов";
        Integer expectedSalary = 50000;
        Integer expectedDepartmentId = 1;

        Employee actual = employeeService.addEmployee(expectedFirstName, expectedLastName, expectedSalary,expectedDepartmentId);

        assertEquals(expectedFirstName, actual.getFirstName());
        assertEquals(expectedLastName, actual.getLastName());
        assertEquals(expectedSalary, actual.getSalary());
        assertEquals(expectedDepartmentId, actual.getDepartmentId());

    }

    @Test
    void addEmployee_IncorrectParameters_ThrowsInvalidInputException() {

        assertThrows(InvalidInputException.class, () -> {
            employeeService.addEmployee("Алек2сей", "Алексеев", 50000, 2);

        });

        assertThrows(InvalidInputException.class, () -> {
            employeeService.addEmployee("Алексей", "Алек+сеев", 50000, 2);
        });
    }

    @Test
    void addEmployee_EmployeeAlreadyAdded_ThrowsEmployeeAlreadyAddedException() {

        String expectedFirstName = "Иван";
        String expectedLastName = "Иванов";
        Integer expectedSalary = 50000;
        Integer expectedDepartmentId = 1;

        employeeService.addEmployee(expectedFirstName, expectedLastName, expectedSalary,expectedDepartmentId);

        assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.addEmployee(expectedFirstName, expectedLastName, expectedSalary, expectedDepartmentId);
        });
        
    }

    @Test
    void addEmployee_EmployeeStorageIsFull_ThrowsEmployeeStorageIsFullException() {

        employeeService.addEmployee("Иван", "Иванов", 50000, 1);
        employeeService.addEmployee("Петр", "Петров", 60000, 1);
        employeeService.addEmployee("Борис", "Борисов", 70000, 2);
        employeeService.addEmployee("Захар", "Захаров", 80000, 2);
        employeeService.addEmployee("Павел", "Павлов", 90000, 3);
        employeeService.addEmployee("Алексей", "Алексеев", 100000, 3);
        employeeService.addEmployee("Александр", "Александров", 110000, 4);
        employeeService.addEmployee("Григорий", "Григорьев", 120000, 4);
        employeeService.addEmployee("Макар", "Макаров", 130000, 5);
        employeeService.addEmployee("Сергей", "Сергеев", 140000, 5);

        assertThrows(EmployeeStorageIsFullException.class, () -> {
            employeeService.addEmployee("Дмитрий", "Дмитриев", 150000, 5);
        });
    }
    
    @Test
    void findEmployee_CorrectParameters_Success() {

        String expectedFirstName = "Иван";
        String expectedLastName = "Иванов";
        Integer expectedSalary = 50000;
        Integer expectedDepartmentId = 1;
        employeeService.addEmployee(expectedFirstName, expectedLastName, expectedSalary,expectedDepartmentId);

        Employee actual = employeeService.findEmployee(expectedFirstName, expectedLastName);

        assertEquals(expectedFirstName, actual.getFirstName());
        assertEquals(expectedLastName, actual.getLastName());
        assertEquals(expectedSalary, actual.getSalary());
        assertEquals(expectedDepartmentId, actual.getDepartmentId());

    }

    @Test
    void findEmployee_IncorrectParameters_ThrowsInvalidInputException() {

        assertThrows(InvalidInputException.class, () -> {
            employeeService.findEmployee("Ма3ар", "Макаров");

        });

        assertThrows(InvalidInputException.class, () -> {
            employeeService.findEmployee("Макар", "Мак=ров");
        });
    }

    @Test
    void findEmployee_EmployeeNotFound_ThrowsEmployeeNotFoundException() {

        String expectedFirstName = "Борис";
        String expectedLastName = "Борисов";
        Integer expectedSalary = 70000;
        Integer expectedDepartmentId = 2;

        employeeService.addEmployee(expectedFirstName, expectedLastName, expectedSalary,expectedDepartmentId);

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.findEmployee("Петр", "Петров");
        });

    }

    @Test
    void removeEmployee_CorrectParameters_Success() {

        String expectedFirstName = "Павел";
        String expectedLastName = "Павлов";
        Integer expectedSalary = 100000;
        Integer expectedDepartmentId = 5;
        employeeService.addEmployee(expectedFirstName, expectedLastName, expectedSalary,expectedDepartmentId);

        Employee actual = employeeService.removeEmployee(expectedFirstName, expectedLastName);

        assertEquals(expectedFirstName, actual.getFirstName());
        assertEquals(expectedLastName, actual.getLastName());
        assertEquals(expectedSalary, actual.getSalary());
        assertEquals(expectedDepartmentId, actual.getDepartmentId());

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.findEmployee(expectedFirstName, expectedLastName);
        });

    }

    @Test
    void removeEmployee_IncorrectParameters_ThrowsInvalidInputException() {

        assertThrows(InvalidInputException.class, () -> {
            employeeService.removeEmployee("Борис!", "Борисов");

        });

        assertThrows(InvalidInputException.class, () -> {
            employeeService.removeEmployee("Борис", "Борисов_");
        });
    }

    @Test
    void  removeEmployee_EmployeeNotFound_ThrowsEmployeeNotFoundException() {

        String expectedFirstName = "Захар";
        String expectedLastName = "Захаров";
        Integer expectedSalary = 90000;
        Integer expectedDepartmentId = 4;

        employeeService.addEmployee(expectedFirstName, expectedLastName, expectedSalary,expectedDepartmentId);

        assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.removeEmployee("Павел", "Павлов");
        });

    }

    @Test
    void findAll() {

        Employee employee1 = new Employee("Иван", "Иванов", 50000, 1);
        Employee employee2 = new Employee("Петр", "Петров", 60000, 2);
        Employee employee3 = new Employee("Борис", "Борисов", 70000, 3);

        employeeService.addEmployee(
                employee1.getFirstName(),
                employee1.getLastName(),
                employee1.getSalary(),
                employee1.getDepartmentId()
        );

        employeeService.addEmployee(
                employee2.getFirstName(),
                employee2.getLastName(),
                employee2.getSalary(),
                employee2.getDepartmentId()
        );

        employeeService.addEmployee(
                employee3.getFirstName(),
                employee3.getLastName(),
                employee3.getSalary(),
                employee3.getDepartmentId()
        );

        List<Employee> expected = new ArrayList<>();
        // В переменную expected помещаем ожидаемый результат работы метода

        expected.add(employee1);
        expected.add(employee2);
        expected.add(employee3);

        Collection<Employee> actual = employeeService.findAll();
        // В переменную actual типа List помещаем действительный результат работы тестируемого метода findAll()

        assertEquals(expected.size(), actual.size());

        assertTrue(actual.containsAll(expected));
        // Для сравнения используем статический метод assertTrue() класса Assert
        // который принимает два параметра и выдает ошибку сравнения, если результаты неэквивалентны
        // иначе программа проходит тест
    
    }
    
}