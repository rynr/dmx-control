package org.rjung.util.dmx.control.util;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void validateDmxValuesForValidDmxValues() {
        IntStream.range(0, 256).forEach(Validation::validateDmxValue);
    }

    @Test
    void validateDmxValuesForNullFails() {
        var exception = assertThrows(IllegalArgumentException.class, () ->
                Validation.validateDmxValue(null));
        assertThat(exception.getMessage(), is("Argument needs to be within 0 and 255"));
    }

    @Test
    void validateDmxValuesForNumbersBelowZero() {
        IntStream.range(-10, 0).forEach(invalidNumber -> {
            var exception = assertThrows(IllegalArgumentException.class,
                    () -> Validation.validateDmxValue(invalidNumber),
                    () -> "Failed Verifying the invalid number " + invalidNumber);
            assertThat(exception.getMessage(), is("Argument needs to be within 0 and 255"));
        });
    }

    @Test
    void validateDmxValuesForNumbersGreaterThan255() {
        IntStream.range(256, 280).forEach(invalidNumber -> {
            var exception = assertThrows(IllegalArgumentException.class,
                    () -> Validation.validateDmxValue(invalidNumber),
                    () -> "Failed Verifying the invalid number " + invalidNumber);
            assertThat(exception.getMessage(), is("Argument needs to be within 0 and 255"));
        });
    }

}