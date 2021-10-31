package sdkdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import sdkdata.entity.Project;
import sdkdata.exception.DataFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.*;
import sdkdata.exception.ErrorCode;
import sdkdata.exception.ProjectException;
import sdkdata.service.ProjectService;

import java.util.*;

@Controller
@RequestMapping("/sdkguide")
public class ProjectController {

    private ProjectService projectService;

    @Autowired
    ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value="/analyze", method=RequestMethod.GET)
    public ResponseEntity<String> fetchProject() {
        try {
            projectService.fetchProject();
        } catch (ProjectException err) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Project is not found", err);
        }
        return ResponseEntity.ok("Project fetched Successfully");
    }

    @RequestMapping(value="/projects", method = RequestMethod.POST)
    public <T> ResponseEntity<String> createProject(@RequestBody Map<String, T> dataObject) throws DataFormatException {
        if (dataObject == null) {
            throw new DataFormatException("No data has been received", ErrorCode.NULL_DATA);
        }
        try {
            projectService.createProject(dataObject);
        } catch (ProjectException err) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Project is not uploaded", err);
        }
        return ResponseEntity.ok("Project created Successfully");
    }
}
