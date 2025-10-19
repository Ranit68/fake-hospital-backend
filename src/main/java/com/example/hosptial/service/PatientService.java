package com.example.hosptial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hosptial.entity.Patient;
import com.example.hosptial.repository.PatientRepository;
@Service
public class PatientService {
    @Autowired
    private PatientRepository repository;
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }
    public Patient savePatient(Patient patient) {
        return repository.save(patient);
    }
}