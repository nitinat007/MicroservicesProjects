package com.nitin.springjpawithh2example.entitymodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int course_id;

    @Column(name="course_name")
    private String courseName;

    @Column(name = "description")
    private String desc;

    private long course_fee;

    @Column(name="is_active")
    private boolean isActive;

}
