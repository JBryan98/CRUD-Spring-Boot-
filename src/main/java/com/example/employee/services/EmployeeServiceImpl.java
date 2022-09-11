package com.example.employee.services;

import com.example.employee.entity.EmployeeEntitiy;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        EmployeeEntitiy employeeEntitiy = new EmployeeEntitiy();
        BeanUtils.copyProperties(employee, employeeEntitiy);
        employeeRepository.save(employeeEntitiy);

        return employee;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<EmployeeEntitiy> employeeEntitiys = employeeRepository.findAll();
        List<Employee> employees = employeeEntitiys.stream().map(employee -> new Employee(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmailId()
        )).toList();
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmployeeEntitiy employee = employeeRepository.findById(id).get();
        employeeRepository.delete(employee);
        return true;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EmployeeEntitiy employeeEntitiy = employeeRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntitiy, employee);
        return employee;
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        EmployeeEntitiy employeeEntitiy = employeeRepository.findById(id).get();
        employeeEntitiy.setFirstName(employee.getFirstName());
        employeeEntitiy.setLastName(employee.getLastName());
        employeeEntitiy.setEmailId(employee.getEmailId());

        employeeRepository.save(employeeEntitiy);
        return employee;
    }
}
