package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "hangxe")
@NamedQueries({
        @NamedQuery(name = "HangXe.findAll", query = "select h from HangXe h"),
        @NamedQuery(name = "HangXe.findById", query = "select h from HangXe h where h.id = :id")
})
public class HangXe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAHANGXE", nullable = false)
    private Integer id;

    @Size(max = 256)
    @Column(name = "TENHANG", length = 256)
    private String tenhang;

    @Size(max = 256)
    @Column(name = "QUOCGIA", length = 256)
    private String quocgia;

    @Size(max = 256)
    @Column(name = "MOTA", length = 256)
    private String mota;

    @OneToMany(mappedBy = "mahangxe")
    private Set<Xe> xes = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenhang() {
        return tenhang;
    }

    public void setTenhang(String tenhang) {
        this.tenhang = tenhang;
    }

    public String getQuocgia() {
        return quocgia;
    }

    public void setQuocgia(String quocgia) {
        this.quocgia = quocgia;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public Set<Xe> getXes() {
        return xes;
    }

    public void setXes(Set<Xe> xes) {
        this.xes = xes;
    }

}