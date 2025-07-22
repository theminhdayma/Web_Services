package com.data.session10.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;
    @Column(name = "receiver", nullable = false)
    private String receiver;
    @Column(name = "sender", nullable = false)
    private String sender;
    @Column(name = "money", nullable = false)
    private Double money;
    @Column(name = "note", nullable = false)
    private String note;
    @Column(name = "create_at", nullable = false)
    private String createAt;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING,
        COMPLETED,
        FAILED
    }
}
