package com.example.repository;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

  public Page<Booking> findAllByRoomIdAndDate(Integer roomId, Date date, Pageable pageable);

  public List<Booking> findAllOverlapping(Integer roomId, Date date, Time startAt, Time endAt);
  
}
