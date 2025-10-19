package com.example.hosptial.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hosptial.entity.Doctor;

public interface  DoctorRepository extends JpaRepository<Doctor, Long>{
    List<Doctor> findByDepartmentId(Long departmentId);
    List<Doctor> findBySpecialtyContainingIgnoreCase(String specialty);
    Optional<Doctor> findByEmail(String email);
}
