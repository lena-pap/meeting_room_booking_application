package com.example.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.model.Booking;
import com.example.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> list(Integer roomId, Date date, Integer pageNo, Integer pageSize, String sortBy) {
        if (date.before(new Date(System.currentTimeMillis()))) {
            return List.of();
        } else {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

            Page<Booking> pagedResult = bookingRepository.findAllByRoomIdAndDate(roomId, date, paging);

            if(pagedResult.hasContent()) {
                return pagedResult.getContent();
            } else {
                return List.of();
            }
        }
    }
     
    public Booking save(Booking booking) throws Exception {
        if (isOverlapping(booking)) {
            throw new Exception("Booking is overlapping with another!");
        }

        return bookingRepository.save(booking);
    }

    public Booking find(long id) {
        return bookingRepository.findById(id).get();
    }

    public void delete(long id) throws Exception {
        if (find(id).isPast()) {
            throw new Exception("Cannot cancel a past booking!");
        }

        bookingRepository.deleteById(id);
    }

    private boolean isOverlapping(Booking booking) {
        List<Booking> overlappingBookings = bookingRepository.findAllOverlapping(
            booking.getRoom().getId(),
            booking.getDate(),
            booking.getStartAt(),
            booking.getEndAt()
        );

        return overlappingBookings.size() > 0;
    }
}
