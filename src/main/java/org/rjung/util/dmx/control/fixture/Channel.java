package org.rjung.util.dmx.control.fixture;

public enum Channel {
    R("red"), G("green"), B("blue"),
    W("white"),
    A("amber"),
    UV("uv"),
    X("x-axis"),
    Y("y-axis"),
    SW("switch");

    private final String name;

    Channel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
