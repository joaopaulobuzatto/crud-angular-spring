package com.buzatto;

import com.buzatto.enums.Category;
import com.buzatto.model.Course;
import com.buzatto.model.Lesson;
import com.buzatto.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(CourseRepository courseRepository) {
        return args -> {
            courseRepository.deleteAll();

            Course course = new Course();
            course.setName("Angular com Spring");
            course.setCategory(Category.FRONT_END);

            Lesson lesson = new Lesson();
            lesson.setName("Introdução");
            lesson.setYoutubeUrl("watch?v1");
            lesson.setCourse(course);
            course.getLessons().add(lesson);

            Lesson lesson1 = new Lesson();
            lesson1.setName("Angular");
            lesson1.setYoutubeUrl("watch?v2");
            lesson1.setCourse(course);
            course.getLessons().add(lesson1);

            courseRepository.save(course);
        };
    }

}
