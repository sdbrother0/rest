package org.sdbrother.rest.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.sdbrother.rest.dto.TestDto;
import org.sdbrother.rest.exception.TestException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Log4j2
public class TestController {

    @PostMapping("/test")
    public void test(@Valid @RequestBody TestDto testDto) {
       log.info(testDto);
       if (testDto.getVal().equals(15)) {
           throw new TestException("15 - bad!!!!!");
       }
    }

}
