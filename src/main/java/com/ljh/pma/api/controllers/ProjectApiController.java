package com.ljh.pma.api.controllers;

import com.ljh.pma.entities.Project;
import com.ljh.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectService projService;

    @GetMapping
    public Iterable<Project> getProjects() {
        return projService.getAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") long id) {
        return projService.getById(id);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody @Valid Project project) {
        return projService.save(project);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Project update(@RequestBody @Valid Project project) {
        return projService.save(project);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Project partialUpdate(@PathVariable("id") long id, @RequestBody @Valid Project patchProject) {
        return projService.updateById(id, patchProject);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        try {
            projService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        }
    }
}
