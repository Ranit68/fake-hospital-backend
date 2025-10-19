package com.example.hosptial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hosptial.entity.Patient;
public interface PatientRepository extends JpaRepository<Patient, Long> {
}