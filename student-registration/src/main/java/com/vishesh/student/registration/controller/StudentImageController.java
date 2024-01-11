package com.vishesh.student.registration.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vishesh.student.registration.exceptions.ResponseBody;
import com.vishesh.student.registration.service.StudentRegistrationService;

@RestController
@RequestMapping ("v1/student")
public class StudentImageController {
	@Autowired
	StudentRegistrationService studentRegistrationService;
	@CrossOrigin
	@PostMapping(value="/addImage" , consumes ="multipart/form-data" ,produces="multipart/form-data")//application/json
	public String uploadImage(@RequestParam("file") MultipartFile file,@RequestParam String userId)
	{
//		System.out.println(userId);
		
		return studentRegistrationService.uploadImage(file,userId);
	}
	
	@CrossOrigin
	@PostMapping(value="/uploadExcelFile" , consumes = "multipart/form-data",produces="application/json")
	public ResponseBody uploadEXCLFile(@RequestParam("ExcelFile") MultipartFile excelFile)
	{
		
		
//			System.out.println(studentRegistrationService.extractExcelFile(excelFile.getInputStream()));
			return studentRegistrationService.extractExcelFile(excelFile);
	
	}
	@CrossOrigin
	@PostMapping(value="/uploadTextFile" , consumes = "multipart/form-data",produces="application/json")
	public Map<String,Object> uploadTextFile(@RequestParam("textFile") MultipartFile textFile)
	{
		
		
//			System.out.println(studentRegistrationService.extractExcelFile(excelFile.getInputStream()));
			return studentRegistrationService.extractTextFile(textFile);
	
	}
	
	
	@CrossOrigin
	@GetMapping("/downloadExcelFile")
	public ResponseEntity<?> downloadExcelFile(){
		
//		return ResponseEntity.ok().header(null, null);
		return null;
	}
}




















