package com.example.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.model.Booking;
import com.example.service.BookingService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/bookings")
public class BookingsController {
    @Autowired
	private BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<Booking>> getBookings(
        @RequestParam Integer roomId, @RequestParam Date date, @RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "startAt") String sortBy
    ) {

        List<Booking> bookings = bookingService.list(roomId, date, pageNo, pageSize, sortBy);

        return ResponseEntity.ok().body(bookings);
    }

	@PostMapping
    public ResponseEntity<String> createBooking(@Valid @RequestBody Booking booking) {
        try {
            Booking to_be_created = bookingService.save(booking);

            if (to_be_created.getId() == null) {
                return ResponseEntity.badRequest().body("Booking was not completed!");
            }
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("The booking for " + booking.getDate() + " at " + booking.getStartAt() + " was completed!");
    }

	@DeleteMapping("{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable("id") int id) {
        try {
            bookingService.delete(id);

            return ResponseEntity.ok("The booking was cancelled.");
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}