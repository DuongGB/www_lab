package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "role")
@NamedQueries({
        @NamedQuery(name = "Role.findAll", query = "select r from Role r"),
        @NamedQuery(name = "Role.findByRoleId", query = "select r from Role r where r.roleId = :roleId"),
        @NamedQuery(name = "Role.existsByRoleId", query = "select (count(r) > 0) from Role r where r.roleId = :roleId"),
        @NamedQuery(name = "Role.updateRoleByRoleId", query = "update Role r set r.roleName = :roleName, r.description = :description, r.status = :status where r.roleId = :roleId"),
        @NamedQuery(name = "Role.deleteByRoleId", query = "delete from Role r where r.roleId = :roleId"),
        @NamedQuery(name = "Role.isAdministrator", query = "select (count(r) > 0) from Role r inner join GrantAccess ga on r.roleId = ga.role.roleId inner join Account a on ga.account.accountId = a.accountId where a.accountId = :accountId and r.roleName = 'administrator'")
})
public class Role {
    @Id
    @Column(name = "role_id", nullable = false, length = 50)
    private String roleId;

    @Column(name = "role_name", nullable = false, length = 50)
    private String roleName;

    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "status", nullable = false)
    private Byte status;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

}