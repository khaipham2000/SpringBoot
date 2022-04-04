package vn.techmasterr.jobhunt.controller;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.techmasterr.jobhunt.model.Employer;

@Controller
@RequestMapping("/employer")
public class EmployerController {
    ConcurrentHashMap<String, Employer> employerList =new ConcurrentHashMap<>();
    public EmployerController(){
        employerList.put("01",new Employer("Cường", "cuong.com", "cuong@gmail.com"," Hanoi"));
        employerList.put("02",new Employer("Tuấn", "tuan.com", "tuan@gmail.com"," Hanoi"));
        employerList.put("03",new Employer("Thai", "thai.com", "thai@gmail.com"," Hanoi"));
    }

    @GetMapping("/list")
    public String students(Model model) {
        var employers = employerList.values().stream().collect(Collectors.toList());
        model.addAttribute("employers", employers);
        return "employer/list";
    }

    @GetMapping("/add")
    public String employerForm(Model model){
        model.addAttribute("employer", new Employer("", "", "", ""));
        return "employer/add";
    }

    @PostMapping("/add")
    public String createEmployer(Model model, @ModelAttribute("employer") Employer employer){
        employerList.put(UUID.randomUUID().toString(), new Employer(employer.name(),employer.website(),employer.email(),employer.address()));
        return "redirect:/employer/list";
    }
  
}
