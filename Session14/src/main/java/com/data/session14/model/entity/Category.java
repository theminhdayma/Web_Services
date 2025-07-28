package com.data.session14.model.entity;

import com.data.session14.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;
    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;
    @Column(name = "status", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Product> products;
}
