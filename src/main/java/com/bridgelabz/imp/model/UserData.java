package com.bridgelabz.imp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity 
@Table(name="user_registeration")
@Data
public class UserData 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	public Long userid;
	public String fullname;
	public String mobilenumber;
	public int age;
	public String occupation;
	public String familybackground;
	public String healthcondition;
	public String vehicledata;
	public LocalDate registereddate=LocalDate.now();
	public LocalDate updateddate;
	public String kyc;
	public String permanentAddress;
	public String temporaryAddress;

	public UserData() {}

	public UserData(Long userid, String fullname, String mobilenumber, int age, String occupation, String familybackground,
			String healthcondition, String vehicledata, LocalDate registereddate, LocalDate updateddate,
			String kyc, String permanentAddress, String temporaryAddress) {
		super();
		this.userid = userid;
		this.fullname = fullname;
		this.mobilenumber = mobilenumber;
		this.age = age;
		this.occupation = occupation;
		this.familybackground = familybackground;
		this.healthcondition = healthcondition;
		this.vehicledata = vehicledata;
		this.registereddate = registereddate;
		this.updateddate = updateddate;
		this.kyc = kyc;
		this.permanentAddress = permanentAddress;
		this.temporaryAddress = temporaryAddress;
	}

}
