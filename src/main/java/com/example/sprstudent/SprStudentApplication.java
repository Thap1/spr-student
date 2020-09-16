package com.example.sprstudent;

import com.example.sprstudent.model.Major;
import com.example.sprstudent.model.Student;
import com.example.sprstudent.model.Subject;
import com.example.sprstudent.model.SubjectName;
import com.example.sprstudent.repository.MajorRepository;
import com.example.sprstudent.repository.StudentRepository;
import com.example.sprstudent.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class SprStudentApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SprStudentApplication.class, args);
        System.out.println("----------------------Constructor");
    }

}
