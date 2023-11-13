package pro.sky.skyproMockhw2_12.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.skyproMockhw2_12.model.Employee;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;
    private Employee employee5;
    private Employee employee6;
    private Employee employee7;
    private Employee employee8;
    private Employee employee9;
    private Employee employee10;
    private Employee employee11;
    private Employee employee12;


    @BeforeEach
    public void setUp() {

         employee1 = new Employee("Иван", "Иванов", 50000, 1);
         employee2 = new Employee("Петр", "Петров", 60000, 1);
         employee3 = new Employee("Борис", "Борисов", 70000, 2);
         employee4 = new Employee("Захар", "Захаров", 80000, 2);
         employee5 = new Employee("Павел", "Павлов", 90000, 3);
         employee6 = new Employee("Алексей", "Алексеев", 100000, 3);
         employee7 = new Employee("Александр", "Александров", 110000, 4);
         employee8 = new Employee("Григорий", "Григорьев", 120000, 4);
         employee9 = new Employee("Макар", "Макаров", 130000, 5);
         employee10 = new Employee("Сергей", "Сергеев", 140000, 5);
         employee11 = new Employee("Дмитрий", "Дмитриев", 150000, 5);
         employee12 = new Employee("Владимир", "Владимиров", 160000, 4);


        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
        employeeService.addEmployee(employee3);


    }

    @Test
    void addEmployee() {

    }

    @Test
    void removeEmployee() {
    }

    @Test
    void findEmployee() {

    }

    @Test
    void findAll() {

        List<Employee> actual = employeeService.findAll();
        // В переменную actual типа List помещаем действительный результат работы тестируемого метода findAll()

        List<Employee> expected = new ArrayList<Employee>();
        // В переменную expected помещаем ожидаемый результат работы метода

        expected.add(employee1);
        expected.add(employee2);
        expected.add(employee3);

        assertEquals(expected, actual);
        // Для сравнения используем статический метод assertEquals() класса Assert
        // который принимает два параметра и выдает ошибку сравнения, если результаты неэквивалентны
        // иначе программа проходит тест
    
    }





}