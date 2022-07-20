package com.sachini.roomservice.service;

import com.sachini.model.room.Room;
import com.sachini.roomservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl {

    RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository repository) {
        this.roomRepository = repository;
    }

    public Room save(Room customer){
        return roomRepository.save(customer);
    }

    public Room findById(int id){
        Optional<Room> room = roomRepository.findById(id);
        if(room.isPresent()){
            return room.get();
        }else{
            return new Room();
        }
    }

    public List<Room> findAll(){
        return roomRepository.findAll();
    }
}
