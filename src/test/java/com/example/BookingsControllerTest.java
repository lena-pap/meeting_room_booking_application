package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.BookingsController;
import com.example.model.Booking;
import com.example.model.Employee;
import com.example.model.Room;
import com.example.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@WebMvcTest(BookingsController.class)
public class BookingsControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
	private BookingService service;

    @Test
    public void givenBookings_whenGetBookingsWithoutParameters_thenReturnBadRequest() throws Exception {
        mvc.perform(get("/bookings")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void givenBookings_whenGetBookingsWithParameters_thenReturnJsonArray() throws Exception {
        Booking booking1 = new Booking();
        booking1.setDate(Date.valueOf("2023-09-12"));
        booking1.setStartAt(Time.valueOf("09:00:00"));
        Booking booking2 = new Booking();
        booking2.setDate(Date.valueOf("2023-09-12"));
        booking2.setStartAt(Time.valueOf("10:00:00"));

        when(service.list(isA(Integer.class), isA(Date.class))).thenReturn(List.of(booking1, booking2));

        mvc.perform(get("/bookings?roomId=2&date=2023-9-02")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(content().string("[{\"id\":null,\"date\":\"2023-09-12\",\"startAt\":\"09:00:00\",\"endAt\":null,\"employee\":null,\"room\":null,\"past\":false},{\"id\":null,\"date\":\"2023-09-12\",\"startAt\":\"10:00:00\",\"endAt\":null,\"employee\":null,\"room\":null,\"past\":false}]"));
    }

    @Test
    public void givenBookingObject_whenCreateBooking_thenReturnSuccessString() throws Exception {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setRoom(new Room());
        booking.setEmployee(new Employee());
        booking.setDate(Date.valueOf("2024-9-02"));
        booking.setStartAt(Time.valueOf("13:00:00"));
        booking.setEndAt(Time.valueOf("15:00:00"));

        ObjectMapper objectMapper = new ObjectMapper();
		String bookingJsonString = objectMapper.writeValueAsString(booking);

        when(service.save(any(Booking.class))).thenReturn(booking);

        mvc.perform(post("/bookings")
            .content(bookingJsonString)
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string("The booking for 2024-09-02 at 13:00:00 was completed!"));
    }

    @Test
    public void givenBookingObject_whenCreateBookingWithErrors_thenReturnErrorString() throws Exception {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setRoom(new Room());
        booking.setEmployee(new Employee());
        booking.setDate(Date.valueOf("2024-9-02"));
        booking.setStartAt(Time.valueOf("13:00:00"));
        booking.setEndAt(Time.valueOf("15:00:00"));

        ObjectMapper objectMapper = new ObjectMapper();
		String bookingJsonString = objectMapper.writeValueAsString(booking);

        when(service.save(any(Booking.class))).thenThrow(new Exception("Failed to create!"));

        mvc.perform(post("/bookings")
            .content(bookingJsonString)
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string("Failed to create!"));
    }

    @Test
    public void givenBookingObject_whenCancelBooking_thenReturnSuccessString() throws Exception {
        Mockito.doNothing().when(service).delete(any(Long.class));

        mvc.perform(delete("/bookings/1")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string("The booking was cancelled."));
    }

    @Test
    public void givenBookingObject_whenCancelBookingWithErrors_thenReturnErrorString() throws Exception {
        doThrow(new Exception("Failed to cancel!")).when(service).delete(any(Long.class));

        mvc.perform(delete("/bookings/1")
            .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andExpect(content().contentType("application/json"))
            .andExpect(content().string("Failed to cancel!"));
    }
}
