package com.buzatto;

import com.buzatto.dto.CourseDTO;
import com.buzatto.enums.Category;
import com.buzatto.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CrudSpringApplicationTests {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void testCreateCourseSuccess() {
        final String name = "Valid Name";
        final String slug = "valid-name";
        final String category = Category.BACK_END.getValue();

        webTestClient
                .post()
                .uri("/api/courses")
                .bodyValue(new CourseDTO(null, null, null,
                        name, null, category, new ArrayList<>()))
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("name").isEqualTo(name)
                .jsonPath("category").isEqualTo(category)
                .jsonPath("slug").isEqualTo(slug)
                .jsonPath("createdDate").isNotEmpty()
                .jsonPath("lastModifiedDate").isNotEmpty();
    }

    @Test
    public void testCreateCourseFailure() {
        final String name = "";
        final String slug = "";
        final String category = "";

        webTestClient
                .post()
                .uri("/api/courses")
                .bodyValue(new CourseDTO(null, null, null,
                        name, slug, category, new ArrayList<>()))
                .exchange()
                .expectStatus().isBadRequest();
    }

}
