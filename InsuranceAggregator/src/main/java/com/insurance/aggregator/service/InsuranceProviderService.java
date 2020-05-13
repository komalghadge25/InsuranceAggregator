package com.insurance.aggregator.service;

import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.insurance.aggregator.entity.InsuranceProvider;
import com.insurance.aggregator.exception.NoSuchRecordException;
import com.insurance.aggregator.repository.InsuranceProviderRepository;

@Service
@Transactional
public class InsuranceProviderService {
	
	@Autowired
	private InsuranceProviderRepository insuranceRepo;
	
	public List<InsuranceProvider> getAllProvider(){ 
		  return insuranceRepo.findAll(); 
		  }
	  
	  public void saveInsuranceProvider(InsuranceProvider insuranceProvider) { 
		  
		  insuranceRepo.save(insuranceProvider); 
		  }
	  
	  public InsuranceProvider getProviderById(Long insurance_provider_id) { 
		  return insuranceRepo.findById(insurance_provider_id).orElseThrow(()->  new NoSuchRecordException("NO SUCH ELEMENT FOUND!!") );
		  }
	  
	  public void deleteProviderById(long insurance_provider_id) throws NoSuchRecordException{ 
		  insuranceRepo.deleteById(insurance_provider_id);
	  }
	 

}
