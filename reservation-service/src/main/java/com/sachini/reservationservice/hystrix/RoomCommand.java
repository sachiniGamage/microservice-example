package com.sachini.reservationservice.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.sachini.model.room.Room;
import org.springframework.web.client.RestTemplate;

public class RoomCommand extends HystrixCommand<Room> {

    RestTemplate restTemplate;
    int roomId;

    public RoomCommand(RestTemplate restTemplate, int roomId){

        super(HystrixCommandGroupKey.Factory.asKey("default"));

        this.restTemplate = restTemplate;
        this.roomId = roomId;
    }

    @Override
    protected Room run() throws Exception {
        return restTemplate.getForObject("http://room/services/room/"+ roomId,Room.class);
    }

    @Override
    protected Room getFallback() {
        return new Room();
    }
}
