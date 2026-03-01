package com.pvz.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ObjectMap;

public class ScreenManager {
    private final ObjectMap<String, Screen> screens = new ObjectMap<>();
    private Screen currentScreen = null;

    private final SpriteBatch batch;

    private static final ScreenManager instance = new ScreenManager();
    private ScreenManager () {
        batch = new SpriteBatch();
        GameScreen game = new GameScreen(batch);
        registerScreen("game", game);
    }
    public static ScreenManager getInstance() {
        return instance;
    }

    public void registerScreen(String name, Screen screen) {
        screens.put(name, screen);
    }

    public void registerScreen(ObjectMap<String, Screen> screens) {
        screens.putAll(screens);
    }

    public boolean switchScreen(String name) {
        Screen temp = screens.get(name);
        if (temp == null) return false;
        if(currentScreen != null) {
            currentScreen.hide();
        }
        currentScreen = temp;
        currentScreen.show();
        return true;
    }

    public void render(float delta) {
        currentScreen.render(delta);
    }

    public void dispose() {
        for (Screen screen : screens.values()) {
            screen.dispose();
        }
        screens.clear();
        currentScreen = null;
        batch.dispose();
    }

    public Screen getCurrentScreen() {
        return currentScreen;
    }
}
