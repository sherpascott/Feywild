package com.feywild.feywild.block.entity;

import com.feywild.feywild.network.FeywildPacketHandler;
import com.feywild.feywild.network.ItemMessage;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class InventoryTile extends TileEntity implements IInventory {

    public InventoryTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public int getContainerSize() {
        return 5;
    }

    public List<ItemStack> getItems() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        for(int i = 0; i < getContainerSize(); i++){
            if(!getItem(i).isEmpty()){
                return true;
            }
        }
        return false;
    }
    //get stack
    @Override
    public ItemStack getItem(int index) {
        return getItems().get(index);
    }

    //decrement
    @Override
    public ItemStack removeItem(int index, int count) {
        ItemStack stack = getItem(index);
        stack.shrink(count);
        return stack;
    }

    /* Update the inventory
       flags = -1  means update the entire inventory
       otherwise update one item slot
     */
    public void updateInventory(int flags, boolean shouldCraft) {
        if(flags == -1){
           for (int i = 0; i < getContainerSize(); i++) {
               FeywildPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new ItemMessage(getItems().get(i), worldPosition, i));
           }
        }else{
            FeywildPacketHandler.INSTANCE.send(PacketDistributor.ALL.noArg(), new ItemMessage(getItems().get(flags),worldPosition,flags));
        }
    }

    // Clear slot
    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return getItems().set(index, ItemStack.EMPTY);
    }

    //Set stack at index
    @Override
    public void setItem(int index, ItemStack stack) {
        getItems().set(index,stack);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return player.distanceToSqr(getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ()) <= 16;
    }

    //Clear all items
    @Override
    public void clearContent() {
        for (int i = 0; i < getContainerSize(); i++) {
            removeItemNoUpdate(i);
        }
        updateInventory(-1,false);
    }
}
