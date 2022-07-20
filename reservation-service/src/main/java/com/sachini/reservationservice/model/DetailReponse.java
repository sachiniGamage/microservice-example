package com.sachini.reservationservice.model;

import com.sachini.model.customer.Customer;
import com.sachini.model.reservation.Reservation;
import com.sachini.model.room.Room;

public class DetailReponse implements Response{
    Reservation reservation;
    Customer customer;
    Room room;

    public DetailReponse(Reservation reservation, Customer customer, Room room) {
        this.reservation = reservation;
        this.customer = customer;
        this.room = room;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
