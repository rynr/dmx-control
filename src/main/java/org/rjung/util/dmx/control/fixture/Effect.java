package org.rjung.util.dmx.control.fixture;

public interface Effect {
    void update(long frame, int fps, int numOfFixtures, Group group);
}
