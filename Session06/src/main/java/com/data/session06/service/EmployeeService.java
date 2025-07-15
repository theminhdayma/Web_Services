package com.data.session06.service;

import com.data.session06.entity.Employee;
import com.data.session06.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    Employee updateEmployee(Long id, Employee employee) {
        if (employeeRepository.existsById(id)) {
            employee.setId(id);
            return employeeRepository.save(employee);
        }
        return null;
    }
}
