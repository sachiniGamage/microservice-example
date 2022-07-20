package com.sachini.reservationservice.service;

import com.sachini.model.customer.Customer;
import com.sachini.model.reservation.Reservation;
import com.sachini.model.room.Room;
import com.sachini.reservationservice.model.DetailReponse;
import com.sachini.reservationservice.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl {

    ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    RestTemplate restTemplate;



    public Reservation findById(int id){
        Optional<Reservation> reservation = reservationRepository.findById(id);

        if (reservation.isPresent()){
            return reservation.get();
        }else{
            return new Reservation();
        }
    }

    public Reservation save(Reservation customer){
        return reservationRepository.save(customer);
    }

    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    public DetailReponse findDetailResponse(int id){
        Reservation reservation = findById(id);
        Customer customer = getCustomer(reservation.getCustomerId());
        Room room = getRoom(reservation.getRoomId());

        return  new DetailReponse(reservation,customer,room);
    }

    public Customer getCustomer(int custId){
        Customer customer = restTemplate.getForObject("http://customer/services/customer/" + custId, Customer.class);
        return customer;
    }

    private Room getRoom(int roomId){
        return restTemplate.getForObject("http://room/services/room/"+ roomId,Room.class);

    }
}
