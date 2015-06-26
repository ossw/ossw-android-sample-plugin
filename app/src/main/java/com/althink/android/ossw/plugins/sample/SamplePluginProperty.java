package com.althink.android.ossw.plugins.sample;

import com.althink.android.ossw.plugins.api.PluginPropertyType;

/**
 * Created by krzysiek on 14/06/15.
 */
public enum SamplePluginProperty {
    INT_PARAM(1, "intParam", PluginPropertyType.INTEGER),
    FLOAT_PARAM(2, "floatParam", PluginPropertyType.FLOAT),
    STRING_PARAM(3, "stringParam", PluginPropertyType.STRING),
    ENUM_PARAM(4, "enumParam", PluginPropertyType.ENUM),
    MOD_PARAM(5, "modParam", PluginPropertyType.INTEGER);

    private int id;
    private String name;
    private PluginPropertyType type;

    private SamplePluginProperty(int id, String name, PluginPropertyType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public static SamplePluginProperty resolveByName(String propertyName) {
        for (SamplePluginProperty property : SamplePluginProperty.values()) {
            if (property.getName().equals(propertyName)) {
                return property;
            }
        }
        return null;
    }

    public PluginPropertyType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
