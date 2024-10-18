package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ColumnDefault("''")
    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @OneToMany(mappedBy = "job")
    private Set<Jobskill> jobskills = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Jobskill> getJobskills() {
        return jobskills;
    }

    public void setJobskills(Set<Jobskill> jobskills) {
        this.jobskills = jobskills;
    }

}