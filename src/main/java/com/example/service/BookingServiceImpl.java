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
     
    public Booking save(Booking booking) throws Exception {
        if (isOverlapping(booking)) {
            throw new Exception("Booking is overlapping with another!");
        }

        return repo.save(booking);
    }

    public Booking find(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) throws Exception {
        if (find(id).isPast()) {
            throw new Exception("Cannot cancel a past booking!");
        }

        repo.deleteById(id);
    }

    private boolean isOverlapping(Booking booking) {
        List<Booking> overlappingBookings = repo.findAllOverlapping(
            booking.getRoom().getId(),
            booking.getDate(),
            booking.getStartAt(),
            booking.getEndAt()
        );

        return overlappingBookings.size() > 0;
    }
}
