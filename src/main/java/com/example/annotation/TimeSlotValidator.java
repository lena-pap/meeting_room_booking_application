package com.example.annotation;

import java.sql.Date;
import java.time.LocalDate;

import com.example.model.Booking;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/* This custom validator checks the validity of start and end times. */
public class TimeSlotValidator implements ConstraintValidator<TimeSlotValidation, Booking> {
  @Override
  public void initialize(TimeSlotValidation constraintAnnotation) {
  }

  @Override
  public boolean isValid(Booking booking, ConstraintValidatorContext context) {
    // First check if the start is before the end time.
    if (booking.getStartAt().after(booking.getEndAt())) {
      return false;
    }

    // Then, in case the date is today, check if the start date is future.
    Date current_date = Date.valueOf(LocalDate.now());
    if (current_date == booking.getDate() && booking.getStartAt().before(current_date)) {
      return false;
    }

    // Lastly, check if the time slot is an hour, or a multiple of an hour.
    long duration = booking.getEndAt().getTime() - booking.getStartAt().getTime();
    return duration % 3600000 == 0;
  }
}
