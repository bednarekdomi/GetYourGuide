package com.App.GetYourGuide.domain;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guide_id")
    private long id;
    private String name;
    private boolean isAvailable;
    @OneToMany(
            mappedBy = "guide",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<OrderDetails> tours;
    private int daysOffSinceLastTrip;

}
