package com.details.employee.service;

import com.details.employee.Exception.EmployeeNotFoundException;
import com.details.employee.model.Employee;
import com.details.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
        Optional<Employee>  employee=employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
       }
       else{
            throw new EmployeeNotFoundException("Employee not found with id: "+id);
       }
    }
    public  Employee saveEmployee(Employee emp){
        System.out.println("IN Service");
        return  employeeRepository.save(emp);
    }

 public List<Employee> findAll(){
        return  employeeRepository.findAll();
 }

 //employeeService.updateEmployeeDetails(empId, empObj);

    public ResponseEntity<Employee> updateEmployeeDetails(String id, Employee employee) {
        Optional<Employee> empData = employeeRepository.findById(Integer.valueOf(id));
        if (empData.isPresent()) {
            Employee emp1 = empData.get();
            emp1.setFirstName(employee.getFirstName());
            emp1.setLastName(employee.getLastName());
            emp1.setEmailId(employee.getEmailId());

            return new ResponseEntity<>(employeeRepository.save(emp1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteEmployee(String id) {
        try {
            System.out.println("inside delete=====>>");
            employeeRepository.deleteById(Integer.valueOf(id));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
