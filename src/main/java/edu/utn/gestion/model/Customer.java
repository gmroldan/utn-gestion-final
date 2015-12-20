package edu.utn.gestion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable = false, unique = true, length = 25)
    private String name;
    
    @Column(nullable = false, unique = true, length = 11)
    private String cuit;
    
    @Column(nullable = false, unique = true, length = 10)
    private String phoneNumber;
    
    @Column(nullable = false, unique = true, length = 30)
    private String email;

    /**
     * No-args Constructor.
     */
    public Customer() {
    }

    /**
     * Class constructor.
     * 
     * @param id
     * @param name
     * @param cuit
     * @param phoneNumber
     * @param email 
     */
    public Customer(long id, String name, String cuit, String phoneNumber, String email) {
        this.id = id;
        this.name = name;
        this.cuit = cuit;
        this.phoneNumber = phoneNumber;
        this.email = email;
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

    @Override
    public String toString() {
        return name;
    }
}
