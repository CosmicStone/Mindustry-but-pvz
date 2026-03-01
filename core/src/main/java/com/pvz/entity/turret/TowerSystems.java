package com.pvz.entity.turret;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.pvz.entity.Components;
import com.pvz.entity.Components.AttackComponent;
import com.pvz.entity.Components.PositionComponent;
import com.pvz.entity.Components.FactionComponent;
import com.pvz.entity.Faction;

public class TowerSystems {
    public static class LineShootingSystem extends IteratingSystem {
        private ComponentMapper<AttackComponent> attackMapper;
        private ComponentMapper<PositionComponent> positionMapper;
        private ComponentMapper<FactionComponent> factionMapper;
        private Engine engine;

        public LineShootingSystem() {
            super(Family.all(AttackComponent.class, PositionComponent.class, FactionComponent.class)
                .exclude(Components.SimpleMovementComponent.class, Components.VelocityComponent.class).get());
        }

        public void addedToEngine(Engine engine) {
            super.addedToEngine(engine);
            this.engine = engine;
            attackMapper = ComponentMapper.getFor(AttackComponent.class);
            positionMapper = ComponentMapper.getFor(PositionComponent.class);
            factionMapper = ComponentMapper.getFor(FactionComponent.class);
        }

        @Override
        protected void processEntity(Entity turret, float deltaTime) {
            FactionComponent faction = factionMapper.get(turret);
            if (faction.faction != Faction.Turret) return; // 只处理植物

            AttackComponent attack = attackMapper.get(turret);
            PositionComponent pos = positionMapper.get(turret);

            attack.timer += deltaTime;
            if (attack.timer >= attack.cooldown) {

                //Entity enemy = findEnemy();

                if (true) {
                    createBullet(turret, pos.position.add(pos.size.scl(1/2f))); // 从炮塔中心发射
                    attack.timer -= attack.cooldown;
                }
            }
        }

        private void createBullet(Entity turret, Vector2 position) {
            Entity bullet = new Entity();
        }
    }

    public static class Productionystem extends IteratingSystem {
        private ComponentMapper<TowerComponents.ProductionComponent> productionMapper;
        private ComponentMapper<Components.PositionComponent> positionMapper;

        public Productionystem() {
            super(Family.all(TowerComponents.ProductionComponent.class, TowerComponents.ProductionComponent.class).get());
        }

        public void addedToEngine(Engine engine) {
            productionMapper = ComponentMapper.getFor(TowerComponents.ProductionComponent.class);
            positionMapper = ComponentMapper.getFor(Components.PositionComponent.class);
        }

        @Override
        protected void processEntity(Entity entity, float deltaTime) {

        }
    }
}
