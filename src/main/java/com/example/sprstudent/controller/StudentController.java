package com.example.sprstudent.controller;

import com.example.sprstudent.model.Major;
import com.example.sprstudent.model.Student;
import com.example.sprstudent.model.Subject;
import com.example.sprstudent.model.SubjectName;
import com.example.sprstudent.param.StudentParam;
import com.example.sprstudent.repository.MajorRepository;
import com.example.sprstudent.repository.StudentRepository;
import com.example.sprstudent.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MajorRepository majorRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping("/")
    public String listStudent(Model model) {
        List<Student> students = studentRepository.findAll();

        model.addAttribute("students", students);
        return "/index";
    }

    @RequestMapping("/newStudent")
    public String newStudent(Model model) {
        Student students = new Student();
        model.addAttribute("students", students);
        List<Major> major = majorRepository.findAll();
        model.addAttribute("majorList", major);
        return "/createStudent";
    }

    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("students") StudentParam studentParam) {
        Set<String> strSubject = studentParam.getSubjects();
        Set<Subject> subjects = new HashSet<>();
        strSubject.forEach(sub -> {
            switch (sub) {
                case "1":
                    Subject bac = subjectRepository.findByName(SubjectName.BAC);
                    subjects.add(bac);
                    break;
                case "2":
                    Subject trung = subjectRepository.findByName(SubjectName.TRUNG);
                    subjects.add(trung);
                    break;
                default:
                    Subject nam = subjectRepository.findByName(SubjectName.NAM);
                    subjects.add(nam);
            }
        });
//        Student news
        Student student = new Student();
        student.setId(studentParam.getId());
        student.setFirstName(studentParam.getFirstName());
        student.setLastName(studentParam.getLastName());
        student.setAddress(studentParam.getAddress());
        student.setAge(studentParam.getAge());
        student.setMajor(studentParam.getMajor());
        student.setSubjects(subjects);
        studentRepository.save(student);
        return "redirect:/";
    }
    @RequestMapping("/studentEdit/{id}")
    public ModelAndView studentEdit(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("/studentEdit");
        Student student = studentRepository.getOne(id);
        mav.addObject("student", student);
        return mav;
    }

    @RequestMapping("/studentSubject/{id}")
    public String studentSubject(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("/studentEdit");
        Subject subject =  subjectRepository.getOne(id);
        System.out.println(subject);
        mav.addObject("subjects", subject);
        return "/studentSubject";
    }

    @RequestMapping("/studentDelete/{id}")
    public String studentDelete(@PathVariable(name = "id") long id) {
        studentRepository.deleteById(id);
        return "redirect:/";
    }
}
