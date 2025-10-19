package com.example.hosptial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hosptial.entity.Department;
import com.example.hosptial.service.DepartmentService;
@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "http://localhost:3000")
public class DepartmentController {
    @Autowired
    private DepartmentService service;
    @GetMapping
    public List<Department> getAllDepartments() {
        return service.getAllDepartments();
    }
}

