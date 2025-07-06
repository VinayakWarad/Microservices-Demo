package com.warad.department_service.controller;

import com.warad.department_service.DeptService;
import com.warad.department_service.client.WebClientConfig;
import com.warad.department_service.model.Department;
import com.warad.department_service.model.Employee;
import com.warad.department_service.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private static final Logger LOGGER
            = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentRepository repository;

    @Autowired
    DeptService deptService;

    @PostMapping
    public Department add(@RequestBody Department department) {
        LOGGER.info("Department add: {}", department);
        return repository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll() {
        LOGGER.info("Department find");
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        LOGGER.info("Department find: id={}", id);
        return repository.findById(id);
    }

/*
 Example 1 : GET
 From this dept service webclient call to emp service
 Get all the employees working in specific department
 pass deprtmentId as param
*/
    @GetMapping("/emps/{departmentId}")
    public List<Employee> empsByDeptId(@PathVariable Long departmentId) {
        List<Employee> list = deptService.getEmployeesByDepartmentId(departmentId);
        return list;
    }

/* Example 2 : POST
 From this dept service webclient call to emp service
 provide EMployee record to save Employee in Employee service*/
    @PostMapping("/emp")
    public Employee saveEmployee(@RequestBody Employee employee) {
        Employee emp = deptService.saveEmp(employee);
        return emp;
    }

    @GetMapping("/emp/{id}")
    public Employee getJsonEmployee(@PathVariable Long id) {
        System.out.println("Emp Id entered"+id);
        Employee empJsonStr = deptService.getEmpJson(id);
        return empJsonStr;
    }

}
