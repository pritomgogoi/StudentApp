package com.app.student.service;

import com.app.student.model.FakeData;
import com.app.student.model.response.FakeDataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author by Pritom Gogoi
 */
@Service
public class FakeDataService {

    @Autowired
    private RestTemplate restTemplate;

    private final String URI = "https://jsonplaceholder.typicode.com/posts/";

    public FakeDataResponse findTitleByUserId(final int id) {

        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        final FakeData fakeData = restTemplate.exchange(URI + id, HttpMethod.GET,
                new HttpEntity<>(headers), FakeData.class).getBody();
        final FakeDataResponse fakeDataResponse = new FakeDataResponse();
        fakeDataResponse.setTitle(fakeData.getTitle());

        return fakeDataResponse;
    }
}
