package com.app.student.controller;

import com.app.student.model.Student;
import com.app.student.service.StudentService;
import com.app.student.util.TestUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author by Pritom Gogoi
 */
public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private ObjectMapper objectMapper;

    private List<Student> fakeStudents;

    private Student testStudent;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        fakeStudents = TestUtil.buildFakeStudents();

        testStudent = new Student();
        testStudent.setId(1l);
        testStudent.setName("Pritom");
        testStudent.setPassportNumber("P001");

    }


    @Test
    public void findAllStudentsTest() throws Exception {

        when(studentService.findAll()).thenReturn(fakeStudents);

        final String actualResponse = this.mockMvc.perform(get("/v1/students/"))
                .andExpect(status().isOk()).
                        andReturn().getResponse().getContentAsString();

        final TypeReference<List<Student>> studentMap = new TypeReference<List<Student>>() {
        };

        final List<Student> actualStudents = objectMapper.readValue(actualResponse, studentMap);

        assertEquals(actualStudents, fakeStudents);


    }

    @Test
    public void testFindStudentById() throws Exception {


        when(studentService.findStudentById(1)).thenReturn(testStudent);

        final String actualResponse = this.mockMvc.perform(get("/v1/students/1"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        final Student actualStudent = objectMapper.readValue(actualResponse, Student.class);

        assertEquals(testStudent, actualStudent);

    }

    @Test
    public void testDeleteStudent() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.
                delete("/v1/students/{id}", 1l)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddStudent() throws Exception {

        final String studentJsonString = objectMapper.writeValueAsString(testStudent);

        this.mockMvc.perform(post("/v1/students/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(studentJsonString))
                .andExpect(status().isOk());

    }

}
