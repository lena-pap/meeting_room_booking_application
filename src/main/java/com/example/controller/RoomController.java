package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Room;
import com.example.service.RoomService;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
	  private RoomService roomService;

    @GetMapping
    public List<Room> getRooms() {
        return roomService.findAll();
    }
}
