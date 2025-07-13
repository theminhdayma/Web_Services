package com.data.session03.service.impl;

    import com.data.session03.entity.Employee;
import com.data.session03.rep.EmployeeRepository;
import com.data.session03.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteEmployeeById(Long id) {
        try {
            employeeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean saveEmployee(Employee employee) {
        try {
            employeeRepository.save(employee);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        try {
            if (employeeRepository.existsById(employee.getId())) {
                employeeRepository.save(employee);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Employee> findEmployeeByPhoneNumber(String phoneNumber) {
        Employee employee = employeeRepository.findEmployeeByPhoneNumber(phoneNumber);
        return employee != null ? List.of(employee) : List.of();
    }

    @Override
    public List<Employee> findEmployeesBySalaryGreaterThan(double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    @Override
    public Page<Employee> getEmployeesWithPaging(int page, int size) {
        return employeeRepository.findAll(PageRequest.of(page, size));
    }
}
