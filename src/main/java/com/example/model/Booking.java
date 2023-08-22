package com.example.model;

import java.sql.Date;
import java.sql.Time;

import com.example.annotation.TimeSlotValidation;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
 
@Entity
@Table(name = "bookings")
@TimeSlotValidation
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    @FutureOrPresent
    private Date date;

    @NotNull
    @Column
    private Time startAt;
     
    @NotNull
    @Column
    private Time endAt;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    public Booking() {
    }
        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartAt() {
        return startAt;
    }

    public void setStartAt(Time startAt) {
        this.startAt = startAt;
    }

    public Time getEndAt() {
        return endAt;
    }

    public void setEndAt(Time endAt) {
        this.endAt = endAt;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Checks whether the booking has finished.
     * 
     * @return boolean
     */
    public boolean isPast() {
        long now = System.currentTimeMillis();

        return date.equals(new Date(now)) || date.before(new Date(now)) && endAt.before(new Time(now));
    }
}