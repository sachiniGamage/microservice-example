package com.sachini.reservationservice.model;

import com.sachini.model.reservation.Reservation;

public class SimpleResponse implements Response {

    Reservation reservation;

    public SimpleResponse(Reservation reservation) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
