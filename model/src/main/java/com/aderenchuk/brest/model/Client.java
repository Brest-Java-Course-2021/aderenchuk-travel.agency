package com.aderenchuk.brest.model;

import java.util.Objects;

/**
 *  POJO Client for model.
 */
public class Client {

    /**
     * Client id.
     */
    private Integer clientId;

    /**
     * Client firstname.
     */
    private String firstName;

    /**
     * Client lastname.
     */
    private String lastName;

    /**
     * Tour id.
     */
    private Integer tourId;

    /**
     * Constructor without arguments.
     */
    public Client() {

    }

    /**
     * Constructor without arguments.
     * @param clientId Client id.
     */
    public Client(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * Constructor with  FirstName, LastName, Tour id.
     * @param firstName first name client.
     * @param lastName last Name client.
     * @param tourId Tour id.
     */
    public Client(String firstName, String lastName, Integer tourId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tourId = tourId;
    }

    /**
     * Return Client id.
     * @return clientId.
     */
    public Integer getClientId() {
        return clientId;
    }

    /**
     * Set Client id.
     * @param clientId Client id.
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * Return Client firstname.
     * @return firstname.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set Client firstname.
     * @param firstName Client firstname.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Return Client lastname.
     * @return lastname.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set Client lastname.
     * @param lastName Client lastname.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Return Tour id.
     * @return tourId.
     */
    public Integer getTourId() {
        return tourId;
    }

    /**
     * Set  Tour id.
     * @param tourId Tour id.
     */
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