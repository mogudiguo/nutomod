package com.nutonmod;

import com.nutonmod.entity.EnergyBeingRenderer;
import com.nutonmod.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class NutonModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // 注册能量人实体渲染器
        EntityRendererRegistry.register(ModEntities.ENERGY_BEING, EnergyBeingRenderer::new);
    }
}
