package com.app.student.integrationtests;

import com.app.student.model.response.FakeDataResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @author by Pritom Gogoi
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FakeDataIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testFindTitleById() throws JsonProcessingException {

        final FakeDataResponse fakeDataResponse = new FakeDataResponse();
        fakeDataResponse.setTitle("sunt aut facere repellat provident occaecati excepturi optio reprehenderit");

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        FakeDataResponse act = testRestTemplate.exchange("/v1/fakedata/1", HttpMethod.GET,
                new HttpEntity<>(headers), FakeDataResponse.class).getBody();

        assertEquals(fakeDataResponse.getTitle(), act.getTitle());
    }
}
