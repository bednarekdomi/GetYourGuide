package com.App.GetYourGuide.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
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
    @JoinColumn(name = "guide_id")
    private Guide guide;
    private LocalDate tourDate;
    private boolean isPaid;
    private boolean isVerified;
    private boolean isSubmitted;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order(TourOrder tour, Guide guide, LocalDate tourDate, boolean isPaid, boolean isVerified, boolean isSubmitted,
                 Customer customer) {
        this.tour = tour;
        this.guide = guide;
        this.tourDate = tourDate;
        this.isPaid = isPaid;
        this.isVerified = isVerified;
        this.isSubmitted = isSubmitted;
        this.customer = customer;
    }
}
