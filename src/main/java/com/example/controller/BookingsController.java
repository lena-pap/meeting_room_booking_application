package com.example.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.model.Booking;
import com.example.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/bookings")
public class BookingsController {
    @Autowired
	private BookingService bookingService;

    @GetMapping
    public List<Booking> getBookings(@RequestParam Integer roomId, @RequestParam Date date) {
        return bookingService.list(roomId, date);
    }
}