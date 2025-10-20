package com.example.hosptial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hosptial.entity.Appointment;
import com.example.hosptial.entity.Doctor;
import com.example.hosptial.repository.AppointmentRepository;
import com.example.hosptial.repository.DoctorRepository;
import com.example.hosptial.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "https://fake-hospital-frontend.onrender.com")
public class DoctorController {

    @Autowired
    private DoctorService service;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping("/profile")
    public Doctor saveOrUpdateProfile(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @GetMapping("/{id}")
    public Doctor getDoctorProfile(@PathVariable Long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    @DeleteMapping("/{id}")
    public String deleteProfile(@PathVariable Long id) {
        doctorRepository.deleteById(id);
        return "Profile deleted successfully";
    }

    @GetMapping("/login")
    public Doctor loginDoctor(@RequestParam String email, @RequestParam String password) {
        return doctorRepository.findByEmail(email)
                .filter(d -> d.getPassword() != null
                && d.getPassword().trim().equals(password.trim()))
                .orElseThrow(() -> new RuntimeException("Login failed: Invalid email or password"));
    }

    @GetMapping("/{id}/appointments")
    public List<Appointment> getMyAppointments(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
        return appointmentRepository.findByDoctorId(id);
    }

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return service.getAllDoctors();
    }

    @GetMapping("/department/{departmentId}")
    public List<Doctor> getDoctorsByDepartment(@PathVariable Long departmentId) {
        return service.getDoctorsByDepartment(departmentId);
    }

    @GetMapping("/search")
    public List<Doctor> searchDoctors(@RequestParam("specialty") String specialty) {
        return service.searchDoctorsBySpecialty(specialty);
    }

}
