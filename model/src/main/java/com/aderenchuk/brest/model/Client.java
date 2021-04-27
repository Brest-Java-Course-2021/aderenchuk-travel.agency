package com.aderenchuk.brest.model;

import java.util.Objects;

public class Client {

    private Integer clientId;

    private String firstName;

    private String lastName;

    private Integer tourId;

    public Client(String firstName, String lastName, Integer tourId) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.tourId = tourId;
    }

    public Client() {

    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(tourId, client.tourId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, tourId);
    }

    @Override
    public String toString() {
        return "com.aderenchuk.brest.model.Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", tourId=" + tourId +
                '}';
    }

}