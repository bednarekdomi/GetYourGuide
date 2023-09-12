package com.App.GetYourGuide.domain;

import jakarta.persistence.*;

@Entity
public abstract class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    abstract String getDescription();
    abstract double getCost();
}
