package com.app.student.service;

import com.app.student.dao.StudentDao;
import com.app.student.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by Pritom Gogoi
 */
@Service
public class StudentService {

    @Autowired
    private StudentDao repository;

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Student findStudentById(long id) {
        return repository.findById(id);
    }

    public void deleteStudentById(long id) {
        repository.deleteById(id);
    }

    public void addStudent(final Student student) {
        repository.insert(student);
    }

    public void updateStudent(final Student student) {
        repository.update(student);
    }
}
