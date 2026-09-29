package com.cogig.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cogig.model.ServiceRequest;
import com.cogig.repository.ServiceRequestRepository;

@RestController
@RequestMapping("/api/service-requests")
@CrossOrigin(origins = "*")
public class ServiceRequestController {

    private final ServiceRequestRepository serviceRequestRepository;

    public ServiceRequestController(ServiceRequestRepository serviceRequestRepository) {
        this.serviceRequestRepository = serviceRequestRepository;
    }

    @GetMapping
    public List<ServiceRequest> getServiceRequests(
            @RequestParam(required = false) Long customerId) {

        if (customerId != null) {
            return serviceRequestRepository.findByCustomerId(customerId);
        }

        return serviceRequestRepository.findAll();
    }

    @PostMapping
    public ServiceRequest createServiceRequest(
            @RequestBody ServiceRequest serviceRequest) {

        if (serviceRequest.getStatus() == null ||
                serviceRequest.getStatus().isBlank()) {

            serviceRequest.setStatus("PENDING");
        }

        return serviceRequestRepository.save(serviceRequest);
    }

    @GetMapping("/{id}")
    public ServiceRequest getServiceRequestById(
            @PathVariable Long id) {

        return serviceRequestRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}/status")
    public ServiceRequest updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        ServiceRequest serviceRequest =
                serviceRequestRepository.findById(id).orElse(null);

        if (serviceRequest == null) {
            return null;
        }

        serviceRequest.setStatus(status.toUpperCase());

        return serviceRequestRepository.save(serviceRequest);
    }
}