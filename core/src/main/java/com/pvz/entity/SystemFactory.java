package com.pvz.entity;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;

public class SystemFactory {
    private Engine engine;

    public SystemFactory(Engine engine) {
        this.engine = engine;
    }

    public EntitySystem createSystem(String blueprintId) {
        SystemBlueprint blueprint = SystemRegistry.getInstance().get(blueprintId);
        if (blueprint == null) {
            throw new IllegalArgumentException("Unknown system blueprint: " + blueprintId);
        }
        // 可以在这里设置系统的优先级（如果系统支持动态设置）
        // 但 Ashley 的 EntitySystem 优先级在添加时指定，所以我们需要在添加时处理
        return blueprint.supplier.get();
    }

    public void addSystem(String blueprintId) {
        EntitySystem system = createSystem(blueprintId);
        engine.addSystem(system);
        // 如果需要按优先级排序，可以在添加后重新排序引擎中的系统
        // 但 Engine 内部维护的是按优先级排序的列表，添加时会自动插入正确位置
    }

    public void removeSystem(String blueprintId) {
        // 需要遍历引擎找到对应ID的系统并移除，这需要系统本身持有ID或通过其他方式映射
        // 简单起见，可以遍历引擎的所有系统，根据类名或自定义标签移除
        for (EntitySystem sys : engine.getSystems()) {
            // 如果系统实现了带ID的接口，或者我们可以通过类名判断
            // 这里假设系统类名与 blueprintId 有对应关系
            if (sys.getClass().getSimpleName().equals(blueprintId)) {
                engine.removeSystem(sys);
                break;
            }
        }
    }
}
