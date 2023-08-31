package com.App.GetYourGuide.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long customerId;
    private String name;
    private String email;
    @OneToMany
    private List<OrderDetails> orderedTours;

}
