package com.pvz.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.pvz.entity.EntityFactory;
import com.pvz.entity.EntitySystem;
import com.pvz.entity.enemy.EnemySystem;
import com.pvz.grid.Grid;
import com.pvz.resources.AssetsLoader;

public class GameScreen implements Screen, InputProcessor {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Grid grid;
    private Rectangle tempRect;
    private boolean mouseDown = false;
    private OrthographicCamera camera;
    private Engine engine;
    private EntityFactory factory;

    public GameScreen(SpriteBatch batch) {
        this.batch = batch;
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();

    }

    @Override
    public void show() {
        grid = new Grid(5, 9, 32, 32, 150, 126, AssetsLoader.getInstance().get("background", Texture.class));
        Gdx.input.setInputProcessor(this);
        camera.setToOrtho(false, 1440, 900);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        engine = new Engine();
        factory = new EntityFactory(engine);

        engine.addSystem(new EnemySystem.MoveSystem());
        engine.addSystem(new EntitySystem.RenderSystem(batch));

        Entity testTurret = factory.create("testTurret", 0, 0);
        Entity testEnemy = factory.create("testEnemy", 640, 0);
    }

    @Override
    public void render(float delta) {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        grid.render(batch, shapeRenderer);
        shapeRenderer.end();

        batch.begin();
        engine.update(delta);
        batch.end();

        if (tempRect != null) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            if (mouseDown)
                shapeRenderer.setColor(Color.GREEN);
            else
                shapeRenderer.setColor(Color.RED);
            shapeRenderer.rect(tempRect.x, tempRect.y, tempRect.width, tempRect.height);
            shapeRenderer.end();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    //--------输入--------//
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            mouseDown = true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {

        }
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
