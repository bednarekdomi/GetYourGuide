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
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;
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


}
