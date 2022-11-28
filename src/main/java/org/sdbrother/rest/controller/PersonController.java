package org.sdbrother.rest.controller;

import lombok.RequiredArgsConstructor;
import org.sdbrother.rest.db.entity.Person;
import org.sdbrother.rest.db.repository.PersonRepository;
import org.sdbrother.rest.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/person")
    List<Person> getPerson() {
        return personService.findAll();
    }
}
