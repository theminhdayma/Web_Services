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
public class Notification {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "create_at", nullable = false)
    private String createAt;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private Account account;

    public enum Status {
        UNREAD,
        READ
    }
}
