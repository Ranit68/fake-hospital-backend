package com.example.hosptial.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hosptial.dto.AppointmentRequest;
import com.example.hosptial.entity.Appointment;
import com.example.hosptial.entity.Doctor;
import com.example.hosptial.entity.Patient;
import com.example.hosptial.repository.AppointmentRepository;
import com.example.hosptial.repository.DoctorRepository;
import com.example.hosptial.repository.PatientRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    public Appointment bookAppointment(AppointmentRequest request) {
        // Fetch doctor and patient by IDs
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        LocalDate appointmentDate = LocalDate.parse(request.getDate());

        int count = appointmentRepository.countByDoctorAndDate(doctor, appointmentDate);

        // Create new appointment
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setTime(request.getTime());
        appointment.setDate(appointmentDate);
        appointment.setQueueNumber(count + 1);
        appointment.setBookingDate(LocalDate.now());

        return appointmentRepository.save(appointment);
    }

    public void cancelAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    public List<String> getAvailableSlots(Long doctorId, LocalDate date) {
        List<String> allSlots = List.of("09:00", "10:00", "11:00", "12:00", "14:00", "15:00", "16:00");
        List<Appointment> appointments = appointmentRepository.findByDoctorIdAndDate(doctorId, date);
        List<String> bookedSlots = appointments.stream().map(Appointment::getTime).toList();
        return allSlots.stream().filter(slot -> !bookedSlots.contains(slot)).toList();
    }
}
