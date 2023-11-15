package pro.sky.skyproMockhw2_12.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.skyproMockhw2_12.model.Employee;
import pro.sky.skyproMockhw2_12.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {

        this.departmentService = departmentService;
    }
    @GetMapping(path = "/{id}/salary/min")
    public Employee findEmployeeWithMinSalaryByDepartment(@PathVariable (value = "id") Integer departmentId) {
        return departmentService.findEmployeeWithMinSalaryByDepartment(departmentId);
    }
    @GetMapping(path = "/{id}/salary/max")
    public Employee findEmployeeWithMaxSalaryByDepartment(@PathVariable ("id") Integer departmentId) {
        return departmentService.findEmployeeWithMaxSalaryByDepartment(departmentId);
    }
    @GetMapping(path = "/{id}/salary/sum")
    public int calculateTotalSalaryByDepartment(@PathVariable ("id") Integer departmentId) {
        return departmentService.calculateTotalSalaryByDepartment(departmentId);
    }

    @GetMapping(path = "/{id}/employees")
    public Map<Integer, List<Employee>> employees(@PathVariable(value = "id") Integer departmentId) {
        return departmentService.printEmployeesByDepartment(departmentId);
    }
    @GetMapping(path = "/employees")
    public Map<Integer, List<Employee>> employees() {

        return departmentService.printAllEmployees();
    }


}
