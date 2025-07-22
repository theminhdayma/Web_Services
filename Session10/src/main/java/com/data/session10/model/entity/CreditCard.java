package com.data.session10.model.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false, unique = true)
    private Account account;

    @Column(nullable = false)
    private Double spendingLimit;

    @Column(nullable = false)
    private Double amountSpent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CreditCardStatus status;

    public enum CreditCardStatus {
        ACTIVE,
        INACTIVE
    }
}

