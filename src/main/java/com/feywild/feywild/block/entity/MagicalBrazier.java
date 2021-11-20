package com.feywild.feywild.block.entity;

import io.github.noeppi_noeppi.libx.mod.registration.TileEntityBase;
import net.minecraft.tileentity.TileEntityType;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class MagicalBrazier extends TileEntityBase implements IAnimatable {

    private final AnimationFactory animationFactory = new AnimationFactory(this);

    private int textureNumber = 1;
    private int animationCount = 0;

    public MagicalBrazier(TileEntityType<?> type) {
        super(type);
    }

    /* SAVE & LOAD */

    public int getTextureNumber() {
        if (!(animationCount == 10)) {
            animationCount++;
        } else {
            animationCount = 0;

            if (!(textureNumber == 8)) {
                textureNumber++;
            } else {
                textureNumber = 1;

            }
        }

        return textureNumber;
    }

    /* ANIMATION */
    private <E extends IAnimatable> PlayState animationPredicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.magical_brazier.hover", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData animationData) {
        animationData.addAnimationController(new AnimationController<>(this, "controller", 0, this::animationPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.animationFactory;
    }
}
