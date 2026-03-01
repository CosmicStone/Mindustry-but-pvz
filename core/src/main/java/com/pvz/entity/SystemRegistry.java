package com.pvz.entity;

import com.badlogic.gdx.utils.ObjectMap;

public class SystemRegistry {
    private static SystemRegistry instance;
    private ObjectMap<String, SystemBlueprint> blueprints = new ObjectMap<>();

    private SystemRegistry() {}

    public static SystemRegistry getInstance() {
        if (instance == null) instance = new SystemRegistry();
        return instance;
    }

    public void register(SystemBlueprint blueprint) {
        blueprints.put(blueprint.id, blueprint);
    }

    public SystemBlueprint get(String id) {
        return blueprints.get(id);
    }
}
