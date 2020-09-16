package com.example.sprstudent.controller;

import com.example.sprstudent.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @Autowired
    StudentRepository repository;
    @RequestMapping("/student")
    public String listStudent(){
        System.out.println("sdafsdfasfd");
        return "/index";
    }
}
