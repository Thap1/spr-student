package com.example.sprstudent.repository;

import com.example.sprstudent.model.Subject;
import com.example.sprstudent.model.SubjectName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
