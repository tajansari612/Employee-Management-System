package com.tajwebapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Department {
    @Id
    private Integer id;
    private String name;
}
