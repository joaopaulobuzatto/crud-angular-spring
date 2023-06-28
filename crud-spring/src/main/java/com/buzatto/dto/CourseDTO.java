package com.buzatto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        LocalDateTime createdDate,
        LocalDateTime lastModifiedDate,
        @NotBlank @NotNull @Length(min = 3, max = 100) String name,
        String slug,
        @NotNull String category,
        List<LessonDTO> lessons
) {
}
