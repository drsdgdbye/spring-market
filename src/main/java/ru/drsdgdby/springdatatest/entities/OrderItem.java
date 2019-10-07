package ru.drsdgdby.springdatatest.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

 /*   @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;*/

    private Integer amount;
    private Double price;
    @Column(name = "total_price")
    private Double totalPrice;
}
