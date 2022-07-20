package com.sachini.reservationservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sachini.model.customer.Customer;
import com.sachini.model.reservation.Reservation;
import com.sachini.model.room.Room;
import com.sachini.reservationservice.hystrix.CommonHystrixCommand;
import com.sachini.reservationservice.hystrix.RoomCommand;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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


    public DetailReponse findDetailResponse(int id) throws ExecutionException, InterruptedException {
        Reservation reservation = findById(id);
        Customer customer = getCustomer(reservation.getCustomerId());
        Room room = getRoom(reservation.getRoomId());

        return  new DetailReponse(reservation,customer,room);
    }

    public DetailReponse findDetailResponsefallback(int id){
        return new DetailReponse(new Reservation(),new Customer(), new Room());
    }

    private Customer getCustomer(int custId) throws ExecutionException, InterruptedException {
        CommonHystrixCommand<Customer> customerCommonHystrixCommand = new CommonHystrixCommand<Customer>("default", () -> {
            return restTemplate.getForObject("http://customer/services/customer/" + custId, Customer.class);
        },() -> {
            return  new Customer();
        } );
//        Customer customer = restTemplate.getForObject("http://customer/services/customer/" + custId, Customer.class);
//        return customer;

        Future<Customer> customerFuture = customerCommonHystrixCommand.queue();
        return customerFuture.get();
    }

    private Room getRoom(int roomId){

        RoomCommand roomCommand = new RoomCommand(restTemplate,roomId);
        return roomCommand.execute();

//        return restTemplate.getForObject("http://room/services/room/"+ roomId,Room.class);

    }
}
