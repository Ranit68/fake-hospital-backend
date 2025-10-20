package com.example.hosptial.controller;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hosptial.dto.AppointmentRequest;
import com.example.hosptial.entity.Appointment;
import com.example.hosptial.repository.AppointmentRepository;
import com.example.hosptial.service.AppointmentService;
import com.example.hosptial.service.PdfService;

@RestController
@RequestMapping("/api/appointment")
@CrossOrigin(origins = "https://fake-hospital-frontend.onrender.com")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    @Autowired
    private PdfService pdfService;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Get all appointments
    @GetMapping
    public List<Appointment> getAllAppointment() {
        return service.getAllAppointment();
    }

    // Book an appointment
    @PostMapping
    public Appointment bookAppointment(@RequestBody AppointmentRequest request) {
        return service.bookAppointment(request);
    }

    // Cancel appointment by ID
    @DeleteMapping("/{id}")
    public void cancelAppointment(@PathVariable Long id) {
        service.cancelAppointment(id);
    }

    // Get available slots for a doctor on a date
    @GetMapping("/slots/{doctorId}/{date}")
    public List<String> getAvailableSlots(
            @PathVariable Long doctorId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return service.getAvailableSlots(doctorId, date);
    }

    // Generate PDF for an appointment
    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        ByteArrayInputStream pdf = pdfService.generateAppointmentPdf(appointment);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=appointment_" + id + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf.readAllBytes());
    }
}
