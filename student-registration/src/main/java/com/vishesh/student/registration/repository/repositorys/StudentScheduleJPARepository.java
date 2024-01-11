package com.vishesh.student.registration.repository.repositorys;

import java.math.BigDecimal;
import java.sql.Date;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.vishesh.student.registration.repository.StudentSchedule;

@Repository
@RepositoryRestResource(collectionResourceRel = "student_schedule", path = "student_schedule")
public interface StudentScheduleJPARepository extends CrudRepository<StudentSchedule, BigDecimal>{
	@Query( nativeQuery = true, value ="SELECT * FROM student_schedule where Schedule_date=:today")
	Iterable<StudentSchedule> findByDate(@Param("today") Date date); 
}
