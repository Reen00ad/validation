package com.example.validationexproject.Controller;

import com.example.validationexproject.ApiResponce.ApiResponce;
import com.example.validationexproject.Model.Project;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    ArrayList<Project> projects=new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getProjects(){
        return ResponseEntity.status(200).body(projects);
    }

    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody @Valid Project project, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);
        }
        projects.add(project);
        return ResponseEntity.status(200).body(new ApiResponce("project added"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateProject(@PathVariable int index,@RequestBody @Valid Project project,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);
        }

        projects.set(index,project);
        return ResponseEntity.status(200).body(new ApiResponce("project updated"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index){
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponce("event deleted"));
    }

    @PutMapping("/change/{index}")
    public ResponseEntity change(@PathVariable int index){
        if(projects.get(index).getStatus().equalsIgnoreCase("not Started")){
            projects.get(index).setStatus("Progress");
            return ResponseEntity.status(200).body(new ApiResponce("project changed"));}
            else if(projects.get(index).getStatus().equalsIgnoreCase("progress")){
                projects.get(index).setStatus("completed");
            return ResponseEntity.status(200).body(new ApiResponce("project changed"));
        }
        return ResponseEntity.status(400).body(new ApiResponce("not change deleted"));
    }

    @GetMapping("/search/{title}")
    public ResponseEntity search(@PathVariable String title){
        for( Project p : projects){
            if(p.getTitle().equalsIgnoreCase(title)){
                return ResponseEntity.status(200).body(new ApiResponce("project found "+p));
            }
        }

        return ResponseEntity.status(400).body(new ApiResponce("project not found "));
    }

    @GetMapping("/get/{comName}")
    public ResponseEntity company(@PathVariable String comName){
        for( Project p : projects){
            if(p.getCompanyName().equalsIgnoreCase(comName)){
                return ResponseEntity.status(200).body(new ApiResponce("project found "+p));
            }
        }

        return ResponseEntity.status(400).body(new ApiResponce("project not found "));
}}
