package com.cogig.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cogig.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}