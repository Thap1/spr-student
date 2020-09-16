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
//        Subject ktpm = subjectRepository.findById();
        model.addAttribute("students", students);
        return "/index";
    }

    @RequestMapping("/newStudent")
    public String newStudent(Model model) {
        Student students = new Student();
        model.addAttribute("students", students);
        return "/createStudent";
    }

    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("students") StudentParam studentParam) {

        Set<String> strSubject = studentParam.getSubjects();
        Set<Subject> subjects = new HashSet<>();
        strSubject.forEach(role -> {
            switch (role) {
                case "KTPM_SUB":
                    Subject ktpm = subjectRepository.findByName(SubjectName.KTPM_SUB);
                    subjects.add(ktpm);
                    break;
                default:
                    Subject dtvt = subjectRepository.findByName(SubjectName.DTVT_SUB);
                    subjects.add(dtvt);
            }
        });
        Student student = new Student(studentParam.getId(), studentParam.getFirstName(), studentParam.getLastName(), studentParam.getAddress(), studentParam.getAge(), studentParam.getMajor(), subjects);
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
