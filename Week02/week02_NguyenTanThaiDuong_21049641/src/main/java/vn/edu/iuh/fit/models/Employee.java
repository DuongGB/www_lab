/*
 * @ {#} Employee.java   1.0     11/2/2024
 *
 * Copyright (c) 2024 IUH. All rights reserved.
 */

package vn.edu.iuh.fit.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.edu.iuh.fit.enums.EmployeeStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/*
 * @description:
 * @author: Nguyen Tan Thai Duong
 * @date:   11/2/2024
 * @version:    1.0
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "emp_id", columnDefinition = "BIGINT(20)")
    private long id;
    @Column(name = "dob", columnDefinition = "DATETIME(6)")
    private LocalDateTime dob;
    @Column(name = "email", columnDefinition = "VARCHAR(150)")
    private String email;
    @Column(name = "address", columnDefinition = "VARCHAR(250)")
    private String address;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status", columnDefinition = "INT(11)")
    private EmployeeStatus status;
    @Column(name = "phone", columnDefinition = "VARCHAR(15)")
    private String phone;
    @Column(name = "full_name", columnDefinition = "VARCHAR(150)")
    private String fullName;
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Order> lstOrder;

    public Employee(long id, LocalDateTime dob, String email, String address, EmployeeStatus status, String phone, String fullName) {
        this.id = id;
        this.dob = dob;
        this.email = email;
        this.address = address;
        this.status = status;
        this.phone = phone;
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}

