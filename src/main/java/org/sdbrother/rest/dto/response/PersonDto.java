package org.sdbrother.rest.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDto implements Serializable {
    private Long id;
    private String firstname;

}
