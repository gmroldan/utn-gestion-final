package edu.utn.gestion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ASUS on 01/03/2016.
 */
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    private String id;

    @Column(nullable = false)
    private String period;

    @Column(nullable = false)
    private boolean presenteeism;

    @Column(nullable = false)
    private int absences;

    public Attendance() {
    }

    public Attendance(String period, long id) {
        this.id = "" + period + id;
        this.period = period;
    }

    public int getAbsences() {
        return absences;
    }

    public String getId() {
        return id;
    }

    public String getPeriod() {
        return period;
    }

    public boolean isPresenteeism() {
        return presenteeism;
    }

    public void setAbsences(int absences) {
        this.absences = absences;
    }

    public void setPresenteeism(boolean presenteeism) {
        this.presenteeism = presenteeism;
    }
}
