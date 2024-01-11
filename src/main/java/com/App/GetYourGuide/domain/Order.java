package com.App.GetYourGuide.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;
        return isPaid() == order.isPaid() &&
                isVerified() == order.isVerified() &&
                isSubmitted() == order.isSubmitted() &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(tour, order.tour) &&
                Objects.equals(guide, order.guide) &&
                Objects.equals(tourDate, order.tourDate) &&
                Objects.equals(customer, order.customer);
    }


    @Override
    public int hashCode() {
        int year = tourDate.getYear() % 100;
        int month = tourDate.getMonthValue();
        int day = tourDate.getDayOfYear();
        return day * 1000000 + month * 10000 + year * 100;
    }
}
