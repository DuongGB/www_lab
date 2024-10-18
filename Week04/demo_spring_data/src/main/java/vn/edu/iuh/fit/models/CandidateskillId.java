package vn.edu.iuh.fit.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CandidateskillId implements Serializable {
    private static final long serialVersionUID = 3851772979892042396L;
    @Column(name = "candidateId", nullable = false)
    private Integer candidateId;

    @Column(name = "skillId", nullable = false)
    private Integer skillId;

    public Integer getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Integer candidateId) {
        this.candidateId = candidateId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CandidateskillId entity = (CandidateskillId) o;
        return Objects.equals(this.skillId, entity.skillId) &&
                Objects.equals(this.candidateId, entity.candidateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, candidateId);
    }

}