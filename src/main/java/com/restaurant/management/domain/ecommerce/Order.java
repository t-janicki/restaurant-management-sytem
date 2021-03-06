package com.restaurant.management.domain.ecommerce;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "orders")
public class Order extends AbstractAuditing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_number")
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus orderStatus;

    @Column(name = "assigned_to_user_id")
    private Long assignedToUserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type")
    private OrderType orderType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CartOrdered cartOrdered;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Company company;

    public Order() {
    }

    public Order(
            Long id, String orderNumber,
            OrderStatus orderStatus, Long assignedToUserId,
            OrderType orderType, CartOrdered cartOrdered) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.assignedToUserId = assignedToUserId;
        this.orderType = orderType;
        this.cartOrdered = cartOrdered;
    }

    public Order(String orderNumber,
                 OrderStatus orderStatus, Long assignedToUserId,
                 OrderType orderType, CartOrdered cartOrdered,
                 Company company) {
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.assignedToUserId = assignedToUserId;
        this.orderType = orderType;
        this.cartOrdered = cartOrdered;
        this.company = company;
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public CartOrdered getCartOrdered() {
        return cartOrdered;
    }

    public void setCartOrdered(CartOrdered cartOrdered) {
        this.cartOrdered = cartOrdered;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Long getAssignedToUserId() {
        return assignedToUserId;
    }

    public void setAssignedToUserId(Long assignedToUserId) {
        this.assignedToUserId = assignedToUserId;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public static String createOrderNumber(String orderNumber) {
        int orderYear;

        Calendar calendar = Calendar.getInstance();
        orderYear = calendar.get(Calendar.YEAR);

        return orderNumber + "/" + orderYear;
    }

    public static class OrderBuilder {
        private String orderNumber;
        private OrderStatus orderStatus;
        private Long assignedTo;
        private OrderType orderType;
        private CartOrdered cartOrdered;
        private Company company;

        public OrderBuilder setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
            return this;
        }

        public OrderBuilder setStatus(OrderStatus orderStatus) {
            this.orderStatus = orderStatus;
            return this;
        }

        public OrderBuilder setAssignedTo(Long assignedTo) {
            this.assignedTo = assignedTo;
            return this;
        }

        public OrderBuilder setOrderType(OrderType orderType) {
            this.orderType = orderType;
            return this;
        }

        public OrderBuilder setCartOrdered(CartOrdered cartOrdered) {
            this.cartOrdered = cartOrdered;
            return this;
        }

        public OrderBuilder setCompany(Company company) {
            this.company = company;
            return this;
        }

        public Order build() {
            return new Order(this.orderNumber, this.orderStatus, this.assignedTo, this.orderType, this.cartOrdered, this.company);
        }
    }

}
