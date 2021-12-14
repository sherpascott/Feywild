package com.feywild.feywild.entity.render;

import com.feywild.feywild.FeywildMod;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import javax.annotation.Nonnull;

public class FeyBoatRenderer extends BoatRenderer {

    private static final ResourceLocation AUTUMN_TEXTURE = new ResourceLocation(FeywildMod.getInstance().modid, "textures/entity/boat/autumn.png");

    public FeyBoatRenderer(EntityRendererProvider.Context manager) {
        super(manager);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull Boat entity) {
        return AUTUMN_TEXTURE;
    }
}
