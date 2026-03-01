package com.pvz.entity.enemy;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.pvz.entity.Components;
import com.pvz.entity.Components.PositionComponent;
import com.pvz.entity.Components.SimpleMovementComponent;
import com.pvz.entity.Components.FactionComponent;
import com.pvz.entity.Faction;

public class EnemySystem {
    public static class MoveSystem extends IteratingSystem {
        private ComponentMapper<PositionComponent> positionMapper;
        private ComponentMapper<SimpleMovementComponent> moveMapper;

        public MoveSystem() {
            super(Family.all(PositionComponent.class, SimpleMovementComponent.class).get());
        }

        public void addedToEngine(Engine engine) {
            positionMapper = ComponentMapper.getFor(PositionComponent.class);
            moveMapper = ComponentMapper.getFor(SimpleMovementComponent.class);
        }

        @Override
        protected void processEntity(Entity entity, float deltaTime) {

            PositionComponent pos = positionMapper.get(entity);
            SimpleMovementComponent move = moveMapper.get(entity);

            pos.position.sub(move.speed * deltaTime, 0);
        }
    }
}
