package com.example.hosptial.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hosptial.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
    
}
