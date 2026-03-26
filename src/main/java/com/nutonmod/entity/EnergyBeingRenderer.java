package com.nutonmod.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class EnergyBeingRenderer extends MobEntityRenderer<EnergyBeing, BipedEntityModel<EnergyBeing>> {
    
    public EnergyBeingRenderer(EntityRendererFactory.Context context) {
        super(context, new BipedEntityModel<>(context.getPart(EntityModelLayers.PLAYER)), 0.5f);
    }
    
    @Override
    public Identifier getTexture(EnergyBeing entity) {
        return Identifier.of("nutonmod", "textures/entity/energy_being.png");
    }
}
