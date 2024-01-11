package com.vishesh.student.registration.repository.repositorys;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.vishesh.student.registration.repository.StudentRegistration;

public interface StudentRegistrationRepository  extends CrudRepository<StudentRegistration,BigDecimal> {
	Optional<StudentRegistration> findByEmail(String email);
	Optional<StudentRegistration> findByPhoneNumber(String phoneNumber);
}
