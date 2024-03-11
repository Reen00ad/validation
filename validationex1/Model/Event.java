package com.example.validationex1.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class Event {

    @NotEmpty(message = "id should be not empty")
    @Size(min = 2,message = "id should be more than 2 characters")
    private String id;
    @NotEmpty(message = "description should be not empty")
    @Size(min = 15,message = "description should be more than 15 characters")
    private String description;
    @NotNull(message = "capacity should be not empty")
    //@Digits(message = "capacity must be a number", integer = Integer.MAX_VALUE, fraction = 0)
    @Min(value = 25,message = "capacity should be more than 25 ")
    private Integer capacity;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
//    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
