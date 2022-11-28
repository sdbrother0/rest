package org.sdbrother.rest.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Person implements Serializable {

    @Id
    private Long id;
    private String firstname;

}
