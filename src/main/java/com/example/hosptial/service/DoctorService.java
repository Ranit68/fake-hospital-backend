package com.example.hosptial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hosptial.entity.Doctor;
import com.example.hosptial.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }
        public List<Doctor> getDoctorsByDepartment(Long departmentId) {
        return doctorRepository.findByDepartmentId(departmentId);
    }
    public List<Doctor> searchDoctorsBySpecialty(String specialty) {
    return doctorRepository.findBySpecialtyContainingIgnoreCase(specialty);
}

    
}
