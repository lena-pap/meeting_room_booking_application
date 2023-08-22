package com.example.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { TimeSlotValidator.class })
@Documented
public @interface TimeSlotValidation {

    String message() default "The time slot is not valid.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
