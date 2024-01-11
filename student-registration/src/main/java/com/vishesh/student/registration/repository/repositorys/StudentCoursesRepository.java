package com.vishesh.student.registration.repository.repositorys;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vishesh.student.registration.repository.StudentCourses;



public interface StudentCoursesRepository  extends CrudRepository<StudentCourses,BigDecimal>{

}
