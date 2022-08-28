package org.rjung.util.dmx.control.fixture;

import java.util.List;

public enum FixtureType {
    RGB(List.of(Channel.R, Channel.G, Channel.B));

    private final List<Channel> channels;

    private FixtureType(List<Channel> colors) {
        this.channels = colors;
    }

    public List<Channel> getChannels() {
        return channels;
    }
}
