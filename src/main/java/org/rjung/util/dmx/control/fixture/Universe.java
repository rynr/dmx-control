package org.rjung.util.dmx.control.fixture;

import java.util.Collection;

public class Universe {

    private final byte[] channels;

    public Universe() {
        this.channels = new byte[512];
    }

    public byte[] getChannels() {
        return channels;
    }

    public void update(Collection<Group> groups) {
        for (Group group : groups) {
            update(group);
        }
    }

    public void update(Group group) {
        var values = group.getValues();
        for (var entry : values.entrySet()) {
            channels[entry.getKey()] = (byte) entry.getValue().intValue();
        }
    }
}
