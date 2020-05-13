package com.insurance.aggregator.entity;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
//@Entity
//@Table(name="insurance_provider_aggregator")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InsuranceProviderAggregator  {
	
	
	//private static final long serialVersionUID = 1L;
	
	//@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)

	@JsonProperty("planID")
	@JsonAlias("plan_ID")
	private long planID;
	
	//@Column(name = "plan_provider_name")
	@JsonProperty("planProviderName")
	@JsonAlias("insuranceProvider")
	private String planProviderName;
	
	//@Column(name = "plan_coverage")
	@JsonProperty("planCoverage")
	@JsonAlias("cover")
	private double planCoverage;
	
	//@Column(name = "plan_premium")
	@JsonProperty("planPremium")
	@JsonAlias("premium")
	private double planPremium;
	
	@JsonProperty("age")
	@JsonAlias("age")
	private int age;
	

	

}
