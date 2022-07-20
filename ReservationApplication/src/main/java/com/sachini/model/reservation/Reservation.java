package com.sachini.model.reservation;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int customerId;
    private int roomId;
    private LocalDateTime reserveFrom;
    private LocalDateTime reserveTill;

    public Reservation() {
    }

    public Reservation(int id, int customerId, int roomId, LocalDateTime reserveFrom, LocalDateTime reserveTill) {
        this.id = id;
        this.customerId = customerId;
        this.roomId = roomId;
        this.reserveFrom = reserveFrom;
        this.reserveTill = reserveTill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDateTime getReserveFrom() {
        return reserveFrom;
    }

    public void setReserveFrom(LocalDateTime reserveFrom) {
        this.reserveFrom = reserveFrom;
    }

    public LocalDateTime getReserveTill() {
        return reserveTill;
    }

    public void setReserveTill(LocalDateTime reserveTill) {
        this.reserveTill = reserveTill;
    }
}
