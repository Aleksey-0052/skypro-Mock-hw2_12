package pro.sky.skyproMockhw2_12.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.skyproMockhw2_12.model.Employee;
import pro.sky.skyproMockhw2_12.service.EmployeeService;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }
    @GetMapping(path = "/add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName,
                        @RequestParam Integer salary, @RequestParam Integer departmentId) {
        return employeeService.addEmployee(firstName, lastName, salary, departmentId);
    }
    @GetMapping(path = "/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }
    @GetMapping(path = "/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeService.removeEmployee(firstName, lastName);
    }
    @GetMapping
    public Collection<Employee> findAll() {

        return employeeService.findAll();
    }


}
