package com.ecommerce.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
@EqualsAndHashCode
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    private Date orderDate;
    private Date deliveryDate;
    private String orderStatus;
    private double totalPrice;
    private double tax;
    private int quantity;
    private String paymentMethod;
    private boolean isAccept;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderDetail> orderDetailList;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", totalPrice=" + totalPrice +
                ", tax='" + tax + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", customer=" + customer.getUsername() +
                ", orderDetailList=" + orderDetailList.size() +
                '}';
    }
}
