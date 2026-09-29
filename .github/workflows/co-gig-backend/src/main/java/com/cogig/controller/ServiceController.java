package com.cogig.controller;

import com.cogig.model.Service;
import com.cogig.repository.ServiceRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "*")
public class ServiceController {

    private final ServiceRepository repository;

    public ServiceController(ServiceRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Service> getServices() {
        return repository.findAll();
    }

    @PostMapping
    public Service createService(@RequestBody Service service) {
        return repository.save(service);
    }

    @GetMapping("/{id}")
    public Service getServiceById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }
}