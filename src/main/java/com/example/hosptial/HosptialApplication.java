package com.example.hosptial;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.hosptial.entity.Department;
import com.example.hosptial.entity.Doctor;
import com.example.hosptial.entity.Patient;
import com.example.hosptial.repository.DepartmentRepository;
import com.example.hosptial.repository.DoctorRepository;
import com.example.hosptial.repository.PatientRepository;

@SpringBootApplication
public class HosptialApplication {


	@Bean
CommandLineRunner initData(DepartmentRepository deptRepo, DoctorRepository docRepo, PatientRepository patRepo) {
    return args -> {
        if (deptRepo.count() == 0) {
            Department ent = new Department(); ent.setName("ENT"); deptRepo.save(ent);
            Department cardio = new Department(); cardio.setName("Cardiology"); deptRepo.save(cardio);

            Doctor doc1 = new Doctor(); doc1.setName("Dr. Smith"); doc1.setSpecialty("Otolaryngologist"); doc1.setDepartment(ent);doc1.setConsultationFee(500);doc1.setEducation("MBBS");doc1.setExperience("5 years");doc1.setLanguages("Hindi");doc1.setImgeUrl("https://t4.ftcdn.net/jpg/01/34/29/31/360_F_134293169_ymHT6Lufl0i94WzyE0NNMyDkiMCH9HWx.jpg"); docRepo.save(doc1);
            Doctor doc2 = new Doctor(); doc2.setName("Dr. Johnson"); doc2.setSpecialty("Cardiologist"); doc2.setDepartment(cardio); doc2.setConsultationFee(500);doc2.setEducation("MBBS");doc2.setExperience("5 years");doc2.setLanguages("Hindi");doc2.setImgeUrl("https://t4.ftcdn.net/jpg/01/34/29/31/360_F_134293169_ymHT6Lufl0i94WzyE0NNMyDkiMCH9HWx.jpg"); docRepo.save(doc2);

            Patient pat1 = new Patient(); pat1.setName("John Doe"); pat1.setEmail("john@example.com"); pat1.setPhone("1234567890"); patRepo.save(pat1);
        }
    };
}

	public static void main(String[] args) {
		SpringApplication.run(HosptialApplication.class, args);
	
		
	
	
	}

}
