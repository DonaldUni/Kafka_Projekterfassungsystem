package Haegertime.Donald.controller;

import Haegertime.Donald.Model.Project;
import Haegertime.Donald.exceptions.ElementNotFoundException;
import Haegertime.Donald.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    private List<Project> getAllProjects(){

        return projectService.findAllProjects();
    }

    @GetMapping(path = "/{id}")
    private Project getProjectById(@PathVariable(value = "id")Long id){

        return projectService.findProjectById(id);
    }

    @PostMapping
    private Project postProjectToCustomer(@RequestParam(name = "customerId") Long customerId, @RequestBody Project newProject) throws  ElementNotFoundException {

        if (newProject == null){

            return null;
        }else {

            return projectService.addNewProjectToCustomer(customerId, newProject);
        }
    }

    @PatchMapping
    private Project patchProject(@RequestParam(name = "id") Long id, @RequestBody Project updatedProject) throws ElementNotFoundException {

        if (updatedProject == null){

            return null;
        }else {

            return projectService.updateProject(id, updatedProject);
        }
    }

    @DeleteMapping(path ="/projects")
    private void deleteProjectFromCustomer(@RequestParam(name = "customerId") Long customerId, @RequestParam(name = "projectId") Long projectId) throws ElementNotFoundException {

        projectService.removeProjectFromCustomer(customerId, projectId);
    }

    @PatchMapping(path ="/projects/{project_id}/assignments/{employee_id}")
    private void postEmployeeToProject(@RequestParam(name = "project_id") Long project_id, @RequestParam(name = "employee_id") Long employee_id) throws ElementNotFoundException {

        projectService.assignEmployeeToProject(project_id, employee_id);
    }

    @DeleteMapping(path ="/projects/{project_id}/assignments/{employee_id}")
    private void deleteEmployeeFromProject(@RequestParam(name = "project_id") Long project_id, @RequestParam(name = "employee_id") Long employee_id) throws ElementNotFoundException {

        projectService.removeEmployeeFromProject(project_id, employee_id);
    }

}
