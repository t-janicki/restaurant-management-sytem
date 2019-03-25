package com.restaurant.management.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "daily_orders_list")
public class DailyOrderList extends AbstractDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "unique_id")
    private String uniqueId;

    @Column(name = "daily_income")
    private Double dailyIncome;

    @Column(name = "is_open")
    private Boolean isOpen;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<Order> orders = new LinkedHashSet<>();

    public DailyOrderList(Long id, String uniqueId, Double dailyIncome,
                          Boolean isOpen, Set<Order> orders) {
        this.id = id;
        this.uniqueId = uniqueId;
        this.dailyIncome = dailyIncome;
        this.isOpen = isOpen;
        this.orders = orders;
    }

    public DailyOrderList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Double getDailyIncome() {
        return dailyIncome;
    }

    public void setDailyIncome(Double dailyIncome) {
        this.dailyIncome = dailyIncome;
    }

    public Boolean isOpen() {
        return isOpen;
    }

    public void setOpened(Boolean opened) {
        isOpen = opened;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

}
