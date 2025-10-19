package com.example.hosptial.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hosptial.entity.Appointment;
import com.example.hosptial.entity.Doctor;
import com.example.hosptial.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll();
    }

    public Appointment bookAppointment(Appointment appointment) {
        LocalDate appointmentDate = appointment.getDate();
        Doctor doctor = appointment.getDoctor();

        int count = appointmentRepository.countByDoctorAndDate(doctor, appointmentDate);
        appointment.setQueueNumber(count + 1);

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
