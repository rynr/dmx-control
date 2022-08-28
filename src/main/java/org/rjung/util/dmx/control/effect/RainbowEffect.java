package org.rjung.util.dmx.control.effect;

import org.rjung.util.dmx.control.fixture.Channel;
import org.rjung.util.dmx.control.fixture.Color;
import org.rjung.util.dmx.control.fixture.Effect;
import org.rjung.util.dmx.control.fixture.Group;

import java.math.BigDecimal;

public class RainbowEffect implements Effect {

    @Override
    public void update(long frame, int fps, int numOfFixtures, Group group) {
        for (int i = 0; i < numOfFixtures; i++) {
            var pi = Math.PI;
            // var a = ((double) frame / (double) fps) * pi * 2 + (double) i / (double) numOfFixtures;
            var a = (((double) frame / (double) fps) + ((double) i / numOfFixtures)) * pi * 2;
            var r = (1 + Math.sin(a)) / 2.0 * 255;
            var g = (1 + Math.sin(a + (pi * 2 / 3))) / 2.0 * 255;
            var b = (1 + Math.sin(a + (pi * 4 / 3))) / 2.0 * 255;
            group.set(i, Channel.R, (int) r);
            group.set(i, Channel.G, (int) g);
            group.set(i, Channel.B, (int) b);
        }
    }

}
