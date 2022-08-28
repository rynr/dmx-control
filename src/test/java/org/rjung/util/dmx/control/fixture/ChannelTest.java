package org.rjung.util.dmx.control.fixture;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class ChannelTest {

    @Test
    void channelNamesShouldBeUnique() {
        var numberOfChannels = Channel.values().length;
        var numberOfDistinctChannelNames = Stream.of(Channel.values()) // stream all Channels
                .map(Channel::getName) // get the name of the channels
                .map(String::toLowerCase) // also check independent on the case
                .collect(Collectors.toSet()) // The set takes care of unique names
                .size();

        assertThat(numberOfDistinctChannelNames, is(numberOfChannels));
    }

}