package com.pessimistic.lock.poc.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPathRequestDto {
    private Long id;
    private String name;
    private String address;
}
