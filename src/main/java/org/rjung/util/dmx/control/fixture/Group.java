package org.rjung.util.dmx.control.fixture;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Group {
    private final List<Fixture> fixtures;
    private Map<Integer, List<Effect>> effects;
    private Integer program;

    public Group(List<Fixture> fixtures) {
        this.fixtures = fixtures;
        this.setProgram(1);
    }

    public void setEffects(Map<Integer, List<Effect>> effects) {
        this.effects = effects;
    }

    public void setProgram(Integer program) {
        this.program = program;
    }

    public void update(long time, int fps) {
        for (var effect : effects.get(program)) {
            effect.update(time, fps, fixtures.size(), this);
        }
    }

    public Map<Integer, Integer> getValues() {
        return this.fixtures.stream()
                .map(Fixture::getValues)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void set(int index, Channel color, int value) {
        fixtures.get(index).set(color, value);
    }

    @Override
    public String toString() {
        return "[" + fixtures.stream().map(Fixture::toString).collect(Collectors.joining(",")) + "]";
    }
}
