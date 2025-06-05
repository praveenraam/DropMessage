package com.example.demo.Model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private LocalDate expiration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EncryptionType encryption;

    @Column
    private String password;

}
