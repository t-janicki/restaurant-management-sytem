package com.restaurant.management.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isOpen")
    private Boolean isOpen;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LineItem> lineItems = new ArrayList<>();

    public Cart() {
    }

    public Cart(Boolean isOpen, Customer customer, List<LineItem> lineItems) {
        this.isOpen = isOpen;
        this.customer = customer;
        this.lineItems = lineItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Double calculateTotal(){
        return lineItems.stream()
                .mapToDouble(v ->v.getPrice() * v.getQuantity())
                .reduce(Double::sum)
                .getAsDouble();
    }

    public static class CartBuilder {
        private Boolean isOpen;
        private Customer customer;
        private List<LineItem> lineItems = new ArrayList<>();

        public CartBuilder setIsOpen(Boolean isOpen) {
            this.isOpen = isOpen;
            return this;
        }

        public CartBuilder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public CartBuilder setLineItems(List<LineItem> lineItems) {
            this.lineItems = lineItems;
            return this;
        }

        public Cart build() {
            return new Cart(this.isOpen, this.customer, this.lineItems);
        }
    }
}