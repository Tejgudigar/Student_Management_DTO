package com.example.student_management.service;


import com.example.student_management.dto.StudentDTO;
import com.example.student_management.exception.StudentNotFoundException;
import com.example.student_management.model.Student;
import com.example.student_management.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    // ----------------- Mapping Helpers -----------------
    private StudentDTO convertToDTO(Student student) {
        return new StudentDTO(student.getName(), student.getEmail());
    }
    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        return student;
    }

    // ----------------- Business Methods -----------------


    public List<StudentDTO> getAllStudents() {
        return repo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(int id) {
        Student student = repo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return convertToDTO(student);
    }

    public StudentDTO addStudent(StudentDTO dto) {
        Student student = convertToEntity(dto);
        Student saved = repo.save(student);
        return convertToDTO(saved);
    }

    public StudentDTO updateStudent(int id, StudentDTO dto) {
        Student existing = repo.findById(id)
                               .orElseThrow(() -> new StudentNotFoundException(id));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        Student updated = repo.save(existing);
        return convertToDTO(updated);
    }

    public void deleteStudent(int id) {
        if (!repo.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        repo.deleteById(id);
    }
}

