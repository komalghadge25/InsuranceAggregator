package com.insurance.aggregator.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.insurance.aggregator.entity.InsuranceProvider;
import com.insurance.aggregator.service.InsuranceProviderService;

import net.minidev.json.JSONArray;

@RestController
@RequestMapping(path = "/insurance")
public class InsuranceProviderController {
	
	
	private static final Logger log = LoggerFactory.getLogger(InsuranceProviderController.class);

	
	@Autowired
	private InsuranceProviderService providerService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/allProviders")
	public JSONArray getAllProvider() throws IOException 
	{
		HttpHeaders httpHeader = new HttpHeaders();
		httpHeader.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity< String> httpEntity = new HttpEntity<String>(httpHeader);
		restTemplate = new RestTemplate();
		String url = null;
		
		JSONArray arr=new JSONArray();
		JSONArray result = new JSONArray();
		
		for(InsuranceProvider ip : providerService.getAllProvider()) {
			url = ip.getUrl();	
		
			result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, JSONArray.class).getBody();
			try {
					for(int i=0;i<result.size();i++) {
					
						Object jsonObj=result.get(i);
					
					arr.add(jsonObj);
						
							}
						
					}
			
			
			catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("URL =====>> "+ip.getUrl());
			log.info(url);
		}
		return arr;
		
	}
	
	@GetMapping(path="/providers")
	public List<InsuranceProvider> getprovider(){
		return providerService.getAllProvider();
	}
	
	@GetMapping(path="/providers/{insurance_provider_id}")
	public InsuranceProvider getProviderById(@PathVariable Long insurance_provider_id) {
		
		return providerService.getProviderById(insurance_provider_id);
	}
	
	@PostMapping(path =  "/providers")
	public void registerProvider(@RequestBody InsuranceProvider insuranceProvider) {
		providerService.saveInsuranceProvider(insuranceProvider);
	}
	
	@DeleteMapping(path="/providers/{insurance_provider_id}")
	public void deleteProvider(@PathVariable Long insurance_provider_id ) {
		providerService.deleteProviderById(insurance_provider_id);
	}
	
}
