package com.aderenchuk.brest.model;




import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TourTest {

    @Test
    public void getDirectionConstructor() {
        Tour tour = new Tour(101, "Brest-Moscow", LocalDate.of(2002, 2, 12));
        Assertions.assertEquals("Brest-Moscow", tour.getDirection());
    }

    @Test
    public void getDirectionSetter() {
        Tour tour = new Tour();
        tour.setDirection("Brest-Moscow");
        Assertions.assertEquals("Brest-Moscow", tour.getDirection());
    }
}