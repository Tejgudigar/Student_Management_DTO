package com.example.student_management.controller;

import com.example.student_management.dto.StudentDTO;
import com.example.student_management.exception.StudentNotFoundException;
import com.example.student_management.model.Student;
import com.example.student_management.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    //Get /students
    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return service.getAllStudents();
    }

    //Get /students/{id}
    @GetMapping("/{id}")
    public StudentDTO getStudentById(@PathVariable int id) {
        return service.getStudentById(id);
    }

    //post /students
    @PostMapping
    public StudentDTO addStudent(@Valid @RequestBody StudentDTO dto) {
        return service.addStudent(dto);
    }

    //PUT /students/id
    @PutMapping("/{id}")
    public StudentDTO addStudent(@PathVariable int id, @Valid @RequestBody StudentDTO dto) {
        return service.updateStudent(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
    }

}
