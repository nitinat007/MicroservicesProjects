package com.nitin.springjpawithh2example.controller;

import com.nitin.springjpawithh2example.entitymodel.Course;
import com.nitin.springjpawithh2example.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/university")
@Slf4j
public class CourseController {

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllUniversityCourses(@RequestParam(required = false, defaultValue = "any") String course_name) {
        List<Course> courseList = new ArrayList<>();
        try {
            if (course_name.equals("any")) {
                courseRepository.findAll().forEach(courseList::add);
            } else {
                courseRepository.findByCourseNameContaining(course_name).forEach(courseList::add);
            }
            if (courseList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/activeCourses")
    public ResponseEntity<List<Course>> getAllActiveCourses() {
        List<Course> courseList = new ArrayList<>();
        try {
            courseRepository.findByIsActive(true).forEach(courseList::add);
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(courseList,HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/courses")
    public ResponseEntity<Course> createCourse(@RequestBody(required = true) Course course) {
        try {
            Course course1 = courseRepository.save(new Course(course.getCourse_id(), course.getCourseName(), course.getDesc(), course.getCourse_fee(), course.isActive()));
            return new ResponseEntity<>(course1, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Exception: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
