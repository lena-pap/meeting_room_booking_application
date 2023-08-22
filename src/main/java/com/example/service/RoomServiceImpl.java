package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Room;
import com.example.repository.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired  
    private RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
      return roomRepository.findAll();
    }
}
