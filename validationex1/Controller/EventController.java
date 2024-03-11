package com.example.validationex1.Controller;

import com.example.validationex1.ApiResponce.ApiResponce;
import com.example.validationex1.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("api/v1/event")
public class EventController {
    ArrayList<Event> events=new ArrayList<>();

    @GetMapping("/get")
    public ResponseEntity getEvents() {

        return ResponseEntity.status(200).body(events);
    }

    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.status(200).body(new ApiResponce("event added"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid Event event,Errors errors){
        if(errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();

            return ResponseEntity.status(400).body(message);
        }

        events.set(index,event);
        return ResponseEntity.status(200).body(new ApiResponce("event updated"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index){
        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponce("event deleted"));
    }

    //not
    @PutMapping("/change/{index}/{capacity}")
    public ResponseEntity change(@PathVariable int index,@PathVariable int capacity){

        events.get(index).setCapacity(capacity);
        return ResponseEntity.status(200).body(new ApiResponce("capacity change"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity search(@PathVariable String id){
        for( Event e : events){
            if(e.getId().equals(id)){
                return ResponseEntity.status(200).body(new ApiResponce("event found"+e));
            }
        }

        return ResponseEntity.status(400).body(new ApiResponce("event not found "));
    }
}
