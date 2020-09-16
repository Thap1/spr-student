package com.example.sprstudent.param;

import com.example.sprstudent.model.Major;
import com.example.sprstudent.model.Subject;
import com.example.sprstudent.model.SubjectName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentParam {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private Long age;
    private Major major;
    private Set<String> subjects;

}
