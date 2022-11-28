package org.sdbrother.rest.service;

import com.hazelcast.config.IndexType;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCache;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.sdbrother.rest.db.entity.Person;
import org.sdbrother.rest.db.repository.PersonRepository;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Log4j2
@CacheConfig(cacheNames = "persons")
public class PersonService {

    private final PersonRepository personRepository;
    private final CacheManager cacheManager;
    private final HazelcastInstance hazelcastInstance;

    public List<Person> findAll() {
        hazelcastInstance.getMap("persons").addIndex(IndexType.HASH, "firstname");
        List<Person> personList = personRepository.findAll();
        Cache cache = cacheManager.getCache("persons");
        personList
                .stream()
                .forEach(person -> {
                    cache.put(person.getId(), person);
                });
        return personList;
    }

    public Collection<Object> getAll() {
        return hazelcastInstance.getMap("persons").values();
    }

    @Cacheable(value = "persons", key = "#id")
    public Person findById(long id) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return personRepository.findById(id).orElseThrow();
    }

    @CacheEvict(value = "persons", key = "#id")
    public void evict(long id) {
        log.info("evict {}", id);
    }


}
