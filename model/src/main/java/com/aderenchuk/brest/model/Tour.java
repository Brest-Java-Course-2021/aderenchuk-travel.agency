package com.aderenchuk.brest.model;

import java.time.LocalDate;
import java.util.Objects;

public class Tour {

    private Integer tourId;

    private String direction;

    private LocalDate dateTour;

    public Tour() {
    }

    public Tour(String direction, LocalDate dateTour) {
        this.direction = direction;
        this.dateTour = dateTour;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public LocalDate getDateTour() {
        return dateTour;
    }

    public void setDateTour(LocalDate dateTour) {
        this.dateTour = dateTour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tour tour = (Tour) o;
        return Objects.equals(tourId, tour.tourId) && Objects.equals(direction, tour.direction) && Objects.equals(dateTour, tour.dateTour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourId, direction, dateTour);
    }

    @Override
    public String toString() {
        return "Tour{" +
                "tourId=" + tourId +
                ", direction='" + direction + '\'' +
                ", dateTour=" + dateTour +
                '}';
    }
}

