package com.nitin.springjpawithh2example.repository;

import com.nitin.springjpawithh2example.entitymodel.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/*
These are already present: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
 */
public interface CourseRepository extends JpaRepository<Course, Integer> {

    List<Course> findByIsActive(boolean is_active);

    List<Course> findByCourseNameContaining(String course_name);

}
