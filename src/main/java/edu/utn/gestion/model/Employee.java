package edu.utn.gestion.model;

import edu.utn.gestion.model.util.IConstants;

import javax.persistence.*;
import java.util.*;


/**
 * Created by martin on 08/12/15.
 */
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = false, length = 25)
    private String name;

    @Column(nullable = false, unique = true, length = 11)
    private String cuit;

    @Column(nullable = false, unique = true, length = 10)
    private String phoneNumber;

    @Column(nullable = false, unique = true, length = 30)
    private String email;

    @Column(nullable = false, unique = false, length = 30)
    private String address;

    @OneToMany(targetEntity = Family.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "family_id", referencedColumnName = "id", nullable = false)
    private final List<Family> families = new ArrayList<>();

    @OneToOne
    private SalaryCategory category;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date ingress;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    public Employee() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public SalaryCategory getCategory() {
        return category;
    }

    public void setCategory(SalaryCategory category) {
        this.category = category;
    }

    public Date getIngress() {
        return ingress;
    }

    public void setIngress(Date ingress) {
        this.ingress = ingress;
    }

    public List<Family> getFamilies() {
        return families;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.id)
                .append(IConstants.SCORE)
                .append(this.name)
                .toString();
    }
}
