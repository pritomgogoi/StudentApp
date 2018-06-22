package com.app.student.service;

import com.app.student.model.response.FakeDataResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author by Pritom Gogoi
 */
public class FakeDataServiceTest {

    private MockRestServiceServer mockServer;

    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        this.restTemplate = new RestTemplate();
        this.mockServer = MockRestServiceServer.bindTo(this.restTemplate).ignoreExpectOrder(true).build();
    }

    @Test
    public void test() throws JsonProcessingException {
        final FakeDataResponse fakeDataResponse = new FakeDataResponse();
        fakeDataResponse.setTitle("hello world");

        final ObjectMapper mapper = new ObjectMapper();
        String expectedString = mapper.writeValueAsString(fakeDataResponse);

        this.mockServer.expect(requestTo("/v1/fakedata/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(expectedString, MediaType.APPLICATION_JSON));

        FakeDataResponse actual = restTemplate.
                getForEntity("/v1/fakedata/{id}", FakeDataResponse.class, 1).getBody();

        this.mockServer.verify();

        System.out.println("actual : "+actual.getTitle());
        System.out.println("expected : "+fakeDataResponse.getTitle());

    }
}
