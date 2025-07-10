package com.data.session03.service;

import com.data.session03.entity.Employee;
import org.springframework.data.domain.Page;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    boolean deleteEmployeeById(Long id);
    boolean saveEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    List<Employee> findEmployeeByPhoneNumber(String phoneNumber);
    List<Employee> findEmployeesBySalaryGreaterThan(double salary);
    Page<Employee> getEmployeesWithPaging(int page, int size);
}
