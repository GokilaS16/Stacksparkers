package com.cogig.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cogig.model.Worker;
import com.cogig.repository.WorkerRepository;

@RestController
@RequestMapping("/api/workers")
@CrossOrigin(origins = "*")
public class WorkerController {

    private final WorkerRepository workerRepository;

    public WorkerController(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @GetMapping
    public List<Worker> getWorkers(
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) String location) {

        if (skill != null && !skill.isBlank()) {
            return workerRepository.findBySkillContainingIgnoreCase(skill);
        }

        if (location != null && !location.isBlank()) {
            return workerRepository.findByLocationContainingIgnoreCase(location);
        }

        return workerRepository.findAll();
    }

    @PostMapping
    public Worker createWorker(@RequestBody Worker worker) {
        return workerRepository.save(worker);
    }

    @GetMapping("/{id}")
    public Worker getWorkerById(@PathVariable Long id) {
        return workerRepository.findById(id).orElse(null);
    }
}