package com.example.service;

import java.sql.Date;
import java.util.List;

import com.example.model.Booking;

public interface BookingService {
    List<Booking> list(Integer roomId, Date date);

    Booking save(Booking booking) throws Exception;    
}
