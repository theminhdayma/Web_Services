package com.data.session02.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "screen_rooms")
@Data
public class ScreenRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

}