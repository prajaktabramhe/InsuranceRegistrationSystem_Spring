package com.bridgelabz.imp.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity 
@Table(name="userInsurance")
@Data
public class UserData 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	public Long id;
	public String fullname;
	public String mobilenumber;
	public int age;
	public String occupation;
	public String familybackground;
	public String healthcondition;
	public String vehicledata;
	public LocalDateTime registereddate=LocalDateTime.now();
	public LocalDateTime updateddate;
	public String kyc;

	@ElementCollection
	@CollectionTable(name = "UserRegister", joinColumns = @JoinColumn(name = "user_id"))

	@Column(name = "address")
	private List<String> address;

	public UserData() {}

	public UserData(Long id, String fullname, String mobileNumber, int age, String occupation, String familyBackground,
			String healthCondition, String vehicledata, LocalDateTime registeredDate, LocalDateTime updatedDate,
			String kYC) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.mobilenumber = mobileNumber;
		this.age = age;
		this.occupation = occupation;
		this.familybackground = familyBackground;
		this.healthcondition = healthCondition;
		this.vehicledata = vehicledata;
		this.registereddate = registeredDate;
		this.updateddate = updatedDate;
		this.kyc = kYC;
	}





}
