package vn.techmaster.job_hunt.repository;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import vn.techmaster.job_hunt.model.Applicant;

@Repository
public class ApplicantRepo {
    private ConcurrentHashMap<String, Applicant> listApplicant = new ConcurrentHashMap<>();

    public ApplicantRepo() {

    }

    public Collection<Applicant> getAll() {
        return listApplicant.values();
    }

    public Applicant addApplicant(Applicant applicant) {
        String id = UUID.randomUUID().toString();
        applicant.setId(id);
        listApplicant.put(id, applicant);
        return applicant;
    }

    public Applicant findApplicantById(String id) {
        return listApplicant.get(id);
    }

    public Applicant deleteById(String id) {
        return listApplicant.remove(id);
    }

    public void update(Applicant applicant) {
        listApplicant.put(applicant.getId(), applicant);
    }

    @PostConstruct
    public void addSomeData() {
        this.addApplicant(Applicant.builder()
                .name("FPT")
                .email("https://fpt.com.vn")
                .phoneNumber("nvfpt@gmail.com")
                .skill_description("logo_fpt.png")
                .choose_company("FPT")
                .choose_job("Front-end")
                .choose_city("HaNoi")
                .build());
    }
}
