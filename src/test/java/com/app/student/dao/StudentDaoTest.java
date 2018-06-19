package com.app.student.dao;

import com.app.student.Application;
import com.app.student.config.DatabaseConfig;
import com.app.student.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author by Pritom Gogoi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        Application.class,
        DatabaseConfig.class
})
@ActiveProfiles("test")
public class StudentDaoTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void findAllTest() {
        final List<Student> actual = studentDao.findAll();
        assertFalse(actual.isEmpty());
    }

    @Test
    public void findByIdTest() {
        Student actual = studentDao.findById(10002L);

        final Student expected = new Student();
        expected.setId(10002L);
        expected.setName("Ravi");
        expected.setPassportNumber("A1234568");

        assertEquals(expected, actual);
    }

    @Test(expected = EmptyResultDataAccessException.class)
    @Transactional
    @Rollback()
    public void deleteByIdTest() {
        studentDao.deleteById(10002L);
        studentDao.findById(10002L);
    }

    @Test
    @Transactional
    @Rollback()
    public void insertTest(){
        final Student newStudent = new Student();
        newStudent.setId(10003L);
        newStudent.setName("Pritom");
        newStudent.setPassportNumber("B12345678");

        studentDao.insert(newStudent);

        assertEquals(3, studentDao.findAll().size());
    }

    @Test
    @Transactional
    @Rollback()
    public void updateTest(){

        final Student student = new Student();
        student.setId(10002L);
        student.setName("Pritom");
        student.setPassportNumber("B12345678");

        studentDao.update(student);

        Student updatedStudent = studentDao.findById(10002L);

        assertEquals(updatedStudent.getName(),"Pritom");
        assertEquals(updatedStudent.getPassportNumber(),"B12345678");



    }
}
