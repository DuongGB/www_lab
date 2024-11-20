package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "job")
public class Job {
    @Id
    @Column(name = "job_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_desc", nullable = false, length = 2000)
    private String jobDesc;

    @Column(name = "job_name", nullable = false)
    private String jobName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company")
    private Company company;

    @OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
    private List<JobSkill> jobSkills = new ArrayList<>();

    public Job() {
    }

    public Job(String jobDesc, String jobName, Company company) {
        this.jobDesc = jobDesc;
        this.jobName = jobName;
        this.company = company;
    }
}