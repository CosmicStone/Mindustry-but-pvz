package com.pvz.entity;

import com.badlogic.gdx.utils.ObjectMap;

public class EntityRegistry {
    private static EntityRegistry instance;
    private final ObjectMap<String, EntityBlueprint> blueprints = new ObjectMap<>();

    private EntityRegistry() {}

    public static EntityRegistry getInstance() {
        if (instance == null) instance = new EntityRegistry();
        return instance;
    }

    public void register(EntityBlueprint blueprint) {
        if(blueprints.containsKey(blueprint.id)) {
            return;
        }
        blueprints.put(blueprint.id, blueprint);
    }

    public EntityBlueprint get(String id) {
        return blueprints.get(id);
    }

    // 批量注册
    public void registerAll(EntityBlueprint... blueprints) {
        for (EntityBlueprint bp : blueprints) {
            register(bp);
        }
    }
}
