package com.employee.employeeAPI.services;

import com.employee.employeeAPI.modal.Employee;
import com.employee.employeeAPI.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository repo;

    EmployeeService(EmployeeRepository repo){
        this.repo = repo;
    }

    public List<Employee> viewAll(){
        return repo.findAll();
    }

    public Employee findEmployeeById(Long id){
        return repo.findById(id).orElse(null);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee){
        Optional<Employee> existingEmployeeOptional;
        existingEmployeeOptional = repo.findById(id);

        if (existingEmployeeOptional.isPresent()) {
            Employee existingEmployee = existingEmployeeOptional.get();

            // Update the fields you want to change
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            // Update other fields as needed

            // Save the updated employee
            return repo.save(existingEmployee);
        } else {
//             Handle the case when the employee with the given ID is not found
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    public void deleteEmployee(Long id) {
        Optional<Employee> existingEmployeeOptional = repo.findById(id);

        if (existingEmployeeOptional.isPresent()) {
            // Delete the employee by ID
            repo.deleteById(id);
        } else {
            // Handle the case when the employee with the given ID is not found
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    public Employee createEmployee(Employee employee){
        return repo.save(employee);
    }
}
