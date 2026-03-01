package com.pvz.tool;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.pvz.entity.Components;
import com.pvz.entity.EntityBlueprint;
import com.pvz.entity.EntityRegistry;
import com.pvz.entity.Faction;
import com.pvz.entity.bullet.BulletComponents;
import com.pvz.resources.AssetsLoader;
import com.pvz.screen.ScreenManager;


public class Initialization {
    public static final int WIN_X = 1440, WIN_Y = 900;
    public static void init() {
        initAssets();
        loadEntities();
        initScreens();
    }

    private static void initScreens() {
        ScreenManager.getInstance().switchScreen("game");
    }

    private static void initAssets() {
        AssetsLoader loader = AssetsLoader.getInstance();
        loader.load("background", "background/5x9_background.png", Texture.class);
        loader.load("archer", "turret/archer.png", Texture.class);
        loader.load("hammer", "enemy/hammer.png", Texture.class);
        loader.load("bullet", "bullet/bullet.png", Texture.class);
        loader.finishLoading();
    }

    @SuppressWarnings("unchecked")
    private static void loadEntities() {
        EntityRegistry registry = EntityRegistry.getInstance();
        registry.register(new EntityBlueprint("testTurret", Array.with(
            Components.position(64, 64),
            Components.health(300),
            Components.price(150),
            Components.texture("archer"),
            Components.faction(Faction.Turret)
        )));

        registry.register(new EntityBlueprint("testEnemy", Array.with(
            Components.position(48, 48),
            Components.health(270),
            Components.texture("hammer"),
            Components.simpleMovement(20f),
            Components.faction(Faction.Enemy)
        )));

        registry.register(new EntityBlueprint("testBullet", Array.with(
            BulletComponents.damage(20),
            Components.texture("bullet"),
            Components.velocity(50, 0)
        )));
    }
}
