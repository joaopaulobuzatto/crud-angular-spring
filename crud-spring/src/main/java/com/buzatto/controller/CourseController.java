package com.buzatto.controller;

import com.buzatto.model.Course;
import com.buzatto.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<Course> list() {
        return courseService.list();
    }

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(courseService.save(course));
    }

}