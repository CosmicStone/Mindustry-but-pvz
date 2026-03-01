package com.pvz.resources;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ObjectMap;

public class AssetsLoader {
    private final AssetManager manager = new AssetManager();
    private AssetsLoader() {}
    private static final AssetsLoader instance = new AssetsLoader();
    private final ObjectMap<String, String> nameToPath = new ObjectMap<>();

    public static AssetsLoader getInstance() {
        return instance;
    }

    /**
     * 加载资源
     * @param name 资源名称
     * @param path 资源路径
     * @param type 资源类型
     */
    public <T> void load(String name, String path, Class<T> type) {
        nameToPath.put(name, path);
        manager.load(path, type);
    }

    /**
     * 获取资源，若没有名称对应的资源则抛出异常
     * @param name 资源名称
     * @param type 资源类型
     * @return 返回资源
     */
    public <T> T get(String name, Class<T> type) {
        String path = nameToPath.get(name);
        if (path == null) {
            throw new IllegalArgumentException("没有资源有这个名字：" + name);
        }
        return manager.get(path, type);
    }

    public Texture getTexture(String name) {
        String path = nameToPath.get(name);
        if (path == null) {
            throw new IllegalArgumentException("没有资源有这个名字：" + name);
        }
        return manager.get(path, Texture.class);
    }

    /**
     * 移除不需要加载资源，若没有名称对应的资源则不执行
     * @param name 资源名称
     */
    public void unload(String name) {
        String path = nameToPath.remove(name);
        if (path != null) {
            manager.unload(path);
        }
    }

    /**
     * 更新资源
     * @return 如果所有资源已被加载则返回true
     */
    public boolean update() {
        return manager.update();
    }

    /**
     * 完成加载，必须在最后的load方法后使用
     */
    public void finishLoading() {
        manager.finishLoading();
    }

    public void dispose() {
        manager.dispose();
        nameToPath.clear();
    }
}
