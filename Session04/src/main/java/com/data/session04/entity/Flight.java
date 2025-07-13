package com.data.session04.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String flightNumber;
    private String departure;
    private String destination;
    private Double price;
}
