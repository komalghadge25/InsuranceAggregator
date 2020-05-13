package com.insurance.aggregator.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@Entity
@Table(name="insurance_provider")
public class InsuranceProvider implements Serializable{
	/*
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long insurance_provider_id;
	@Column
	private String insurance_provider_name;
	
	@Column
	private String url;
	
	@Column
	private String response_type;
	
	@Column 
	private String response;

	

	
}
