package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.BookingsController;
import com.example.service.BookingService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}
