package com.data.session05.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "fruits")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer stock;

    private Boolean status;

    private LocalDate createdAt;

}
