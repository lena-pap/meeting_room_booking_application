package com.example.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

  public List<Booking> findAllByRoomIdAndDate(Integer roomId, Date date);
  
}
