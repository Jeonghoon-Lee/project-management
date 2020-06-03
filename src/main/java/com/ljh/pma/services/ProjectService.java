package com.ljh.pma.services;

import com.ljh.pma.dto.ProjectStatus;
import com.ljh.pma.dto.ProjectTimeline;
import com.ljh.pma.entities.Project;
import com.ljh.pma.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepo;

    public Project save(Project employee) {
        return projectRepo.save(employee);
    }

    public Iterable<Project> getAll() {
        return projectRepo.findAll();
    }

    public Project getById(long id) {
        return projectRepo.findById(id).get();
    }

    public Project updateById(long id, Project patchProject) {
        Project project = getById(id);

        if (patchProject.getName() != null) {
            project.setName(patchProject.getName());
        }
        if (patchProject.getStage() != null) {
            project.setStage(patchProject.getStage());
        }
        if (patchProject.getDescription() != null) {
            project.setDescription(patchProject.getDescription());
        }
        if (patchProject.getEmployees() != null) {
            project.setEmployees(patchProject.getEmployees());
        }

        return projectRepo.save(project);
    }

    public void deleteById(long id) {
        projectRepo.deleteById(id);
    }

    public List<ProjectStatus> getProjectStatus() {
        return projectRepo.getProjectStatus();
    }

    public List<ProjectTimeline> getProjectTimeline() {
        return projectRepo.getProjectTimeline();
    }
}
