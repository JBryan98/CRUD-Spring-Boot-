package com.example.employee.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table (name = "employees")
public class EmployeeEntitiy {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
}
