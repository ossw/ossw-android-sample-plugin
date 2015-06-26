package com.althink.android.ossw.plugins.api;

/**
 * Created by krzysiek on 24/06/15.
 */
public enum PluginPropertyType {
    INTEGER(Integer.class), FLOAT(Float.class), STRING(String.class), ENUM(Integer.class);

    private Class clazz;

    private PluginPropertyType(Class clazz) {
        this.clazz = clazz;
    }

    public boolean isTypeSupported(Object object) {
        return object == null || object.getClass().equals(clazz);
    }
}
