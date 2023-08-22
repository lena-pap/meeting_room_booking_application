package com.example.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Booking;
import com.example.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository repo;

    public List<Booking> list(Integer roomId, Date date) {
        if (date.before(new Date(System.currentTimeMillis()))) {
            return List.of();
        } else {
            return repo.findAllByRoomIdAndDate(roomId, date);
        }
    }
}
