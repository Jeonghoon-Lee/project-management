package com.ljh.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljh.pma.dto.ProjectTimeline;
import com.ljh.pma.entities.Employee;
import com.ljh.pma.entities.Project;
import com.ljh.pma.services.EmployeeService;
import com.ljh.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public String displayProjects(Model model) throws JsonProcessingException {
        Iterable<Project> projectList = projectService.getAll();
        model.addAttribute("projectList", projectList);

        List<ProjectTimeline> projectTimelines = projectService.getProjectTimeline();
        String jsonProjectTimeline = new ObjectMapper().writeValueAsString(projectTimelines);

        model.addAttribute("projectTimeline", jsonProjectTimeline);
        return "projects/project-list";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {
        Project newProject = new Project();
        Iterable<Employee> employeeList = employeeService.getAll();

        model.addAttribute("project", newProject);
        model.addAttribute("employeeList", employeeList);
        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project) {
        // this should handle saving to the database...
        projectService.save(project);
        // use a redirect to prevent duplicate submission
        return "redirect:/projects";
    }

    @GetMapping("/update")
    public String updateProject(Model model, @RequestParam("id") long id) {
        Project project = projectService.getById(id);
        Iterable<Employee> employeeList = employeeService.getAll();

        model.addAttribute("project", project);
        model.addAttribute("employeeList", employeeList);

        return "projects/new-project";
    }

    @GetMapping("/delete")
    public String deleteProject(@RequestParam("id") long id) {
        projectService.deleteById(id);
        return "redirect:/projects";
    }
}
