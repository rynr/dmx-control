package org.rjung.util.dmx.control.fixture;

import org.rjung.util.dmx.control.util.Validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Color {

    private Map<Channel, Integer> channels;

    public static final Color WHITE = Arrays.stream(Channel.values())
            .collect(
                    Color::new,
                    (color, channel) -> color.setChannel(channel, 255),
                    (colorLeft, colorRight) -> colorRight.channels.forEach(colorLeft::setChannel))
            .lock();
    public static final Color BLACK = Arrays.stream(Channel.values())
            .collect(
                    Color::new,
                    (color, channel) -> color.setChannel(channel, 0),
                    (colorLeft, colorRight) -> colorRight.channels.forEach(colorLeft::setChannel))
            .lock();


    public Color() {
        this(new HashMap<>());
    }

    public Color(Map<Channel, Integer> channels) {
        Validation.validateDmxValues(channels.values());
        this.channels = channels;
    }

    public Color setChannel(Channel channel, Integer value) {
        Validation.validateDmxValue(value);
        this.channels.put(channel, value);
        return this;
    }

    public Color clearChannel(Channel channel) {
        this.channels.remove(channel);
        return this;
    }

    public Color lock() {
        this.channels = Map.copyOf(this.channels);
        return this;
    }

    public Map<Channel, Integer> getChannels() {
        return Map.copyOf(channels);
    }
}
