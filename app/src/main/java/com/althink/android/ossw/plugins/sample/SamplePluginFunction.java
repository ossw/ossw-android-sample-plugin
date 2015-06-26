package com.althink.android.ossw.plugins.sample;

/**
 * Created by krzysiek on 14/06/15.
 */
public enum SamplePluginFunction {

    INCREASE_MOD_PARAM(1, "increaseModParam"),
    DECREASE_MOD_PARAM(2, "decreaseModParam");

    private final int id;
    private final String name;

    private SamplePluginFunction(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static SamplePluginFunction resolveById(int functionId) {
        for (SamplePluginFunction function : SamplePluginFunction.values()) {
            if (function.getId() == functionId) {
                return function;
            }
        }
        return null;
    }
}
