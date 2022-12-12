package org.sdbrother.rest.controller;

import java.util.Collection;

import lombok.RequiredArgsConstructor;
import org.sdbrother.rest.dto.response.PersonDto;
import org.sdbrother.rest.service.PersonService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/persons")
    public Collection<Object> getPersons() {
        return personService.getAll();
    }

    @GetMapping("/person/{id}")
    public PersonDto getPerson(@PathVariable Long id) {
        return personService.findById(id);
    }

    @GetMapping("/evict/{id}")
    public String evict(@PathVariable Long id) {
        personService.evict(id);
        return "evict " + id;
    }


}
