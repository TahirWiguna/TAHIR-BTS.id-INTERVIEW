package com.example.tesinterview.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ChecklistItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "checklist", referencedColumnName = "id")
    private Checklist checklist;

    private String name;
    private Boolean checked;
}
