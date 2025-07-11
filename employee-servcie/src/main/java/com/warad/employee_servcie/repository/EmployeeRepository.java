package com.warad.employee_servcie.repository;

import com.warad.employee_servcie.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepository {
    private List<Employee> employees
            = new ArrayList<>();

    public Employee add(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public List<Employee> saveEmployees(List<Employee> emp){
        employees.addAll(emp);
       return emp;
    }

    public Employee findById(Long id) {
        return employees.stream()
                .filter(a -> a.id().equals(id))
                .findFirst()
                .orElseThrow();
    }
    public Employee findByJsonId(Long id) {
        Employee emps=employees.stream()
                .filter(a -> a.id().equals(id))
                .findFirst().orElseThrow();
        return emps;

    }

    public List<Employee> findAll() {
        return employees;
    }

    public List<Employee> findByDepartment(Long departmentId) {
        return employees.stream()
                .filter(a -> a.departmentId().equals(departmentId))
                .toList();
    }
}
