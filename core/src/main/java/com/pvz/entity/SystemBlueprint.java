package com.pvz.entity;

import com.badlogic.ashley.core.EntitySystem;

import java.util.function.Supplier;

public class SystemBlueprint {
    public String id;
    public int priority;
    public Supplier<EntitySystem> supplier;

    public SystemBlueprint(String id, int priority, Supplier<EntitySystem> supplier) {
        this.id = id;
        this.priority = priority;
        this.supplier = supplier;
    }
}
