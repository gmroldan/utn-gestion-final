package edu.utn.gestion.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Benja on 22/02/2016.
 */
@Entity
@Table(name = "settlement")
public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)

    private String period;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Enumerated(EnumType.STRING)
    private EmployeeCategoryEnum category;

    @Column(nullable = false)
    private double grossSalary;

    @Column(nullable = false)
    private double netPay;

    @Column(nullable = false)
    private double discount;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public EmployeeCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(EmployeeCategoryEnum category) {
        this.category = category;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void setGrossSalary(double grossSalary) {
        this.grossSalary = grossSalary;
    }
}
