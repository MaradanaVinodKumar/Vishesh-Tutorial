package com.vishesh.student.registration.repository.repositorys;

import java.math.BigDecimal;

import org.springframework.data.repository.CrudRepository;

import com.vishesh.student.registration.repository.StudentFileData;


public interface StudentFileDataRepository  extends CrudRepository<StudentFileData,BigDecimal>  {

}
