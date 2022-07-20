package com.sachini.roomservice.controller;

import com.sachini.model.room.Room;
import com.sachini.roomservice.service.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services/room")
public class RoomController {

    RoomServiceImpl roomService;

    @Autowired
    public RoomController(RoomServiceImpl roomService) {
        this.roomService = roomService;
    }

    @PostMapping
    public Room save(@RequestBody Room room){
        return roomService.save(room);
    }

    @GetMapping(value = "/{id}")
    public Room getRoom(@PathVariable int id){
        System.out.println("Request came");
        return roomService.findById(id);
    }

    @GetMapping
    public List<Room> getAllRooms(){
        return roomService.findAll();
    }
}
