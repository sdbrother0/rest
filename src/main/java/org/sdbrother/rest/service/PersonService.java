package org.sdbrother.rest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.sdbrother.rest.db.entity.Person;
import org.sdbrother.rest.db.repository.PersonRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
public class PersonService {

    private final PersonRepository personRepository;
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Cacheable(value = "persons", key = "#id")
    public Person findById(long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @CacheEvict(value = "persons", key = "#id")
    public void evict(long id) {
        log.info("evict {}", id);
    }


}
