package org.rjung.util.dmx.control.util;

import java.util.Collection;

public class Validation {

    // UtilityClass cannot be instantiated
    private Validation() {
    }

    public static void validateDmxValues(Collection<Integer> values) {
        values.forEach(Validation::validateDmxValue);
    }

    public static void validateDmxValue(Integer value) {
        if (value == null || value < 0 || value > 255)
            throw new IllegalArgumentException("Argument needs to be within 0 and 255");
    }

}
