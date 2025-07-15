package com.data.session06.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "classes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Classes {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "class_id")
    private Long classId;
    @Column(name = "class_name")
    private String className;
    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "classes")
    @JsonIgnore
    private List<Students> students;
}
