package org.sdbrother.rest.service;

import lombok.RequiredArgsConstructor;
import org.sdbrother.rest.db.entity.Person;
import org.sdbrother.rest.db.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
