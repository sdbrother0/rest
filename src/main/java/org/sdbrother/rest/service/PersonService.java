package org.sdbrother.rest.service;

import com.hazelcast.config.IndexType;
import com.hazelcast.core.HazelcastInstance;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.sdbrother.rest.db.entity.Person;
import org.sdbrother.rest.db.repository.PersonRepository;
import org.sdbrother.rest.dto.response.PersonDto;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Log4j2
//@CacheConfig(cacheNames = "persons")
public class PersonService {

    private final PersonRepository personRepository;
    private final CacheManager cacheManager;
    private final HazelcastInstance hazelcastInstance;

    private final ModelMapper modelMapper;

    private List<Person> personList;

    @PostConstruct
    public void init() {
        hazelcastInstance.getMap("persons").addIndex(IndexType.HASH, "firstname");
        personList = personRepository.findAll();
        Cache cache = cacheManager.getCache("persons");
        personList
                .stream()
                .forEach(person -> {
                    cache.put(person.getId(), modelMapper.map(person, PersonDto.class));
                });
    }

    public Collection<Object> getAll() {
        return hazelcastInstance.getMap("persons").values();
    }

    @Cacheable(value = "persons", key = "#id")
    public PersonDto findById(long id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return modelMapper.map(personRepository.findById(id).orElseThrow(), PersonDto.class);
    }

    @CacheEvict(value = "persons", key = "#id")
    public void evict(long id) {
        log.info("evict {}", id);
    }


}
