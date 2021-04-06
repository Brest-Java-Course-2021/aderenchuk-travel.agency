package com.aderenchuk.brest.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TourTest {

    @Test
    public void getDirectionConstructor() {
        Tour tour = new Tour("Brest-Moscow");
        Assertions.assertEquals("Brest-Moscow", tour.getDirection());
    }

    public void setDirectionSetter() {
        Tour tour = new Tour();
        tour.setDirection("Brest-Moscow");
        Assertions.assertEquals("Brest-Moscow", tour.getDirection());
    }
}