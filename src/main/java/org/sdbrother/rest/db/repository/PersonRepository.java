package org.sdbrother.rest.db.repository;

import org.sdbrother.rest.db.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
