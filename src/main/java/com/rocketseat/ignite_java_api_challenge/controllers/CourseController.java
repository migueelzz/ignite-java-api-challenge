package com.rocketseat.ignite_java_api_challenge.controllers;

import com.rocketseat.ignite_java_api_challenge.dtos.CourseDTO;
import com.rocketseat.ignite_java_api_challenge.dtos.CourseStatusDTO;
import com.rocketseat.ignite_java_api_challenge.models.Course;
import com.rocketseat.ignite_java_api_challenge.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody @Valid CourseDTO course) {
        Course newCourse = this.courseService.create(course);

        return ResponseEntity.ok().body(newCourse);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        var courses = this.courseService.findAll();

        return ResponseEntity.ok().body(courses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable UUID id, @RequestBody @Valid CourseDTO course) {
        Course updatedCourse = this.courseService.update(id, course);

        return ResponseEntity.ok().body(updatedCourse);
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Course> updateStatusCourse(@PathVariable UUID id, @RequestBody @Valid CourseStatusDTO course) {
        Course updatedStatusCourse = this.courseService.updateStatus(id, course);

        return ResponseEntity.ok().body(updatedStatusCourse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCourse(@PathVariable UUID id) {
        this.courseService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
