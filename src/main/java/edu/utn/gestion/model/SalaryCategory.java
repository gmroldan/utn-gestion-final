package edu.utn.gestion.model;

import edu.utn.gestion.model.util.IConstants;

import javax.persistence.*;

/**
 * Created by ASUS on 28/02/2016.
 */

@Entity
@Table(name = "salary_category")
public class SalaryCategory {

    @Id
    @Column(nullable = false, unique = true, length = 32)
    private String name;

    @Column(nullable = false)
    private double dayPay;

    public SalaryCategory() {
    }

    public SalaryCategory(String name, double dayPay) {
        this.name = name;
        this.dayPay = dayPay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDayPay() {
        return dayPay;
    }

    public void setDayPay(double dayPay) {
        this.dayPay = dayPay;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.name)
                .toString();
    }
}
