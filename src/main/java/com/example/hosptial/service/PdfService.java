package com.example.hosptial.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.example.hosptial.entity.Appointment;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

@Service
public class PdfService {

    public ByteArrayInputStream generateAppointmentPdf(Appointment appointment) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Paragraph title = new Paragraph("Appointment Confirmation", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("Unique ID: " + appointment.getId()));
            document.add(new Paragraph("Booking Date: " + appointment.getBookingDate()));
            document.add(new Paragraph("Appointment Date: " + appointment.getDate()));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            addTableCell(table, "Doctor Name");
            addTableCell(table, appointment.getDoctor().getName());

            addTableCell(table, "Department");
            addTableCell(table, appointment.getDoctor().getDepartment().getName());

            addTableCell(table, "Patient Name");
            addTableCell(table, appointment.getPatient().getName());

            addTableCell(table, "Mobile Number");
            addTableCell(table, appointment.getPatient().getPhone());

            addTableCell(table, "Email");
            addTableCell(table, appointment.getPatient().getEmail());

            document.add(table);

            document.add(new Paragraph("Please reach the hospital 15 minutes before your appointment."));
            document.add(new Paragraph("Thank you for booking with us!"));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

    private void addTableCell(PdfPTable table, String value) {
        PdfPCell cell = new PdfPCell(new Phrase(value));
        cell.setPadding(8f);
        table.addCell(cell);
    }
}
