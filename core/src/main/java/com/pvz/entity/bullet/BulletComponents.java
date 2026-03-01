package com.pvz.entity.bullet;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

import java.util.function.Supplier;

public class BulletComponents {
    public static class DamageComponent implements Component {
        public int damage;

        public DamageComponent(int damage) {
            this.damage = damage;
        }
    }

    public static Supplier<Component> damage(int damage) {
        return () -> new DamageComponent(damage);
    }

    public static class TargetComponent implements Component {
        public Entity target; // 目标实体
        public TargetComponent(Entity target) { this.target = target; }
    }
    public static Supplier<Component> target(Entity target) {
        return () -> new TargetComponent(target);
    }
}
