package com.insurance.aggregator.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurance.aggregator.entity.InsuranceProvider;
import com.insurance.aggregator.entity.InsuranceProviderAggregator;
import com.insurance.aggregator.service.InsuranceProviderService;


@RestController
@CrossOrigin(origins = "*")
public class IPAggregatorController implements Serializable {
		private static final long serialVersionUID = 1L;

		@Autowired
		private InsuranceProviderService service;

		private static RestTemplate template;
		
		private static final Logger log = LoggerFactory.getLogger(IPAggregatorController.class);


		

		@GetMapping("/getAllPlans")
		public List<InsuranceProviderAggregator> getAllPlans() throws Exception {

			log.info("getAllPlans ======>>>>");

			HttpHeaders headers = new HttpHeaders();
			template = new RestTemplate();

			String ipUrl = "";

			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

			HttpEntity<String> entity = new HttpEntity<>(headers);

			ArrayList<Object> planList = new ArrayList<>();
					
			List<InsuranceProviderAggregator> commonList = new ArrayList<InsuranceProviderAggregator>();
			
			InsuranceProviderAggregator ipa = null;

			for (InsuranceProvider list : service.getAllProvider()) {

				ipUrl = list.getUrl();
				log.info("URL From Insurance Provider ===>>" + ipUrl);
				 
				ObjectMapper mapper = new ObjectMapper();

				String outputJson = "";
				log.info("Entity =====>>" + entity.toString());

				ResponseEntity<Object> respEntity = template.exchange(ipUrl, HttpMethod.GET, entity, Object.class);

				if (respEntity != null) {
					log.info("respEntity.getBody()===>> " + respEntity.getBody());
					
					planList = (ArrayList<Object>) respEntity.getBody();
					//ArrayList<Object> obj = new ArrayList<>();
					//obj.add(finalResponse);
					for (Object object : planList) {

						try {
							outputJson = mapper.writeValueAsString(object);
							log.info("outputJson ====>> " + outputJson);
							ipa = mapper.readValue(outputJson, InsuranceProviderAggregator.class);

							log.info("Policy Id ====>>" + ipa.getPlanID());
							log.info("common plans from mapper.read====>> " + ipa);
							if (ipa != null) {
								commonList.add(ipa);
							}

						} catch (Exception e) {

						}

					}

				}

			}
			
			return commonList;

		}
		

}
		
		
