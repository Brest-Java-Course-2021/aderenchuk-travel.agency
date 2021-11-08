package com.aderenchuk.brest.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class TourDto {

    /**
     * Tour Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tourId")
    @Min(value = 1, message = "Tour number is a required field")
    private Integer tourId;

    /**
     * Tour Direction.
     */
    @Column(name = "direction")
    @NotBlank(message = "Direction is a required field")
    private String direction;

    /**
     * Date tour.
     */
    @Column(name = "dateTour")
    @NotNull(message = "dateTour is a required field")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateTour;

    /**
     * Quantity clients of tour.
     */
    @Min(value = 1, message = "quantityClients is a required field")
    private Integer quantityClients;

    /**
     * Constructor without arguments.
     */
    public TourDto() {
    }

    /**
     * Constructor with tour direction.
     *
     * @param direction direction
     */
    public TourDto(String direction) {
        this.direction = direction;
    }

    /**
     * Returns <code>Integer</code> representation of this tourId.
     *
     * @return tourId Tour Id.
     */
    public Integer getTourId() {
        return tourId;
    }

    /**
     * Sets the tour's identifier.
     *
     * @param tourId Tour Id.
     */
    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    /**
     * Returns <code>String</code> representation of this direction.
     *
     * @return direction tourDirection
     */
    public String getDirection() {
        return direction;
    }

    /**
     * Sets the tour's name.
     *
     * @param direction tourDirection.
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * Returns <code>LocalDate</code> representation of date tour
     * for the Tour.
     *
     * @return dateTour.
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate getDateTour() {
        return dateTour;
    }

    /**
     * Sets the tour's name.
     *
     * @param dateTour dateTour.
     */
    public void setDateTour(LocalDate dateTour) {
        this.dateTour = dateTour;
    }

    /**
     * Returns <code>Integer</code> representation of quantity clients
     * for the Tour.
     *
     * @return quantityClients.
     */
    public Integer getQuantityClients() {
        return quantityClients;
    }

    /**
     * Sets the tour's quantity clients.
     *
     * @param quantityClients Quantity clients.
     */
    public void setQuantityClients(Integer quantityClients) {
        this.quantityClients = quantityClients;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "TourDto{" +
                "tourId=" + tourId +
                ", direction='" + direction + '\'' +
                ", dateTour=" + dateTour +
                ", quantityClients=" + quantityClients +
                '}';
    }
}
