package ru.drsdgdby.springdatatest.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(max = 32, message = "title must be no more 32 characters")
    private String title;
    @Column(name = "price")
    @PositiveOrZero(message = "price should not be less than 0")
    private Double cost;

    @OneToOne(mappedBy = "product")
    private OrderItem orderItem;
}
