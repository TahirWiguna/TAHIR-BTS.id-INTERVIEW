package com.example.tesinterview.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;
    private String username;
    private String password;
    private String token;
    private Long tokenExpiredAt;

    @OneToMany(mappedBy = "user")
    private List<Checklist> checklist;

}
