package com.pvz.entity;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pvz.resources.AssetsLoader;

import java.util.function.Supplier;

public class Components {
    public static class HealthComponent implements Component {
        public int hp;
        public int maxHp;

        public HealthComponent(int maxHp) {
            this.maxHp = maxHp;
            hp = maxHp;
        }
    }

    public static Supplier<Component> health(int maxHp) {
        return () -> new HealthComponent(maxHp);
    }

    public static class PriceComponent implements Component {
        public int price;

        public PriceComponent(int price) {
            this.price = price;
        }
    }
    public static Supplier<Component> price(int price) {
        return () -> new PriceComponent(price);
    }

    public static class TextureComponent implements Component {
        public Texture texture;

        public TextureComponent(Texture texture) {
            this.texture = texture;
        }
    }
    public static Supplier<Component> texture(String textureName) {
        return () -> new TextureComponent(AssetsLoader.getInstance().getTexture(textureName));
    }

    public static class PositionComponent implements Component {
        public Rectangle rect;

        public PositionComponent(Rectangle rect) {
            this.rect = rect;
        }

        public PositionComponent(float x, float y, float width, float height) {
            rect = new Rectangle(x, y, width, height);
        }
    }
    public static Supplier<Component> position(float x, float y, float width, float height) {
        return () -> new PositionComponent(x, y, width, height);
    }
    public static Supplier<Component> position(float width, float height) {
        return position(0, 0, width, height);
    }

    public static class AttackComponent implements Component {
        public float multiplier;
        public int amount;
        public float cooldown;
        public float timer;

        public AttackComponent(float multiplier, int amount, float cooldown, float timer) {
            this.multiplier = multiplier;
            this.amount = amount;
            this.cooldown = cooldown;
            this.timer = timer;
        }
    }
    public static Supplier<Component> attack(int amount, float cooldown) {
        return attack(1f, amount,cooldown, 0);
    }
    public static Supplier<Component> attack(int amount, float cooldown, float timer) {
        return attack(1f, amount, cooldown, timer);
    }
    public static Supplier<Component> attack(float multiplier, int amount, float cooldown) {
        return attack(multiplier, 0, cooldown, 0);
    }
    public static Supplier<Component> attack(float multiplier, int amount, float cooldown, float timer) {
        return () -> new AttackComponent(multiplier, amount, cooldown, timer);
    }

    public static class FactionComponent implements Component {
        public Faction faction;

        public FactionComponent(Faction faction) {
            this.faction = faction;
        }
    }
    public static Supplier<Component> faction(Faction faction) {
        return () -> new FactionComponent(faction);
    }

    public static class VelocityComponent implements Component {
        public Vector2 velocity;

        public VelocityComponent() {
            velocity = Vector2.Zero;
        }
        public VelocityComponent(Vector2 velocity) {
            this.velocity = velocity;
        }
        public VelocityComponent(float speed, Vector2 direction) {
            if(direction.isUnit()) {
                direction.nor();
            }
            velocity = direction.scl(speed);
        }
        public VelocityComponent(float speed, float degrees) {
            velocity = Vector2.X.rotateDeg(degrees).scl(speed);
        }
    }
    public static Supplier<Component> velocity() {
        return VelocityComponent::new;
    }
    public static Supplier<Component> velocity(Vector2 velocity) {
        return () -> new VelocityComponent(velocity);
    }
    public static Supplier<Component> velocity(float speed, Vector2 direction) {
        return () -> new VelocityComponent(speed, direction);
    }
    public static Supplier<Component> velocity(float speed, float degrees) {
        return () -> new VelocityComponent(speed, degrees);
    }

    public static class SimpleMovementComponent implements Component {
        public float speed;
        public boolean isReverse;

        public SimpleMovementComponent(float speed, boolean isReverse) {
            this.speed = speed;
            this.isReverse = isReverse;
        }
    }
    public static Supplier<Component> simpleMovement(float speed, boolean isReverse) {
        return () -> new SimpleMovementComponent(speed, isReverse);
    }
    public static Supplier<Component> simpleMovement(float speed) {
        return () -> new SimpleMovementComponent(speed, false);
    }

    public static class BulletOffsetPositionComponent implements Component {
        public Vector2 offset;
        public BulletOffsetPositionComponent(Vector2 offset) {
            this.offset = offset;
        }
        public BulletOffsetPositionComponent(float dx, float dy) {
            offset = new Vector2(dx, dy);
        }
    }
    public static Supplier<Component> offset(Vector2 offset) {
        return () -> new BulletOffsetPositionComponent(offset);
    }
    public static Supplier<Component> offset(float dx, float dy) {
        return () -> new BulletOffsetPositionComponent(dx, dy);
    }
}
