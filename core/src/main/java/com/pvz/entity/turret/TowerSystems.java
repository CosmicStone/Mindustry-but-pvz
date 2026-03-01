package com.pvz.entity.turret;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.pvz.entity.Components;
import com.pvz.entity.Components.AttackComponent;
import com.pvz.entity.Components.PositionComponent;
import com.pvz.entity.Components.FactionComponent;
import com.pvz.entity.Components.SimpleMovementComponent;
import com.pvz.entity.Components.VelocityComponent;
import com.pvz.entity.Faction;

public class TowerSystems {
    public static class ShootSystem extends IteratingSystem {
        private ComponentMapper<AttackComponent> attackMapper;
        private ComponentMapper<PositionComponent> positionMapper;
        private ComponentMapper<FactionComponent> factionMapper;
        private Engine engine;

        public ShootSystem() {
            super(Family.all(AttackComponent.class, PositionComponent.class, FactionComponent.class).get());
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
