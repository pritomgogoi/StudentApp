package com.app.student.util;

import com.app.student.model.Student;

import java.util.Arrays;
import java.util.List;

/**
 * @author by Pritom Gogoi
 */
public class TestUtil {

    public static List<Student> buildFakeStudents() {

        final Student student1 = new Student();
        student1.setId(1L);
        student1.setName("Tom");
        student1.setPassportNumber("PQWE");

        final Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Jerry");
        student2.setPassportNumber("MKIUY");

        return Arrays.asList(student1, student2);
    }
}
