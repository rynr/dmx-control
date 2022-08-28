package org.rjung.util.dmx.control.fixture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Fixture {
    private final Map<Channel, Integer> channels;
    private final Map<Integer, Integer> values;

    public Fixture(int startAddress, FixtureType channels) {
        this.channels = new HashMap<>();
        this.values = new HashMap<>();
        List<Channel> colors = channels.getChannels();
        for (int i = 0; i < colors.size(); i++) {
            var index = startAddress + i;
            this.channels.put(colors.get(i), index);
            this.values.put(index, 0);
        }
    }

    public Map<Integer, Integer> getValues() {
        return Map.copyOf(values);
    }

    public void set(Channel color, int value) {
        if (channels.containsKey(color))
            values.put(channels.get(color), value);
    }

    @Override
    public String toString() {
        return
                values.keySet().stream().sorted()
                        .map(k -> toChar(values.get(k)))
                        .collect(Collectors.joining());
    }

    private String toChar(int value) {
        if (value < 29)
            return " ";
        else if (value < 57)
            return "▁";
        else if (value < 86)
            return "▂";
        else if (value < 114)
            return "▃";
        else if (value < 143)
            return "▄";
        else if (value < 171)
            return "▅";
        else if (value < 200)
            return "▆";
        else if (value < 228)
            return "▇";
        else
            return "█";
    }
}
