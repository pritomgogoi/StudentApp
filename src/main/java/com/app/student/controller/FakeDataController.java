package com.app.student.controller;

import com.app.student.model.response.FakeDataResponse;
import com.app.student.service.FakeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author by Pritom Gogoi
 */
@RestController
@RequestMapping("/v1/fakedata/")
public class FakeDataController {

    @Autowired
    private FakeDataService fakeDataService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FakeDataResponse findTitlebyId(@PathVariable final int id) {
        return fakeDataService.findTitleByUserId(id);
    }
}
