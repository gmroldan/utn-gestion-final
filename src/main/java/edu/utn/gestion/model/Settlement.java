package edu.utn.gestion.model;

import javax.persistence.*;

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

    @Column(nullable = false)
    private double presenteeismAmount;

    @Column(nullable = false)
    private double retireAmount;

    @Column(nullable = false)
    private double law19032;

    @Column(nullable = false)
    private double socialCare;

    @Column(nullable = false)
    private double faecys;

    @Column(nullable = false)
    private double remunerationAmount;

    @Column(nullable = false)
    private double syndicate;

    public Settlement(Employee employee, String period) {
        this.employee = employee;
        this.category = employee.getCategory();
        this.period = period;
    }

    public long getId() {
        return id;
    }

    public double getNetPay() {
        return netPay;
    }

    public double getDiscount() {
        return discount;
    }

    public String getPeriod() {
        return period;
    }

    public Employee getEmployee() {
        return employee;
    }

    public EmployeeCategoryEnum getCategory() {
        return category;
    }

    public double getGrossSalary() {
        return grossSalary;
    }

    public void calculateSettlement(double grossSalary) {

        double remuneration = grossSalary;
        this.grossSalary = grossSalary;

        //antiguedad 2%

        this.presenteeismAmount = grossSalary * 0.083;
        remuneration = remuneration + presenteeismAmount;

        this.retireAmount = remuneration * 0.11;
        this.law19032 = remuneration * 0.03;
        this.socialCare = remuneration * 0.03;
        this.faecys = remuneration * 0.005;
        this.syndicate = remuneration * 0.02;

        this.discount = this.retireAmount + this.law19032 + this.socialCare + this.faecys + this.syndicate;
        this.netPay = grossSalary - this.discount;
        this.remunerationAmount = remuneration;
    }

    public double getPresenteeismAmount() {
        return presenteeismAmount;
    }

    public double getRetireAmount() {
        return retireAmount;
    }

    public double getLaw19032() {
        return law19032;
    }

    public double getSocialCare() {
        return socialCare;
    }

    public double getFaecys() {
        return faecys;
    }

    public double getRemunerationAmount() {
        return remunerationAmount;
    }

    public double getSyndicate() {
        return syndicate;
    }
}
