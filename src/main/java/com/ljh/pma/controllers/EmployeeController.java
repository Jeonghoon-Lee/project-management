package com.ljh.pma.controllers;

import com.ljh.pma.entities.Employee;
import com.ljh.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {
        Employee newEmployee = new Employee();
        model.addAttribute("employee", newEmployee);
        return "employees/new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Model model, @Valid Employee employee, Errors errors) {

        if (errors.hasErrors())
            return "employees/new-employee";

        employeeService.save(employee);
        return "redirect:/employees";
    }

    @GetMapping
    public String displayEmployees(Model model) {
        Iterable<Employee> employeeList = employeeService.getAll();
        model.addAttribute("employeeList", employeeList);
        return "employees/employee-list";
    }

    // using parameter
    @GetMapping(path = "/update", params = {"id"})
    public String updateEmployee(@RequestParam("id") long id , Model model) {
        Employee employee = employeeService.getById(id);
        model.addAttribute("employee", employee);
        return "employees/new-employee";
    }

    // using path variable
//    @GetMapping("/update/{id}")
//    public String updateEmployee(@PathVariable("id") long id, Model model) {
//        Employee employee = employeeService.getById(id);
//        model.addAttribute("employee", employee);
//        return "employees/new-employees";
//    }

    // using parameter
    @GetMapping(path = "/delete", params = {"id"})
    public String deleteEmployee(@RequestParam("id") long id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }
}
