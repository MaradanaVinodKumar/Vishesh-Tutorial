package com.vishesh.student.registration.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vishesh.student.registration.exceptions.ResponseBody;
import com.vishesh.student.registration.model.EmailIdPassword;
import com.vishesh.student.registration.model.ForgetEmail;
import com.vishesh.student.registration.model.ForgetPhone;
import com.vishesh.student.registration.model.PhoneNumberPassword;
import com.vishesh.student.registration.model.Student;
import com.vishesh.student.registration.model.StudentDetails;
import com.vishesh.student.registration.model.UserPasswordDetails;
import com.vishesh.student.registration.repository.StudentFileData;
import com.vishesh.student.registration.repository.StudentImages;

import com.vishesh.student.registration.repository.StudentRegistration;

import com.vishesh.student.registration.repository.UserPasswordStorage;
import com.vishesh.student.registration.repository.repositorys.StudentFileDataRepository;
import com.vishesh.student.registration.repository.repositorys.StudentImagesRepository;
import com.vishesh.student.registration.repository.repositorys.StudentRegistrationRepository;
import com.vishesh.student.registration.repository.repositorys.UserPasswordStorageRepository;
import com.vishesh.student.registration.service.StudentRegistrationService;

@Service
public class StudentRegistrationServiceImpl implements StudentRegistrationService {
	@Autowired
	StudentRegistrationRepository studentRegistrationRepository;
	@Autowired
	UserPasswordStorageRepository userPasswordStorageRepository;
	@Autowired
	StudentImagesRepository studentImagesRepository;
	@Autowired
	StudentFileDataRepository studentFileDataRepository;

	@Override
	public Student processStudentRegistration(Student student) {
		StudentRegistration studentRegistration = new StudentRegistration();
		studentRegistration.setFirstName(student.getFirstName());
		studentRegistration.setLastName(student.getLastName());
		studentRegistration.setSchoolName(student.getSchoolName());
		studentRegistration.setPhoneNumber(student.getPhoneNumber());
		studentRegistration.setEmail(student.getEmailId());
		studentRegistration.setClassName(student.getClassName());
		studentRegistration.setPassword(student.getPassword());
		studentRegistrationRepository.save(studentRegistration);
		return null;
	}

	@Override
	public Student fetchStudentDetails(Long id) {
		Optional<StudentRegistration> studentDetails = studentRegistrationRepository.findById(new BigDecimal(id));
		Student student = null;
		if (studentDetails.isPresent()) {
			student = new Student();
			StudentRegistration studentResgistration = studentDetails.get();
			student.setFirstName(studentResgistration.getFirstName());
			student.setLastName(studentResgistration.getLastName());
			student.setClassName(studentResgistration.getSchoolName());
			student.setSchoolName(studentResgistration.getSchoolName());
			student.setPhoneNumber(String.valueOf(studentResgistration.getPhoneNumber()));
			student.setEmailId(studentResgistration.getEmail());

		} else {
			throw new Error("no record found!");
		}
		return student;
	}

	@Override
	public boolean phoneNumberExist(String phoneNumber) { // exist
		Optional<StudentRegistration> student = studentRegistrationRepository.findByPhoneNumber(phoneNumber);
		if (student.isPresent()) {
			return true;
		}

		return false;
	}

