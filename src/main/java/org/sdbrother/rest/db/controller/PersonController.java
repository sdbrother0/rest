package org.sdbrother.rest.db.controller;

import lombok.RequiredArgsConstructor;
import org.sdbrother.rest.db.entity.Person;
import org.sdbrother.rest.db.repository.PersonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;

    @GetMapping("/person")
    List<Person> getPerson() {
        return personRepository.findAll();
    }
}
