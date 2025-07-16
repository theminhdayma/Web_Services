package com.data.session07.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seeds")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Seed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seed_id")
    private Long id;
    @Column(name = "seed_name")
    private String seedName;
    @Column(name = "quantity")
    private Integer quantity;
}