	@Override
	public boolean emailIdExist(String emailId) {
		Optional<StudentRegistration> student = studentRegistrationRepository.findByEmail(emailId);

		if (student.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public void storeUserPasswordDetails(UserPasswordDetails userPasswordDetails) {
		UserPasswordStorage userPasswordStorage = new UserPasswordStorage();
		userPasswordStorage.setPhone_number(userPasswordDetails.getPhone());
		userPasswordStorage.setEmail_id(userPasswordDetails.getEmail());
		userPasswordStorage.setPassword(userPasswordDetails.getPassword());
		userPasswordStorageRepository.save(userPasswordStorage);
	}

	@Override
	public boolean PhoneNumberPasswordValidation(PhoneNumberPassword phoneNumberPassword) {
		Iterable<StudentRegistration> userPasswordStorage = studentRegistrationRepository.findAll();
		for (StudentRegistration user : userPasswordStorage) {
			if (user.getPhoneNumber().equals(phoneNumberPassword.phoneNumber)) {
				if (user.getPassword().equals(phoneNumberPassword.password)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public boolean emailIdPasswordValidation(EmailIdPassword emailIdPassword) {
		Iterable<StudentRegistration> userPasswordStorage = studentRegistrationRepository.findAll();
		for (StudentRegistration user : userPasswordStorage) {
			if (user.getEmail().equals(emailIdPassword.getEmailId())) {
				if (user.getPassword().equals(emailIdPassword.getPassword())) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	@Override
	public void updateNewPasswordByPhoneNumber(ForgetPhone forgetPhone) {
		Optional<StudentRegistration> studentDetails = studentRegistrationRepository
				.findByPhoneNumber(forgetPhone.getPhoneNumber());
		if (studentDetails.isPresent()) {
			StudentRegistration persion = studentDetails.get();
			persion.setPassword(forgetPhone.getNewPassword());
			studentRegistrationRepository.save(persion);
		} else {
			throw new Error("user not found");
		}
	}

	@Override
	public void updateNewPasswordByEmail(ForgetEmail forgetemailId) {
		Optional<StudentRegistration> studentDetails = studentRegistrationRepository
				.findByEmail(forgetemailId.getEmailId());
		if (studentDetails.isPresent()) {
			StudentRegistration persion = studentDetails.get();
			persion.setPassword(forgetemailId.getNewPassword());
			studentRegistrationRepository.save(persion);
		} else {
			throw new Error("user not found");
		}
	}

	@Override
	public StudentDetails studentDetailsGet(String userName) {
		if ((userName.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"))
				&& (!userName.isEmpty())) {
			Optional<StudentRegistration> studentDetails = studentRegistrationRepository.findByEmail(userName);
			if (studentDetails.isPresent()) {
				StudentRegistration student = studentDetails.get();
				StudentDetails details = new StudentDetails();
				details.setName(student.getFirstName() + " " + student.getLastName());
				details.setPhoneNumber(student.getPhoneNumber());
				details.setEmailId(student.getEmail());
				details.setClassName(student.getClassName());
				details.setSchoolName(student.getSchoolName());
				Iterable<StudentImages> studentImage = studentImagesRepository.findAll();
				for (StudentImages user : studentImage) {
					if (user.getStudentId().equals(student.getStudentId())) {
						details.setImage(user.getImage());
					}
				}
				return details;
			}
		} else if (userName.matches("^[6-9][0-9]{9}$") && (!userName.isEmpty())) {
			Optional<StudentRegistration> studentDetails = studentRegistrationRepository.findByPhoneNumber(userName);
			if (studentDetails.isPresent()) {
				StudentRegistration student = studentDetails.get();
				StudentDetails details = new StudentDetails();
				details.setName(student.getFirstName() + " " + student.getLastName());
				details.setPhoneNumber(student.getPhoneNumber());
				details.setEmailId(student.getEmail());
				details.setClassName(student.getClassName());
				details.setSchoolName(student.getSchoolName());
				Iterable<StudentImages> studentImage = studentImagesRepository.findAll();
				for (StudentImages user : studentImage) {
					if (user.getStudentId().equals(student.getStudentId())) {
						details.setImage(user.getImage());
					}
				}
				return details;
			}
		} else {
			throw new Error("user is not found");
		}
		return null;

	}

	@Override
	public String uploadImage(MultipartFile file, String userId) {
		StudentImages studentImage = new StudentImages();
		if ((userId.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$"))
				&& (!userId.isEmpty())) {
			Optional<StudentRegistration> studentDetails = studentRegistrationRepository.findByEmail(userId);
			if (studentDetails.isPresent()) {
				try {
					StudentRegistration student = studentDetails.get();
					studentImage.setStudentId(student.getStudentId());
					studentImage.setImage(file.getBytes());
					studentImagesRepository.save(studentImage);
					return "Success ";
				} catch (IOException e) {
					e.printStackTrace();
					throw new Error("not saved image");
				}

			} else {
				throw new Error("userId is not found !");
			}
		} else if ((userId.matches("^[6-9][0-9]{9}$")) && (!userId.isEmpty())) {
			Optional<StudentRegistration> studentDetails = studentRegistrationRepository.findByPhoneNumber(userId);
			if (studentDetails.isPresent()) {

				try {
					StudentRegistration student = studentDetails.get();
					studentImage.setStudentId(student.getStudentId());
					studentImage.setImage(file.getBytes());
					studentImagesRepository.save(studentImage);
					return "Success ";
				} catch (IOException e) {
					e.printStackTrace();
					throw new Error("not saved image");
				}
			} else {
				throw new Error("userId is not found !");
			}
		} else {
			throw new Error("userId is not found !");

		}
	}

/*	@Override
	public Map<String, Object> extractExcelFile(MultipartFile excelFile) {

		if (excelFile.getContentType()
				.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			try {
				XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
				XSSFSheet sheet = workbook.getSheet("Sheet1");
				// System.out.println(sheet.toString());
				boolean firstRow = false;
				for (Row row : sheet) {
					StudentFileData studentExcelFileData = new StudentFileData();
					if (firstRow) {
						Iterator<Cell> cellIterator = row.iterator();
						int rowCellsCount = 0;
						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();
							if (rowCellsCount != 0) {
								// System.out.print(" "+cell+" ");
								switch (rowCellsCount) {
								case 1:
									studentExcelFileData.setFirstName(cell.toString());
									break;
								case 2:
									studentExcelFileData.setLastName(cell.toString());
									break;
								case 3:
									studentExcelFileData.setClassName(cell.toString());
									break;
								case 4:
									studentExcelFileData.setSchoolName(cell.toString());
									break;
								case 5:
									studentExcelFileData
											.setPhoneNumber(String.format("%.0f", cell.getNumericCellValue()));
									break;
								case 6:
									studentExcelFileData.setEmail(cell.toString());
									break;
								}
							}
							rowCellsCount++;
						}
						// System.out.println("");
						studentFileDataRepository.save(studentExcelFileData);
					} else {
						firstRow = true;
					}
				}
				workbook.close();

				Map<String, Object> response = new HashMap<>();

				response.put("status", 200);
				response.put("Message", " '" + excelFile.getOriginalFilename() + "' file data saved in data base");
				return response;
			} catch (IOException e) {
				throw new ResourceNotFoundException("data have not updated in database. because the given file is '"
						+ excelFile.getOriginalFilename() + "' is not a Excel file.");
				// e.printStackTrace();
			}

		} else {

			throw new ResourceNotFoundException("data have not updated in database. because the given file '"
					+ excelFile.getOriginalFilename() + "' is not a Excel file.");

		}

	}
	
	*/
	
	@Override
	public ResponseBody extractExcelFile(MultipartFile excelFile) {

		if (excelFile.getContentType()
				.equalsIgnoreCase("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			try {
				XSSFWorkbook workbook = new XSSFWorkbook(excelFile.getInputStream());
				XSSFSheet sheet = workbook.getSheet("Sheet1");
				// System.out.println(sheet.toString());
				boolean firstRow = true;
				Integer[] fileDataHeadIndex = { 0, 1, 2, 3, 4, 5 ,6};
				for (Row row : sheet) {
					StudentFileData studentExcelFileData = new StudentFileData();
					Iterator<Cell> cellIterator = row.iterator();
					
					int rowCellsCount = 0;
					
					String column="";
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						column +=cell.toString()+",";
//						System.out.println(cell+" "+rowCellsCount);
						rowCellsCount++;
					}
					
					String[] fileDataHeadFields =column.split(",");
//					System.out.println(column+" "+fileDataHeadFields[1]);
//					trimStrings(fileDataHeadFields);
					if (firstRow) {
						firstRow = false;
						fileDataHeadIndexFinder(fileDataHeadFields, fileDataHeadIndex, excelFile.getOriginalFilename());
					} else {
						
						studentExcelFileData.setFirstName(fileDataHeadFields[fileDataHeadIndex[1]].trim());
						studentExcelFileData.setLastName(fileDataHeadFields[fileDataHeadIndex[2]].trim());
						studentExcelFileData.setClassName(fileDataHeadFields[fileDataHeadIndex[3]].trim());
						studentExcelFileData.setSchoolName(fileDataHeadFields[fileDataHeadIndex[4]].trim());
						studentExcelFileData.setPhoneNumber(String.format("%.0f", Double.parseDouble(fileDataHeadFields[fileDataHeadIndex[5]])).trim());
						studentExcelFileData.setEmail(fileDataHeadFields[fileDataHeadIndex[6]].trim());
						studentFileDataRepository.save(studentExcelFileData);
					}
						// System.out.println("");
				}
				workbook.close();
				
				ResponseBody responseBody =new ResponseBody(0, null);

				responseBody.setStatus(200);
				responseBody.setMessage(" '" + excelFile.getOriginalFilename() + "' file data saved in data base");
				
				return responseBody;
			} catch (IOException e) {
				throw  new ResourceNotFoundException("data have not updated in database. because the given file is '"
						+ excelFile.getOriginalFilename() + "' is not a Excel file.");
				// e.printStackTrace();
			}

		} else {

			throw new ResourceNotFoundException("data have not updated in database. because the given file '"
					+ excelFile.getOriginalFilename() + "' is not a Excel file.");

		}

	}
//	@Override
//	public ByteArrayInputStreamn extractExcelFile() {
//		
//		
//		return null;
//	}

	/*
	 * @Override public Map<String, Object> extractTextFile(MultipartFile textFile)
	 * {
	 * 
	 * //StringBuilder resultStringBuilder = new StringBuilder();
	 * if(textFile.getContentType().equalsIgnoreCase("text/csv")||textFile.
	 * getContentType().equalsIgnoreCase("text/plain")) { try (BufferedReader br=
	 * new BufferedReader(new InputStreamReader(textFile.getInputStream()))) {
	 * String line; while ((line = br.readLine()) != null) {
	 * //resultStringBuilder.append(line).append("\n"); // System.out.println(line);
	 * String[] rowData=line.split(","); // System.out.println(rowData[1]);
	 * StudentFileData studentTextFileData = new StudentFileData();
	 * studentTextFileData.setFirstName(rowData[1]);
	 * studentTextFileData.setLastName(rowData[2]);
	 * studentTextFileData.setClassName(rowData[3]);
	 * studentTextFileData.setSchoolName(rowData[4]);
	 * studentTextFileData.setPhoneNumber(rowData[5]);
	 * studentTextFileData.setEmail(rowData[6]);
	 * studentFileDataRepository.save(studentTextFileData); } Map<String, Object>
	 * response = new HashMap<>(); response.put("status",200);
	 * response.put("Message"," '"+textFile.getOriginalFilename()
	 * +"' file data saved in data base"); return response; } catch (IOException e)
	 * { // TODO Auto-generated catch block throw new
	 * ResourceNotFoundException("data have not updated in database. because the given file is '"
	 * +textFile.getOriginalFilename()+"' is not a Excel file."); } } else { throw
	 * new
	 * ResourceNotFoundException("data have not updated in database. because the given file is '"
	 * +textFile.getOriginalFilename()+"' is not a Excel file."); } // return
	 * resultStringBuilder.toString(); // }
	 */
	@Override
	public Map<String, Object> extractTextFile(MultipartFile textFile){
		if (textFile.getContentType().equalsIgnoreCase("text/plain")||textFile.getContentType().equalsIgnoreCase("text/csv")) {     //textFile.getContentType().equalsIgnoreCase("text/csv")||
			try (BufferedReader br = new BufferedReader(new InputStreamReader(textFile.getInputStream()))) { 
				String line;
				boolean heading = true;
				Integer[] fileDataHeadIndex = { 0, 1, 2, 3, 4, 5 ,6};
				while ((line = br.readLine()) != null) {
					// resultStringBuilder.append(line).append("\n");
//			        	System.out.println(line);
					StudentFileData studentTextFileData = new StudentFileData();
					if (heading){
						String[] fileDataHeadFields = line.split(",");
//						trimStrings(fileDataHeadFields);
						fileDataHeadIndexFinder(fileDataHeadFields, fileDataHeadIndex, textFile.getOriginalFilename());
						heading = false;
					} 
					else{
						String[] rowData = line.split(",");
						// System.out.println(rowData[1]);
//						trimStrings(rowData);
						studentTextFileData.setFirstName(rowData[fileDataHeadIndex[1]].trim());
						studentTextFileData.setLastName(rowData[fileDataHeadIndex[2]].trim());
						studentTextFileData.setClassName(rowData[fileDataHeadIndex[3]].trim());
						studentTextFileData.setSchoolName(rowData[fileDataHeadIndex[4]].trim());
						studentTextFileData.setPhoneNumber(rowData[fileDataHeadIndex[5]].trim());
						studentTextFileData.setEmail(rowData[fileDataHeadIndex[6]].trim());
						studentFileDataRepository.save(studentTextFileData);
					}
				}
				Map<String, Object> response = new HashMap<>();
				response.put("status", 200);
				response.put("message", " '" + textFile.getOriginalFilename() + "' file data saved in data base");
				return response;
			} catch (IOException e) {
				throw new ResourceNotFoundException("data have not updated in database. because the given file is '"+ textFile.getOriginalFilename() + "' is not a text file.");
			}
		} else {
			throw new ResourceNotFoundException("data have not updated in database. because the given file is '"+ textFile.getOriginalFilename() + "' is not a text file.");
		}
			  
	}

	public void fileDataHeadIndexFinder(String[] fileDataHeadFields, Integer[] fileDataHeadIndex, String fileName) {
		
		String[] dataBaseHeadFields = {"id","first_name", "last_name", "class_name", "school_name", "phone_number","email_id"};
		int dataBaseFieldIndex = 0;
		for (String dataBaseField : dataBaseHeadFields) {
			int fileFieldIndex = 0;
			boolean field = true;
			for (String fileField : fileDataHeadFields) {
//					System.out.println(dataBaseField+" == "+ fileField +"   ->"+dataBaseField.equals(fileField));
					if(dataBaseField.equals(fileField.trim().toLowerCase())) {
						fileDataHeadIndex[dataBaseFieldIndex] = fileFieldIndex;
						field = false;
						break;
					}
					fileFieldIndex++;
			}
			if (field) {
				throw new ResourceNotFoundException("data have not updated in database. because the field '"+ dataBaseField + "' is not found in '" + fileName + "' file.");
			}
			dataBaseFieldIndex++;
		}
	}
	

}

