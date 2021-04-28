package com.aderenchuk.brest.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

/**
 * POJO Tour for model.
 */
public class Tour {

    /**
     * Tour id.
     */
    private Integer tourId;

    /**
     * Name of tour direction .
     */
    private String direction;

    /**
     * Date of tour.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateTour;

    /**
     * Constructor without arguments.
     */
    public Tour() {
    }

    /**
     * Constructor with Tour id, name of route tour and date.
     * @param tourId Tour id.
     * @param direction name of direction tour.
     * @param dateTour Date of tour.
     */
    public Tour(Integer tourId, String direction, LocalDate dateTour) {
        this.tourId = tourId;
        this.direction = direction;
        this.dateTour = dateTour;
    }

    /**
     * Return Tour id.
     * @return tourId.
     */
    public Integer getTourId() {
        return tourId;
    }

    /**
     * Set Tour id.
     * @param tourId Tour id.
     */
    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    /**
     * Return name of direction tour.
     * @return tour.
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Set name of route tour.
     * @param direction name of direction tour.
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Return Date of tour.
     * @return date Date of tour.
     */
    public LocalDate getDateTour() {
        return dateTour;
    }

    /**
     * Set Date of tour.
     * @param dateTour Date of tour.
     */
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

