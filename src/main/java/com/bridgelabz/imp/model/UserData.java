package com.bridgelabz.imp.model;

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
	public LocalDateTime registereddate=LocalDateTime.now();
	public LocalDateTime updateddate;
	public String kyc;
	public String permanentAddress;
	public String temporaryAddress;

	public UserData() {}

	public UserData(Long userid, String fullname, String mobilenumber, int age, String occupation, String familybackground,
			String healthcondition, String vehicledata, LocalDateTime registereddate, LocalDateTime updateddate,
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
