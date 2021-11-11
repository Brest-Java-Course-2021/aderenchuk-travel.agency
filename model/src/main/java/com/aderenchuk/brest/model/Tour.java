package com.aderenchuk.brest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

/**
 * POJO Tour for model.
 */
@Entity
@Table(name = "TOUR")
public class Tour {

    /**
     * Tour id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tourId")
    @Min(value = 1, message = "Tour number is a required field")
    private Integer tourId;

    /**
     * Name of tour direction .
     */

    @Column(name = "direction")
    @NotBlank(message = "Direction is a required field")
    private String direction;

    /**
     * Date of tour.
     */
    @Column(name = "dateTour")
    @NotNull(message = "dateTour is a required field")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateTour;

    @Transient
    private XMLGregorianCalendar dateSoapTour;

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

    public Tour(Integer tourId, String name, Date birthday) {
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
    @Required
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
    @Required
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Return Date of tour.
     * @return date Date of tour.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate getDateTour() {
        return dateTour;
    }

    /**
     * Set Date of tour.
     * @param dateTour Date of tour.
     */
    @Required
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

    public void setDateSoapTour(XMLGregorianCalendar dateTour) {
        this.dateSoapTour = dateSoapTour;
    }
}

