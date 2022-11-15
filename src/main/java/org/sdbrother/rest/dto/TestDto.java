package org.sdbrother.rest.dto;


import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class TestDto {
    @NotNull
    private String test;
    @NotNull
    @Min(10)
    @Max(20)
    private Integer val;
}
