package com.employee.employeeAPI.controller;

import com.employee.employeeAPI.modal.Employee;
import com.employee.employeeAPI.services.EmployeeService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EmployeeController {
    private final EmployeeService service;

    EmployeeController(EmployeeService service){
        this.service = service;
    }

    @PostMapping("/employee")
    Employee createEmployee(@RequestBody Employee employee){
        return service.createEmployee(employee);
    }
    @GetMapping("/employee")
    List<Employee> viewAll(){
        return service.viewAll();
    }

    @GetMapping("/employee/{id}")
    Employee viewEmployee(@PathVariable Long id){
        return service.findEmployeeById(id);
    }

    @PutMapping("/employee/{id}")y
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return service.updateEmployee(id, updatedEmployee);
    }

    @DeleteMapping("/employee/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
    }

}
