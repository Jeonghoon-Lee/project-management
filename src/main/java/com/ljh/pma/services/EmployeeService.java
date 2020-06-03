package com.ljh.pma.services;

import com.ljh.pma.dto.EmployeeProject;
import com.ljh.pma.entities.Employee;
import com.ljh.pma.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepo;

    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Iterable<Employee> getAll() {
        return employeeRepo.findAll();
    }

    public Employee getById(long id) {
        return employeeRepo.findById(id).get();
    }

    public Employee updateById(long id, Employee patchEmployee) {
        Employee employee = getById(id);

        if (patchEmployee.getEmail() != null) {
            employee.setEmail(patchEmployee.getEmail());
        }
        if (patchEmployee.getFirstName() != null) {
            employee.setFirstName(patchEmployee.getFirstName());
        }
        if (patchEmployee.getLastName() != null) {
            employee.setLastName(patchEmployee.getLastName());
        }
        return employeeRepo.save(employee);
    }

    public void deleteById(long id) {
        employeeRepo.deleteById(id);
    }

    public List<EmployeeProject> getProjectsByEmployee() {
        return employeeRepo.getProjectsByEmployee();
    }

    public Iterable<Employee> findAll(PageRequest pageAndSize) {
        return employeeRepo.findAll(pageAndSize);
    }
}
