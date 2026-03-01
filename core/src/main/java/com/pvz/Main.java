package com.pvz;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.pvz.resources.AssetsLoader;
import com.pvz.screen.ScreenManager;
import com.pvz.tool.Initialization;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private static final float STEP_TIME = 1/60f; // 固定时间步长（60次/秒）
    private float accumulator = 0f;
    private ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        Gdx.graphics.setForegroundFPS(60);
        Initialization.init();
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();
        accumulator += deltaTime;

        while (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME;
        }

        ScreenManager.getInstance().render(deltaTime - accumulator);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.line(32,725,1440-180,725);
        shapeRenderer.line(1440-180,725,1440-180,900);
        shapeRenderer.line(32,725,32,900);
        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        ScreenManager.getInstance().dispose();
        AssetsLoader.getInstance().dispose();
        shapeRenderer.dispose();
    }
}
