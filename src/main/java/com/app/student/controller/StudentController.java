package com.app.student.controller;

import com.app.student.model.Student;
import com.app.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by Pritom Gogoi
 */
@RestController
@RequestMapping("v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Student> findAllStudents() {
        return studentService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Student findStudentById(@PathVariable final long id) {

        return studentService.findStudentById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteStudentById(@PathVariable final long id) {
        studentService.deleteStudentById(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

}
