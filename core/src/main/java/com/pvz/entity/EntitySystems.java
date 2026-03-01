package com.pvz.entity;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntitySystems {
    public static class RenderSystem extends EntitySystem {
        private ComponentMapper<Components.TextureComponent> textureMapper;
        private ComponentMapper<Components.PositionComponent> positionMapper;
        private final SpriteBatch batch;

        public RenderSystem(SpriteBatch batch) {
            this.batch = batch;
        }


        public void addedToEngine(Engine engine) {
            textureMapper = ComponentMapper.getFor(Components.TextureComponent.class);
            positionMapper = ComponentMapper.getFor(Components.PositionComponent.class);
        }

        @Override
        public void update(float deltaTime) {
            if(!batch.isDrawing()) return;
            for (Entity entity : getEngine().getEntitiesFor(Family.all(Components.PositionComponent.class,
                Components.TextureComponent.class).get())) {
                Components.PositionComponent pos = positionMapper.get(entity);
                Components.TextureComponent texture = textureMapper.get(entity);
                batch.draw(texture.texture, pos.position.x, pos.position.y, pos.size.x, pos.size.y);
            }
        }
    }
}
