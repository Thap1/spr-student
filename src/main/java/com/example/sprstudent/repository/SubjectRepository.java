package com.example.sprstudent.repository;

import com.example.sprstudent.model.Subject;
import com.example.sprstudent.model.SubjectName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
//    @Query("SELECT s FROM Subject s " +
//            "WHERE s.subjectName = :search")
//    Subject findByName(@Param("search") SubjectName s );
}
