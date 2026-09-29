package com.cogig.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cogig.model.ServiceRequest;

public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

    List<ServiceRequest> findByCustomerId(Long customerId);
}