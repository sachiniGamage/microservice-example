package com.sachini.reservationservice.controller;

import com.sachini.model.reservation.Reservation;
import com.sachini.reservationservice.model.Response;
import com.sachini.reservationservice.model.SimpleResponse;
import com.sachini.reservationservice.service.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/services/reservations")
public class ReservationController {

    ReservationServiceImpl reservationService;

    @Autowired
    public ReservationController(ReservationServiceImpl reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Reservation save(@RequestBody Reservation reservation){
        return reservationService.save(reservation);
    }

    @GetMapping (value = "/{id}")
    public Response getReservation(@PathVariable int id, @RequestParam(required = false) String type) throws ExecutionException, InterruptedException {
        if(type == null){
            return new SimpleResponse(reservationService.findById(id));

        }else {
            return reservationService.findDetailResponse(id);
        }
    }

    @GetMapping
    public List<Reservation> getAllReservation(){
        return reservationService.findAll();
    }

}
