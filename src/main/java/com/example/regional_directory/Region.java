package com.example.regional_directory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Region {
    private Long id;
    private String name;
    private String shortName;
}
