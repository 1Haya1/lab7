package org.example.lms.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Student {

    private Integer id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Positive(message = "Age must be a positive integer")
    private int age;
}
