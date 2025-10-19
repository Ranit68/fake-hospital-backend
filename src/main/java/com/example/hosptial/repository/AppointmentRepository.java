package com.example.hosptial.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hosptial.entity.Appointment;
import com.example.hosptial.entity.Doctor;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    int countByDoctorAndDate(Doctor doctor, LocalDate date);

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByDoctorIdAndDate(Long doctorId, LocalDate date);
}
