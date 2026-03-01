package com.pvz.entity.turret;

import com.badlogic.ashley.core.Component;

import java.util.function.Supplier;

public class TowerComponents {
    public static class ProductionComponent implements Component {
        public float interval;
        public float timer;
        public int amount;
        public String resourceId;

        public ProductionComponent(float interval, float timer, int amount, String resourceId) {
            this.interval = interval;
            this.timer = timer;
            this.amount = amount;
            this.resourceId = resourceId;
        }
    }
    public static Supplier<Component> production(float interval, String resourceId) {
        return production(interval, 0f, 1, resourceId);
    }
    public static Supplier<Component> production(float interval, float timer, String resourceId) {
        return production(interval, timer, 1, resourceId);
    }
    public static Supplier<Component> production(float interval, float timer, int amount, String resourceId) {
        return () -> new ProductionComponent(interval, timer, amount, resourceId);
    }
}
