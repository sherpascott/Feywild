package com.feywild.feywild.item;

import com.feywild.feywild.FeyPlayerData;
import com.feywild.feywild.FeywildMod;
import com.feywild.feywild.config.MiscConfig;
import com.feywild.feywild.config.data.ScrollSelectType;
import com.feywild.feywild.network.OpeningScreenMessage;
import com.feywild.feywild.util.TooltipHelper;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.registries.ForgeRegistries;
import org.moddingx.libx.base.ItemBase;
import org.moddingx.libx.mod.ModX;
import vazkii.patchouli.api.PatchouliAPI;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class FeywildLexicon extends ItemBase {

    public FeywildLexicon(ModX mod, Properties properties) {
        super(mod, properties);
    }

    @Nonnull
    @Override
    public InteractionResultHolder<ItemStack> use(@Nonnull Level levelIn, @Nonnull Player player, @Nonnull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player instanceof ServerPlayer) {
            if (!FeyPlayerData.get(player).getBoolean("feywild_got_scroll") && MiscConfig.initial_scroll == ScrollSelectType.BOOK) {
                FeywildMod.getNetwork().channel.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) player), new OpeningScreenMessage());
                // feywild_got_scroll is set when the player actually retrieves a scroll
            } else {
                PatchouliAPI.get().openBookGUI((ServerPlayer) player, Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(this)));
            }
        }
        return new InteractionResultHolder<>(InteractionResult.FAIL, stack);
    }

    @Override
    public void appendHoverText(@Nonnull ItemStack stack, @Nullable Level level, @Nonnull List<Component> tooltip, @Nonnull TooltipFlag flag) {
        TooltipHelper.addTooltip(tooltip, Component.translatable("message.feywild.feywild_lexicon"));
        super.appendHoverText(stack, level, tooltip, flag);
    }
}
