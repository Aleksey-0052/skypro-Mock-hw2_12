package pro.sky.skyproMockhw2_12.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sky.skyproMockhw2_12.exceptions.EmployeeNotFoundException;
import pro.sky.skyproMockhw2_12.model.Employee;

import java.util.*;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

//@ContextConfiguration(classes = {DepartmentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;


    //@Autowired
    //private DepartmentServiceImpl departmentService;

    //@MockBean
    //private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {

        employeeService.resetMapForTest();
    }

    @Test
    public void testFindEmployeeWithMinSalaryByDepartment_Success() {

        Employee employee1 = new Employee("Иван", "Иванов", 50000, 2);
        Employee employee2 = new Employee("Петр", "Петров", 60000, 1);
        Employee employee3 = new Employee("Борис", "Борисов", 70000, 1);

        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);
        expected.add(employee2);
        expected.add(employee3);

        Mockito.when(employeeService.findAll())
                .thenReturn(expected);
        Employee actual = departmentService.findEmployeeWithMinSalaryByDepartment(1);

        assertNotNull(actual);
        assertEquals(employee2, actual);

        verify(employeeService, times(1)).findAll();

    }

    @Test
    public void testFindEmployeeWithMinSalaryByDepartment_EmployeeNotFoundException() {

        List<Employee> expected = new ArrayList<>();
        Employee employee1 = new Employee("Иван", "Иванов", 50000, 2);
        Employee employee2 = new Employee("Петр", "Петров", 60000, 2);
        Employee employee3 = new Employee("Борис", "Борисов", 70000, 3);

        //Mockito.when(employeeService.findAll(any()))
                //.thenThrow(EmployeeNotFoundException.class);

        assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.findEmployeeWithMinSalaryByDepartment(1));

    }
    
    @Test
    public void testFindEmployeeWithMaxSalaryByDepartment_Success() {

        Employee employee1 = new Employee("Иван", "Иванов", 50000, 1);
        Employee employee2 = new Employee("Петр", "Петров", 60000, 1);
        Employee employee3 = new Employee("Борис", "Борисов", 70000, 1);

        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);
        expected.add(employee2);
        expected.add(employee3);

        Mockito.when(employeeService.findAll())
                .thenReturn(expected);
        Employee actual = departmentService.findEmployeeWithMaxSalaryByDepartment(1);

        assertNotNull(actual);
        assertEquals(employee3, actual);

        verify(employeeService, times(1)).findAll();

    }

    @Test
    public void testFindEmployeeWithMaxSalaryByDepartment_EmployeeNotFoundException() {

        List<Employee> expected = new ArrayList<>();
        Employee employee1 = new Employee("Иван", "Иванов", 50000, 2);
        Employee employee2 = new Employee("Петр", "Петров", 60000, 2);
        Employee employee3 = new Employee("Борис", "Борисов", 70000, 3);

        //Mockito.when(employeeService.findAll(any()))
        //.thenThrow(EmployeeNotFoundException.class);


        assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.findEmployeeWithMaxSalaryByDepartment(2));

    }
    @Test
    public void testCalculateTotalSalaryByDepartment_Success() {

        Employee employee1 = new Employee("Иван", "Иванов", 50000, 1);
        Employee employee2 = new Employee("Петр", "Петров", 60000, 1);
        Employee employee3 = new Employee("Борис", "Борисов", 70000, 1);

        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);
        expected.add(employee2);
        expected.add(employee3);

        Mockito.when(employeeService.findAll())
                .thenReturn(expected);
        int actual = departmentService.calculateTotalSalaryByDepartment(1);
        assertEquals(180000, actual);

        verify(employeeService, times(1)).findAll();

    }

    @Test
    public void testPrintAllEmployees_Success() {

        Employee employee1 = new Employee("Иван", "Иванов", 50000, 1);
        Employee employee2 = new Employee("Петр", "Петров", 60000, 2);
        Employee employee3 = new Employee("Борис", "Борисов", 70000, 3);

        List<Employee> expected1 = Arrays.asList(employee1);
        List<Employee> expected2 = Arrays.asList(employee2);
        List<Employee> expected3 = Arrays.asList(employee3);

        Mockito.when(employeeService.findAll())
                .thenReturn(Arrays.asList(employee1, employee2, employee3));

        Map<Integer, List<Employee>> expected = Map.of(1, expected1, 2, expected2, 3, expected3);

        Map<Integer, List<Employee>> actual = departmentService.printAllEmployees();

        assertEquals(3, actual.size());
        assertEquals(expected, actual);

    }

    @Test
    public void testPrintEmployeesByDepartment_Success() {

        int departmentId = 1;

        Employee employee1 = new Employee("Иван", "Иванов", 50000, departmentId);
        Employee employee2 = new Employee("Петр", "Петров", 60000, departmentId);
        Employee employee3 = new Employee("Борис", "Борисов", 70000, departmentId);

        List<Employee> expected = Arrays.asList(employee1, employee2, employee3);

        Mockito.when(employeeService.findAll())
                .thenReturn(expected);

        Map<Integer, List<Employee>> actual = departmentService.printEmployeesByDepartment(1);

        assertEquals(1, actual.size());             // проверка, что только один отдел

        assertEquals(expected, actual.get(departmentId));    // проверка, что список сотрудников совпадает с ожидаемым


    }

    
}