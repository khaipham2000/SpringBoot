package vn.techmaster.joblist.controller;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.joblist.dto.JobRequest;
import vn.techmaster.joblist.model.Job;

@RestController
@RequestMapping("/job")
public class JobController {
    private ConcurrentHashMap<String, Job> jobs;

    public JobController() {
        jobs = new ConcurrentHashMap<>();
        jobs.put("1", new Job("1", "BUSINESS CREDIT SPECIALIST",
                "Perform the management of credit and guarantee operations in accordance with the Bank's regulations and processes and the provisions of the Law.",
                "Ha Noi", 5000, 10000, "dieuj@gmail.com"));
        jobs.put("2",
                new Job("2", "HR SPECIALISTS",
                        "Support the Head of Department in building the annual personnel schedule throughout the system.",
                        "Ha Noi", 1000, 7000, "dzai@gmail.com"));
        jobs.put("3", new Job("3", "INVESTING EXPERT",
                "Macroeconomic analysis and forecast: financial, monetary, economic sectors to evaluate feasible projects to participate in investment and avoid potential risks arising in different industries and all over the world.",
                "Hai Phong", 2000, 14000, "monk12@gmail.com"));
        jobs.put("4", new Job("4", "CUSTOMER RELATIONSHIP SPECIALIST",
                "Marketing, introducing products and services to customers, directly interacting with customers, understanding customer needs for banking services",
                "Ho Chi Minh", 5000, 8000, "moi@gmail.com"));
        jobs.put("5", new Job("5", "TELLER",
                "Welcoming, understanding needs, introducing, advising and supporting customers on banking products and services",
                "Da Nang", 1000, 3000, "abc@gmail.com"));
    }

    @GetMapping
    public List<Job> getJobs() {
        return jobs.values().stream().toList();
    }

    @PostMapping
    public Job createNewBook(@RequestBody JobRequest jobRequest) {
        String uuid = UUID.randomUUID().toString();
        Job newBook = new Job(uuid, jobRequest.title(), jobRequest.description(), jobRequest.location(),
                jobRequest.min_salary(), jobRequest.max_salary(), jobRequest.email());
        jobs.put(uuid, newBook);
        return newBook;
    }

    @GetMapping(value = "/sortbytitle")
    public List<Job> getJobs2() {
        return jobs.values().stream()
                .sorted(Comparator.comparing(Job::getTitle))
                .collect(Collectors.toList());
    }
    //1
    @GetMapping(value = "/sortbylocation")
    public List<Job> getJobs3() {
        return jobs.values().stream()
                .sorted(Comparator.comparing(Job::getLocation))
                .collect(Collectors.toList());
    }

    //2
    @GetMapping(value = "/salary/{salary}")
    public List<Job> getJobs4(@PathVariable("salary") int salary) {
        return jobs.values().stream().filter(i -> (i.getMin_salary() <= (salary)) && (i.getMax_salary() >= (salary)))
                .collect(Collectors.toList());
    }

    //3
    @GetMapping(value = "/keyword/{keyword}")
    public List<Job> getJobs5(@PathVariable("keyword") String keyword) {
        return jobs.values().stream()
                .filter(i -> (i.getTitle().toLowerCase().contains(keyword)) || (i.getDescription().toLowerCase().contains(keyword)))
                .collect(Collectors.toList());
    }

    //4
    @GetMapping(value = "/query")
    public List<Job> getJobs6(@RequestParam("location") String location,
            @RequestParam("keyword") String keyword) {
        return jobs.values().stream()
                .filter(i -> ((i.getTitle().toLowerCase().contains(keyword)) || (i.getDescription().toLowerCase().contains(keyword)))
                        && (i.getLocation().toLowerCase().contains(location)))
                .collect(Collectors.toList());
}
