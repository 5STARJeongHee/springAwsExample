package com.boot.practice.springAwsExample.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
//@AllArgsConstructor
public class HelloResponseDTO {
    private final String name;
    private final int amount;
}
