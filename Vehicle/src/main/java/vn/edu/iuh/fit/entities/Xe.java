package vn.edu.iuh.fit.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "xe")
@NamedQueries({
        @NamedQuery(name = "Xe.findAll", query = "select x from Xe x"),
        @NamedQuery(name = "Xe.findByTenxeIgnoreCaseAndGiaxe", query = "select x from Xe x where upper(x.tenxe) = upper(:tenxe)")
})
public class Xe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MAXE", nullable = false)
    private Integer id;

    @Size(max = 256)
    @Column(name = "TENXE", length = 256)
    private String tenxe;

    @Column(name = "GIAXE")
    private Double giaxe;

    @Column(name = "NAMSANXUAT")
    private Integer namsanxuat;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "MAHANGXE")
    private HangXe mahangxe;

    public Xe(String tenXe, double giaXe, int namSX, HangXe hangXe) {
        this.tenxe = tenXe;
        this.giaxe = giaXe;
        this.namsanxuat = namSX;
        this.mahangxe = hangXe;
    }

    public Xe() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenxe() {
        return tenxe;
    }

    public void setTenxe(String tenxe) {
        this.tenxe = tenxe;
    }

    public Double getGiaxe() {
        return giaxe;
    }

    public void setGiaxe(Double giaxe) {
        this.giaxe = giaxe;
    }

    public Integer getNamsanxuat() {
        return namsanxuat;
    }

    public void setNamsanxuat(Integer namsanxuat) {
        this.namsanxuat = namsanxuat;
    }

    public HangXe getMahangxe() {
        return mahangxe;
    }

    public void setMahangxe(HangXe mahangxe) {
        this.mahangxe = mahangxe;
    }

}