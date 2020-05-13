package com.insurance.aggregator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurance.aggregator.entity.InsuranceProvider;

@Repository
public interface InsuranceProviderRepository extends JpaRepository<InsuranceProvider, Long> {

}
