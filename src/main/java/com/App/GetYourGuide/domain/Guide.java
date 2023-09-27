package com.App.GetYourGuide.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "Guides")
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String name;

    @OneToMany(mappedBy = "guide",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Order> tours;
    private long daysOffSinceLastTrip = setDaysOffSinceLastTrip(tours);

    public long setDaysOffSinceLastTrip(List<Order> tours) {
        LocalDate lastTripDate = tours.get(0).getTourDate();
        for (Order order : tours) {
            LocalDate tourDate = order.getTourDate();
            if (tourDate.isBefore(LocalDate.now()) && tourDate.isAfter(lastTripDate)) {
                lastTripDate = tourDate;
            }
        }
        return ChronoUnit.DAYS.between(lastTripDate, LocalDate.now());
    }

}
