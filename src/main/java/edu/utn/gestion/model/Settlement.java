package edu.utn.gestion.model;


import javax.persistence.*;
import java.sql.Blob;

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

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private double sueldoBasico;

    @Column(nullable = false)
    private double netPay;

    @Column(nullable = false)
    private double discount;

    @Column(nullable = false)
    private boolean presenteeism;

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

    @Column(nullable = false)
    private double montoPorAntiguedad;

    @Column(nullable = false)
    private int antiguedad;

    private Blob recibo;

    public Settlement() {
    }

    public Settlement(Employee employee, String period) {
        this.employee = employee;
        this.category = employee.getCategory().getName();
        this.period = period;
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

    public String getCategory() {
        return category;
    }

    public double getSueldoBasico() {
        return sueldoBasico;
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

    public boolean isPresenteeism() {
        return presenteeism;
    }

    public void setPresenteeism(boolean presenteeism) {
        this.presenteeism = presenteeism;
    }

    public void setSueldoBasico(double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setPresenteeismAmount(double presenteeismAmount) {
        this.presenteeismAmount = presenteeismAmount;
    }

    public void setRetireAmount(double retireAmount) {
        this.retireAmount = retireAmount;
    }

    public void setLaw19032(double law19032) {
        this.law19032 = law19032;
    }

    public void setSocialCare(double socialCare) {
        this.socialCare = socialCare;
    }

    public void setFaecys(double faecys) {
        this.faecys = faecys;
    }

    public void setRemunerationAmount(double remunerationAmount) {
        this.remunerationAmount = remunerationAmount;
    }

    public void setSyndicate(double syndicate) {
        this.syndicate = syndicate;
    }

    public Blob getRecibo() {
        return recibo;
    }

    public void setRecibo(Blob recibo) {
        this.recibo = recibo;
    }

    public long getId() {
        return id;
    }

    public double getMontoPorAntiguedad() {
        return montoPorAntiguedad;
    }

    public void setMontoPorAntiguedad(double montoPorAntiguedad) {
        this.montoPorAntiguedad = montoPorAntiguedad;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}
