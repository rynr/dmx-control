package org.rjung.util.dmx.control.fixture;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Color {

    private final Map<Channel, Integer> channels;

    public static final Color WHITE = Arrays.stream(Channel.values()).collect(
            Color::new,
            (color, channel) -> color.setChannel(channel, 127),
            (colorLeft, colorRight) -> colorRight.channels.forEach(colorLeft::setChannel));
    public static final Color BLACK = Arrays.stream(Channel.values()).collect(
            Color::new,
            (color, channel) -> color.setChannel(channel, 0),
            (colorLeft, colorRight) -> colorRight.channels.forEach(colorLeft::setChannel));


    public Color() {
        this.channels = new HashMap<>();
    }

    public Color setChannel(Channel channel, Integer value) {
        this.channels.put(channel, value);
        return this;
    }

    public Color clearChannel(Channel channel) {
        this.channels.remove(channel);
        return this;
    }

    public Map<Channel, Integer> getChannels() {
        return Map.copyOf(channels);
    }
}
