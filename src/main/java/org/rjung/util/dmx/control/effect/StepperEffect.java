package org.rjung.util.dmx.control.effect;

import org.rjung.util.dmx.control.fixture.Color;
import org.rjung.util.dmx.control.fixture.Effect;
import org.rjung.util.dmx.control.fixture.Group;

public class StepperEffect implements Effect {

    private final Color onColor;
    private final Color offColor;
    private final long stepMillis;
    private final Integer segments;

    public StepperEffect() {
        this(Color.WHITE, Color.BLACK, 1000, 1);
    }

    public StepperEffect(int segments, long stepMillis) {
        this(Color.WHITE, Color.BLACK, stepMillis, segments);
    }

    public StepperEffect(Color onColor, Color offColor, long stepMillis, Integer segments) {
        this.onColor = onColor;
        this.offColor = offColor;
        this.stepMillis = stepMillis;
        this.segments = segments;
    }

    @Override
    public void update(long timeMillis, int numOfFixtures, Group group) {
        var selected = ((timeMillis / stepMillis) % numOfFixtures);
        for (int i = 0; i < numOfFixtures; i++) {
            var color = around(i, (int) selected, this.segments, numOfFixtures) ? onColor : offColor;
            for (var e : color.getChannels().entrySet()) {
                group.set(i, e.getKey(), e.getValue());
            }
        }

    }

    private boolean around(int count, int step, int segments, int numOfFixtures) {
        for (int range = 0; range < segments; range++) {
            if ((step + range) % numOfFixtures == count)
                return true;
        }
        return false;
    }
}
