package com.feywild.feywild.block.decorative;

import com.google.common.collect.ImmutableSet;
import io.github.noeppi_noeppi.libx.mod.ModX;
import io.github.noeppi_noeppi.libx.mod.registration.Registerable;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

import java.util.Set;

public class SlabBlockBase extends SlabBlock implements Registerable {

    protected final ModX mod;
    private final Item item;

    public SlabBlockBase(ModX mod, Properties properties) {
        this(mod, properties, new net.minecraft.item.Item.Properties());
    }

    public SlabBlockBase(ModX mod, Properties properties, net.minecraft.item.Item.Properties itemProperties) {
        super(properties);
        this.mod = mod;
        if (mod.tab != null) {
            itemProperties.tab(mod.tab);
        }

        this.item = new BlockItem(this, itemProperties);
    }

    public Set<Object> getAdditionalRegisters() {
        return ImmutableSet.of(this.item);
    }

}
