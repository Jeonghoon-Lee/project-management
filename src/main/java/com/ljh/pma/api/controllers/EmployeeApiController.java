package com.ljh.pma.api.controllers;

import com.ljh.pma.entities.Employee;
import com.ljh.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    EmployeeService empService;

    @GetMapping
    public Iterable<Employee> getEmployees() {
        return empService.getAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return empService.getById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody @Valid Employee employee) {
        return empService.save(employee);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody @Valid Employee employee) {
        return empService.save(employee);
    }

    @PatchMapping(path="/{id}", consumes = "application/json")
    public Employee partialUpdate(@PathVariable long id, @RequestBody @Valid Employee patchEmployee) {
        return empService.updateById(id, patchEmployee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        try {
            empService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

        }
    }

    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Employee> findPaginateEmployees(@RequestParam("page") int page,
                                                    @RequestParam("size") int size) {
        PageRequest pageAndSize = PageRequest.of(page, size);
        return empService.findAll(pageAndSize);
    }
}
