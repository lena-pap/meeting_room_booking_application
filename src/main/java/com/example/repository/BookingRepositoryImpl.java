package com.example.repository;

import java.sql.Time;
import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.model.Booking;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Component
public class BookingRepositoryImpl {
  @PersistenceContext
  private EntityManager em;

  /**
   * Records whose time range overlaps a given period from `startAt` to `endAt`
   * (i.e. include common points in time).
   * Cases for overlapping:
   * given period:             |-------|
   *
   * record period:        <---|-->    |             overlaps
   * record period:            |    <--|--->         overlaps
   * record period:            | <---> |             overlaps
   * record period:         <--|-------|->           overlaps
   * record period:   <----->  |       |             does not overlap
   * record period:            |       |   <----->   does not overlap
   * 
   * @param roomId
   * @param date
   * @param startAt
   * @param endAt
   * @return List<Booking>
   */
  public List<Booking> findAllOverlapping(Integer roomId, Date date, Time startAt, Time endAt) {
      TypedQuery<Booking> q = em.createQuery(
        "SELECT b FROM Booking b WHERE b.room.id = :roomId AND b.date = :date AND b.endAt >= :startAt AND b.startAt <= :endAt",
        Booking.class
      );
      q.setParameter("roomId", roomId);
      q.setParameter("date", date);
      q.setParameter("startAt", startAt);
      q.setParameter("endAt", endAt);

      return q.getResultList();
  }
}
