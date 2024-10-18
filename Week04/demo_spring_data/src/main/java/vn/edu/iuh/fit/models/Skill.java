package vn.edu.iuh.fit.models;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "skillName", nullable = false, length = 50)
    private String skillName;

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "field", length = 50)
    private String field;

    @OneToMany(mappedBy = "skill")
    private Set<Candidateskill> candidateskills = new LinkedHashSet<>();

    @OneToMany(mappedBy = "skill")
    private Set<Jobskill> jobskills = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Set<Candidateskill> getCandidateskills() {
        return candidateskills;
    }

    public void setCandidateskills(Set<Candidateskill> candidateskills) {
        this.candidateskills = candidateskills;
    }

    public Set<Jobskill> getJobskills() {
        return jobskills;
    }

    public void setJobskills(Set<Jobskill> jobskills) {
        this.jobskills = jobskills;
    }

}