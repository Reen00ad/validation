package com.example.validationexproject.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {
    @NotEmpty(message = "id should be not empty")
    @Size(min = 2,message = "id should be more than 2 characters")
    private String id;
    @NotEmpty(message = "title should be not empty")
    @Size(min = 8,message = "title should be more than 8 characters")
    private String title;
    @NotEmpty(message = "description should be not empty")
    @Size(min = 15,message = "description should be more than 15 characters")
    private String description;
    @Pattern(regexp ="^(Not Started|In Progress|Completed)$", message = "Status must be Not Started, In Progress, or Completed only")
    private String status;
    @NotEmpty(message = "companyname should be not empty")
    @Size(min = 6,message = "companyname should be more than 6 characters")
    private String companyName;
}
