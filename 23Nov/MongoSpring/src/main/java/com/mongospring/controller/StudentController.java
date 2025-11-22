package com.mongospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mongospring.model.Student;
import com.mongospring.repository.StudentRepository;import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // CREATE
    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // READ ALL
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable String id) {
        return studentRepository.findById(id);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable String id, @RequestBody Student student) {
        student.setId(id);
        return studentRepository.save(student);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {
        studentRepository.deleteById(id);
        return "Deleted Successfully";
    }
}
