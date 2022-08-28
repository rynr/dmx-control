package org.rjung.util.dmx.control.service;

import ch.bildspur.artnet.ArtNetClient;
import org.rjung.util.dmx.control.effect.RainbowEffect;
import org.rjung.util.dmx.control.effect.StepperEffect;
import org.rjung.util.dmx.control.fixture.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.Closeable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

@Component
public class DmxService implements Closeable {
    public final static int FRAMERATE = 20;
    public final static int FRAMETIME = 1000 / FRAMERATE;

    private final AtomicBoolean running;
    private final AtomicLong frameCounter;
    private final List<Group> groups;
    private final ArtNetClient artnet;
    private final Universe universe;

    public DmxService() {
        this.running = new AtomicBoolean(false);
        this.frameCounter = new AtomicLong();
        this.groups = List.of(
                new Group(IntStream.range(0, 8)
                        .mapToObj(i -> new Fixture(i * FixtureType.RGB.getChannels().size(), FixtureType.RGB))
                        .toList()),
                new Group(IntStream.range(0, 8 * 4)
                        .mapToObj(i -> new Fixture(217 + i * FixtureType.RGB.getChannels().size(), FixtureType.RGB))
                        .toList())
        );
        groups.forEach(g -> g.setEffects(Map.of(
                1, List.of(new StepperEffect(1)),
                2, List.of(new StepperEffect(2)),
                3, List.of(new RainbowEffect())
        )));
        this.artnet = new ArtNetClient();
        this.universe = new Universe();
    }

    @Scheduled(fixedRate = FRAMETIME)
    public void sendDmx() {
        if (running.get()) {
            var frame = frameCounter.incrementAndGet();
            for (var group : groups) {
                group.update(frame, FRAMERATE);
            }

            universe.update(groups);

            artnet.unicastDmx("192.168.1.41", 0, 0, universe.getChannels());
            System.out.println(groups);
            System.out.flush();
        }
    }

    public void setProgram(Integer program) {
        this.groups.forEach(g -> g.setProgram(program));
    }

    public void start() {
        this.running.set(true);
        this.artnet.start();
    }

    @Override
    public void close() {
        this.running.set(false);
        this.artnet.stop();
    }
}
