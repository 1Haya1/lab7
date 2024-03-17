package org.example.lms.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Exam {

    private Integer id;

    @NotBlank(message = "Title cannot be blank")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @Positive(message = "Duration must be a positive integer")
    private int durationMinutes;

    @NotBlank(message = "Subject cannot be blank")
    private String subject;

     @NotNull(message = "score cant be null")
    private Double score;


}
