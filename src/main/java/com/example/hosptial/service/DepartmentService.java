package com.example.hosptial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hosptial.entity.Department;
import com.example.hosptial.repository.DepartmentRepository;
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }
}
