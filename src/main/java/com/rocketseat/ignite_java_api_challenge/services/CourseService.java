package com.rocketseat.ignite_java_api_challenge.services;

import com.rocketseat.ignite_java_api_challenge.dtos.CourseDTO;
import com.rocketseat.ignite_java_api_challenge.dtos.CourseStatusDTO;
import com.rocketseat.ignite_java_api_challenge.exceptions.ResourceNotFoundException;
import com.rocketseat.ignite_java_api_challenge.models.Course;
import com.rocketseat.ignite_java_api_challenge.repositories.ICourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    @Autowired
    private ICourseRepository courseRepository;

    public List<Course> findAll() {
        return this.courseRepository.findAll();
    }

    public Course update(UUID courseId, CourseDTO data) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        existingCourse.setName(data.name());
        existingCourse.setCategory(data.category());

        return courseRepository.save(existingCourse);
    }

    public Course updateStatus(UUID courseId, CourseStatusDTO data) {
        Course existingCourse = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        existingCourse.setActive(data.active());

        return courseRepository.save(existingCourse);
    }

    public void delete(UUID courseId) {
        this.courseRepository.deleteById(courseId);
    }

    public Course create(CourseDTO data) {
        Course newCourse = new Course(data);
        this.save(newCourse);

        return newCourse;
    }

    public void save(Course course) {
        this.courseRepository.save(course);
    }
}
