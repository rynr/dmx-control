package org.rjung.util.dmx.control.fixture;

public interface Effect {
    void update(long instant, int numOfFixtures, Group group);
}
