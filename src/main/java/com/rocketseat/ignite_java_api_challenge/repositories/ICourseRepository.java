package com.rocketseat.ignite_java_api_challenge.repositories;

import com.rocketseat.ignite_java_api_challenge.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICourseRepository extends JpaRepository<Course, UUID> {
}
