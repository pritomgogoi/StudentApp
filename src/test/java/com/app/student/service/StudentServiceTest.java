package com.app.student.service;

import com.app.student.dao.StudentDao;
import com.app.student.model.Student;
import com.app.student.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author by Pritom Gogoi
 */
public class StudentServiceTest {

    @InjectMocks
    StudentService studentService;

    @Mock
    StudentDao studentDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {

        List<Student> expected = TestUtil.buildFakeStudents();

        Mockito.when(studentDao.findAll()).thenReturn(expected);

        assertEquals(expected, studentService.findAll());

    }
}
