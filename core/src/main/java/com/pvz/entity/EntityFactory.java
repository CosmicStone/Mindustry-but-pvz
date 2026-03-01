package com.pvz.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

public class EntityFactory {
    private Engine engine;
    private final EntityRegistry registry;

    public EntityFactory(Engine engine) {
        this.engine = engine;
        registry = EntityRegistry.getInstance();
    }

    public Entity create(String blueprintId, float x, float y) {
        EntityBlueprint blueprint = registry.get(blueprintId);
        if (blueprint == null) {
            throw new IllegalArgumentException("Unknown blueprint: " + blueprintId);
        }
        return blueprint.create(engine, x, y);
    }

    public Engine getEngine() { return engine; }
}
