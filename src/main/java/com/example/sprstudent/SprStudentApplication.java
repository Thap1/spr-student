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

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
public class SprStudentApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SprStudentApplication.class, args);
    }

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private final MajorRepository majorRepository;
    @Autowired
    private final SubjectRepository subjectRepository;

    @Override
    public void run(String... args) throws Exception {
        Student student = new Student();
        Major major = new Major();
        Set<Subject> subjectss = new HashSet<>();

        Subject subjectKPM = subjectRepository.findById((long) 1)
                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
        subjectss.add(subjectKPM);

        major.setNameMajor("CNTT1");
        major.setMajorId("2");

        student.setFirstName("Hoangsssssssss");
        student.setLastName("Thapaaaaaaaaaaaaaaaaaa");
        student.setAddress("HP");
        student.setAge((long) 22);
        student.setMajor(major);
        student.setSubjects(subjectss);


//        student.setSubjects(null);

        majorRepository.save(major);
        studentRepository.save(student);
    }
}
