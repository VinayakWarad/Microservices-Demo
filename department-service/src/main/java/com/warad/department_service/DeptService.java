package com.warad.department_service;

import com.warad.department_service.model.Employee;

import java.util.List;

public interface DeptService {
// Get
List<Employee> getEmployeesByDepartmentId(Long deptId);

    Employee saveEmp(Employee employee);
}
