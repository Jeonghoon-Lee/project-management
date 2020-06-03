package com.ljh.pma.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljh.pma.dto.EmployeeProject;
import com.ljh.pma.dto.ProjectStatus;
import com.ljh.pma.entities.Project;
import com.ljh.pma.services.EmployeeService;
import com.ljh.pma.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping()
public class HomeController {

    @Value("${version}")
    private String ver;

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        model.addAttribute("versionNumber", ver);

        Iterable<Project> projectList = projectService.getAll();
        model.addAttribute("projectList", projectList);

        List<ProjectStatus> projectData = projectService.getProjectStatus();
        // Convert projectData object into a json to use in javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);

        model.addAttribute("projectStatusCnt", jsonString);

        List<EmployeeProject> employeeListProjectCount = employeeService.getProjectsByEmployee();
        model.addAttribute("employeeListProjectCount", employeeListProjectCount);

        return "main/home";
    }
}
