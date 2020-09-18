package com.example.sprstudent.controller;

import com.example.sprstudent.model.Major;
import com.example.sprstudent.repository.MajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MajorController {
    @Autowired
    MajorRepository majorRepository;

    @RequestMapping("/major")
    public String majorList(Model model) {
        List<Major> majors = majorRepository.findAll();
        model.addAttribute("majors", majors);
        return "/major";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveMajor(@ModelAttribute("major") Major major) {
        majorRepository.save(major);
        return "redirect:/major";
    }


    @RequestMapping("/newMajor")
    public String majorNew(Model model) {
        Major major = new Major();
        model.addAttribute("major", major);
        return "/createMajor";
    }

    @RequestMapping("/majorEdit/{id}")
    public ModelAndView majorEdit(@PathVariable(name = "id") long id) {
        ModelAndView mav = new ModelAndView("/majorEdit");
        Major major = majorRepository.getOne(id);
        mav.addObject("major", major);
        System.out.println(major);
        return mav;
    }

    @RequestMapping("/majorDelete/{id}")
    public String majorDelete(@PathVariable(name = "id") long id) {
        majorRepository.deleteById(id);
        return "redirect:/major";
    }

}
