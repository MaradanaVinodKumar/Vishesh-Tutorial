package com.vishesh.student.registration.repository.repositorys;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.vishesh.student.registration.repository.StudentesCoursesInCart;

import jakarta.transaction.Transactional;

public interface StudentesCoursesInCartRepository extends CrudRepository<StudentesCoursesInCart,BigDecimal>{
	@Query( nativeQuery = true, value ="SELECT * FROM studentes_courses_in_cart where user_id=:userId")
	Iterable<StudentesCoursesInCart> findByUserId(@Param("userId") BigDecimal userId); 
	 @Modifying
	 @Transactional
	@Query( nativeQuery = true, value =" DELETE FROM studentes_courses_in_cart WHERE user_id=:userId and user_course_id=:userCourseId")
	void removeCourse(@Param("userId") BigDecimal userId,@Param("userCourseId") BigDecimal userCourseId);
	 
	@Query(nativeQuery=true,value="Select * from studentes_courses_in_cart WHERE user_id=:userId and user_course_id=:userCourseId")
	Optional<StudentesCoursesInCart>  studentCourseExistInCart(@Param("userId") BigDecimal userId,@Param("userCourseId") BigDecimal userCourseId);
}
