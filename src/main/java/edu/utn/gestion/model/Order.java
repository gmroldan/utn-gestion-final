package edu.utn.gestion.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 25/12/15.
 */
@Entity
@Table(name = "order_to_supplier")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @OneToMany(targetEntity = OrderDetail.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_detail_id", referencedColumnName = "id", nullable = false)
    private final List<OrderDetail> orderDetails = new ArrayList<>();

    /**
     * No-args constructor. Creates a new instance of <code>Order</code>.
     */
    public Order() {}

    /**
     * Creates a new instance of <code>Order</code>.
     *
     * @param status
     */
    public Order(OrderStatusEnum status) {
        this.status = status;
    }

    /**
     * Creates a new instance of <code>Order</code>.
     *
     * @param id
     * @param status
     */
    public Order(long id, OrderStatusEnum status) {
        this.id = id;
        this.status = status;
    }

    public OrderStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OrderStatusEnum status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }
}