package org.sdbrother.rest.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Person {

    @Id
    private Long id;
    private String firstname;

}
