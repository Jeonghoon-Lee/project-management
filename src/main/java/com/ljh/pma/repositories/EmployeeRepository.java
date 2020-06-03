package com.ljh.pma.repositories;

import com.ljh.pma.dto.EmployeeProject;
import com.ljh.pma.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "apiemployees", path = "apiemployees")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    @Query(nativeQuery=true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount " +
            "FROM employee as e LEFT JOIN project_employee as pe " +
            "ON pe.employee_id = e.employee_id " +
            "GROUP BY e.first_name, e.last_name " +
            "ORDER BY 3 DESC")
    public List<EmployeeProject> getProjectsByEmployee();

    Employee findByEmail(String s);
}
