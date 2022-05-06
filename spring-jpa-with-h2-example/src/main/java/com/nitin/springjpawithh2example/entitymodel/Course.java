package com.nitin.springjpawithh2example.entitymodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnTransformer;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 2 //id increment by 2 with initialValue=1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "course_sequence"
    )
    @Column(
            name="course_id",
            updatable = false
    )
    private int course_id;

    @Column(
            name="course_name",
            columnDefinition="TEXT"
    )
    private String courseName;

    @Column(name = "description")
    private String desc;

    private long course_fee;

    @Column(name="is_active")
    private boolean isActive;

    @Column(name = "course_password")
   // @NonNull
   /* @ColumnTransformer(read = "pgp_sym_decrypt(course_password, 'mySecretKey')", write =  "pgp_sym_encrypt(?, 'mySecretKey')")
    @ColumnTransformer(
            read =  "pgp_sym_decrypt(course_password, current_setting('encrypt.key'))",
            write = "pgp_sym_encrypt(  ?, current_setting('encrypt.key')) ")
   */

    //@ToString.Exclude <-did not work
    @JsonIgnore
    private String coursePassword;

}
