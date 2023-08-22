package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.BookingsController;
import com.example.model.Booking;
import com.example.model.Employee;
import com.example.model.Room;
import com.example.service.BookingService;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.Test;

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
        /* These dates must always be future dates, so I would create future test bookings
         * (to another test database of course) and test them instead. For now, I had no time to
         * make initial data possible, so the result is empty. */ 
        mvc.perform(get("/bookings?roomId=2&date=2023-9-02")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(content().string("[]"));
    }

    /*
     * The below tests are not functional, just created some of the scenarios I would test.
     * Please note that these would be integration tests, meaning there would be data
     * (existing or created on the fly).
     */
    @Test
    public void givenBookingObject_whenCreateBooking_thenReturnSuccessString() throws Exception {
        Booking booking = new Booking();
        booking.setRoom(new Room());
        booking.setEmployee(new Employee());
        booking.setDate(Date.valueOf("2024-9-02"));
        booking.setStartAt(Time.valueOf("13:00:00"));
        booking.setEndAt(Time.valueOf("15:00:00"));

        TestRestTemplate restTemplate = new TestRestTemplate();

		ResponseEntity<String> response = restTemplate.postForEntity(
            "/bookings/",
            booking,
            String.class
        );

        assertEquals(response.getBody(), "The booking for 2024-9-02 at 13:00:00 was completed!");
    }

    @Test
    public void givenBookingObject_whenCreateBookingWhithPastDate_thenReturnHashWithErrorMessage() throws Exception {
    }

    @Test
    public void givenBookingObject_whenCreateBookingWhichOverlaps_thenReturnHashWithErrorMessage() throws Exception {
    }

    @Test
    public void givenBookingObject_whenCreateBookingNotHourly_thenReturnHashWithErrorMessage() throws Exception {
    }

    @Test
    public void givenBookingId_whenCancelBookingWhichExists_thenReturnSuccessString() throws Exception {
    }

    @Test
    public void givenBookingId_whenCancelBookingWhichDoesNotExist_thenReturnErrorString() throws Exception {
    }
}
