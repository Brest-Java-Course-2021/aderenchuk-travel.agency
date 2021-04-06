package com.aderenchuk.brest.model;


import org.junit.Assert;
import org.junit.Test;

public class TourTest {

    @Test
    public void getDirectionConstructor() {
        Tour tour = new Tour("Brest-Moscow");
        Assert.assertEquals("Brest-Moscow", tour.getDirection());
    }

    @Test
    public void getDirectionSetter() {
        Tour tour = new Tour();
        tour.setDirection("Brest-Moscow");
        Assert.assertEquals("Brest-Moscow", tour.getDirection());
    }
}