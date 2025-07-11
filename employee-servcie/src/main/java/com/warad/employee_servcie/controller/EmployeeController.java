package com.warad.employee_servcie.controller;

import com.warad.employee_servcie.model.Employee;
import com.warad.employee_servcie.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/employee")
    public class EmployeeController {
        private static final Logger LOGGER
                = LoggerFactory.getLogger(EmployeeController.class);

        @Autowired
        EmployeeRepository repository;

        @PostMapping("/")
        public Employee add(@RequestBody Employee employee) {
            LOGGER.info("Employee add: {}", employee);
            return repository.add(employee);
        }
        @PostMapping("/addEmps")
        public List<Employee> addAll(@RequestBody List<Employee> employees) {
            LOGGER.info("Employees add: {}", employees);
            return repository.saveEmployees(employees);
        }

        @GetMapping
        public List<Employee> findAll() {
            LOGGER.info("Employee find");
            return repository.findAll();
        }

        @GetMapping("/{id}")
        public Employee findById(@PathVariable("id") Long id) {
            LOGGER.info("Employee find: id={}", id);
            return repository.findById(id);
        }

        @GetMapping("/json/{id}")
        public ResponseEntity<Employee> findByJsonId(@PathVariable("id") Long id) {
            LOGGER.info("Employee find: id={}", id);

            return new ResponseEntity(repository.findByJsonId(id), HttpStatus.OK);
        }

        @GetMapping("/department/{departmentId}")
        public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
            LOGGER.info("Employee find: departmentId={}", departmentId);
            return repository.findByDepartment(departmentId);
        }
}
