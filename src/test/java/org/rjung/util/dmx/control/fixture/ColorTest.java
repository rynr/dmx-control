package org.rjung.util.dmx.control.fixture;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ColorTest {

    @Test
    void staticWhiteColorShouldHaveAllChannelsToTheMaximum() {
        Set<Integer> colorValues = new HashSet<>(Color.WHITE.getChannels().values());
        var uniqueValues = colorValues.size();
        assertThat(uniqueValues, is(1));
        assertThat(colorValues.iterator().next(), is(255));
    }

    @Test
    void staticBlackColorShouldHaveAllChannelsToTheMinimum() {
        Set<Integer> colorValues = new HashSet<>(Color.BLACK.getChannels().values());
        var uniqueValues = colorValues.size();
        assertThat(uniqueValues, is(1));
        assertThat(colorValues.iterator().next(), is(0));
    }

    @Test
    void newColorShouldHaveNoChannels() {
        var color = new Color();
        Map<Channel, Integer> channels = color.getChannels();
        assertThat(channels.size(), is(0));
    }

    @Test
    void newColorWithEmptyChannelMapContainsNoChannels() {
        var color = new Color(Map.of());
        Map<Channel, Integer> channels = color.getChannels();
        assertThat(channels.size(), is(0));
    }

    @Test
    void newColorWithChannelMapContainsTheGivenChannels() {
        var color = new Color(Map.of(Channel.R, 0, Channel.UV, 255));
        Map<Channel, Integer> channels = color.getChannels();
        assertThat(channels.size(), is(2));
        assertThat(channels.keySet(), containsInAnyOrder(Channel.R, Channel.UV));
    }

    @Test
    void newColorWithChannelMapContainsTheGivenChannelsValues() {
        var color = new Color(Map.of(Channel.R, 0, Channel.UV, 255));
        Map<Channel, Integer> channels = color.getChannels();
        assertThat(channels.size(), is(2));
        assertThat(channels.get(Channel.R), is(0));
        assertThat(channels.get(Channel.UV), is(255));
    }

    @Test
    void newColorWithChannelMapReturnsThisChannelsValuesAsImmutable() {
        Map<Channel, Integer> expectedChannels = new HashMap<>();
        expectedChannels.put(Channel.R, 0);
        expectedChannels.put(Channel.UV, 255);

        var color = new Color(expectedChannels);
        Map<Channel, Integer> channels = color.getChannels();

        assertThat(channels, is(expectedChannels));
        assertThrows(UnsupportedOperationException.class,
                () -> channels.put(Channel.W, 1));
    }

    @Test
    void newColorWithChannelMapThrowsOnInvalidValues() {
        var invalidValues = List.of(-1, 256);
        for (var invalidValue : invalidValues) {
            var exception = assertThrows(IllegalArgumentException.class, () ->
                    new Color(Map.of(Channel.R, invalidValue)));

            assertThat(exception.getMessage(), is("Argument needs to be within 0 and 255"));
        }
    }

    @Test
    void settingChannelOfLockedColorThrowsException() {
        var locakedColor = new Color().lock();

        assertThrows(UnsupportedOperationException.class,
                () -> locakedColor.setChannel(Channel.W, 1));
    }

    @Test
    void clearingChannelOfLockedColorThrowsException() {
        var locakedColor = new Color().lock();

        assertThrows(UnsupportedOperationException.class,
                () -> locakedColor.clearChannel(Channel.W));
    }

}