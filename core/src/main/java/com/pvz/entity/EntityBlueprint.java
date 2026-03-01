package com.pvz.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.utils.Array;

import java.util.function.Supplier;

public class EntityBlueprint {
    public String id;
    public Array<Supplier<Component>> componentSuppliers;

    public EntityBlueprint(String id, Array<Supplier<Component>> componentSuppliers) {
        this.id = id;
        this.componentSuppliers = componentSuppliers;
    }

    public Entity create(Engine engine, float x, float y) {
        Entity entity = new Entity();
        for (Supplier<Component> supplier : componentSuppliers) {
            Component comp = supplier.get();
            if (comp instanceof Components.PositionComponent) {
                ((Components.PositionComponent) comp).position.x = x;
                ((Components.PositionComponent) comp).position.y = y;
            }
            entity.add(comp);
        }
            engine.addEntity(entity);
            return entity;
    }
}
