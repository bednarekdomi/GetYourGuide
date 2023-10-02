package com.App.GetYourGuide.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
    @Transient
    private TourOrder tour;

    @ManyToOne
    @JoinColumn(name = "guide_id", nullable = false)
    private Guide guide;
    private LocalDate tourDate;
    private boolean paid;
    private boolean verified;
    private boolean submitted;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    public Order(TourOrder tour, Guide guide, LocalDate tourDate, boolean paid, boolean verified, boolean submitted,
                 Customer customer) {
        this.tour = tour;
        this.guide = guide;
        this.tourDate = tourDate;
        this.paid = paid;
        this.verified = verified;
        this.submitted = submitted;
        this.customer = customer;
    }
}
