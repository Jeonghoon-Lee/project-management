package com.ljh.pma.repositories;

import com.ljh.pma.dto.ProjectStatus;
import com.ljh.pma.dto.ProjectTimeline;
import com.ljh.pma.entities.Project;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

    @Override
    List<Project> findAll();

    @Query(nativeQuery = true,
           value = "SELECT stage as label, COUNT(*) as value FROM project GROUP BY stage")
    List<ProjectStatus> getProjectStatus();

    @Query(nativeQuery = true, value = "SELECT name, start_date as startDate, end_date as endDate FROM project")
    List<ProjectTimeline> getProjectTimeline();
}
