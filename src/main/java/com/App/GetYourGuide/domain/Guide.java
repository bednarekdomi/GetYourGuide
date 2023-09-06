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
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guide_id")
    private long id;
    private String name;
    @OneToMany(
            mappedBy = "guide",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Order> tours;
    private long daysOffSinceLastTrip;

    public void setDaysOffSinceLastTrip(List<Order> tours) {
        if (tours == null || tours.isEmpty()) {
            daysOffSinceLastTrip = 0;
        } else {

            LocalDate lastTripDate = tours.get(0).getTourDate();
            for (Order order : tours) {
                LocalDate tourDate = order.getTourDate();
                if (tourDate != null && tourDate.isAfter(lastTripDate)) {
                    lastTripDate = tourDate;
                }
            }

            daysOffSinceLastTrip = ChronoUnit.DAYS.between(lastTripDate, LocalDate.now());
        }
    }

}
