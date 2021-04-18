package com.aderenchuk.brest.service.rest_app.exception;

public class TourNotFoundException  extends  RuntimeException{
    public TourNotFoundException(Integer tourId) {
        super("Tour not found for id: " + tourId);
    }
}
