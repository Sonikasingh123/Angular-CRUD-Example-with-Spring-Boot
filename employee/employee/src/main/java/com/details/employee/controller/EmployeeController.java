package com.details.employee.controller;

import com.details.employee.Exception.EmployeeNotFoundException;
import com.details.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.details.employee.model.Employee;

import java.util.List;

@RestController
@RequestMapping("/employee")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) throws EmployeeNotFoundException {
        Employee empp = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(empp, HttpStatus.OK);
    }
    @PostMapping("/create/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        System.out.println("IN Controller");
        Employee emp= employeeService.saveEmployee(employee);
        return new ResponseEntity<>(emp,HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getEmployees( ) throws Exception {
        List<Employee> empp = employeeService.findAll();
        return new ResponseEntity<>(empp, HttpStatus.OK);
    }

    @PutMapping("/update/employee/{id}")
    public ResponseEntity<Employee> updateEmployeeDetails(@PathVariable("id") String empId, @RequestBody Employee empObj){
        return employeeService.updateEmployeeDetails(empId, empObj);

    }

    @DeleteMapping("/delete/employee/{empId}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("empId") String empId) {
        return employeeService.deleteEmployee(empId);

    }

}
