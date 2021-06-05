package com.bridgelabz.imp.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity 
@Table(name="insurance_create")
@Data
public class InsuranceCreateModel 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	@ElementCollection
	@CollectionTable(name = "insurance_token",joinColumns = @JoinColumn(name = "userid"))
	private List<Long> tokenid;
	
	@ElementCollection
	@CollectionTable(name = "insurance_entity",joinColumns = @JoinColumn(name = "categoryid"))
	private List<Long> insuranceid;
	
	public int monthperiod;
	public String status;
	public LocalDateTime registereddate=LocalDateTime.now();
	public LocalDateTime updateddate;
	public boolean claim;
	
	public InsuranceCreateModel() {}

	public InsuranceCreateModel(Long id, List<Long> tokenid, List<Long> insuranceid, int monthperiod, String status,
			LocalDateTime registereddate, LocalDateTime updateddate, boolean claim) {
		super();
		this.id = id;
		this.tokenid = tokenid;
		this.insuranceid = insuranceid;
		this.monthperiod = monthperiod;
		this.status = status;
		this.registereddate = registereddate;
		this.updateddate = updateddate;
		this.claim = claim;
	}
	
	


	


	
	
}
