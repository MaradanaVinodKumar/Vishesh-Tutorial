package com.vishesh.student.registration.repository;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table (name = "student_images")
public class StudentImages {
	  @Id
	   @GeneratedValue (strategy = GenerationType.IDENTITY)
	   @Column (name="id")
	   private BigDecimal imageId;
	  	@Column (name="student_id")
	   private BigDecimal student_id;
		@Column (name="image")
		private byte[] image;
		
		public BigDecimal getImageId() {
			return imageId;
		}
		public void setImageId(BigDecimal imageId) {
			this.imageId = imageId;
		}
		public BigDecimal getStudentId() {
			return student_id;
		}
		public void setStudentId(BigDecimal student_id) {
			this.student_id = student_id;
		}
		public byte[] getImage() {
			return image;
		}
		public void setImage(byte[] image) {
			this.image = image;
		}
	  	
	  
}
