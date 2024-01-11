package com.vishesh.student.registration.repository.repositorys;

import java.math.BigDecimal;

import org.springframework.data.repository.CrudRepository;

import com.vishesh.student.registration.repository.StudentImages;

public interface StudentImagesRepository extends CrudRepository<StudentImages,BigDecimal>{
	//Optional<StudentImages> findByStudentId(long student_id);
}
