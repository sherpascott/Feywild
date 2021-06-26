package com.feywild.feywild.recipes;

import com.feywild.feywild.FeywildMod;
import com.feywild.feywild.block.ModBlocks;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DwarvenAnvilRecipeCategory implements IRecipeCategory<DwarvenAnvilRecipe> {

    public final static ResourceLocation UID = new ResourceLocation(FeywildMod.MOD_ID, "dwarven_anvil");
    public IDrawable background;
    public IDrawable icon;
    IGuiHelper helper;

    public DwarvenAnvilRecipeCategory(IGuiHelper helper) {
        ResourceLocation location = new ResourceLocation(FeywildMod.MOD_ID, "textures/gui/dwarven_anvil_jei.png");
        this.helper = helper;
        //background = helper.createBlankDrawable(85, 85); //Would we be able to get a 85x85 image?
        background = helper.createDrawable(location, 0, 0, 85, 85);

        icon = helper.createDrawableIngredient(new ItemStack(ModBlocks.DWARVEN_ANVIL.get()));

    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends DwarvenAnvilRecipe> getRecipeClass() {
        return DwarvenAnvilRecipe.class;
    }

    @Override
    public String getTitle() {
        return "Dwarven Anvil";
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setIngredients(DwarvenAnvilRecipe recipe, IIngredients iIngredients) {
        List<List<ItemStack>> itemStacks = new ArrayList<>();

        recipe.getInputs().forEach(ingredient -> itemStacks.add(Arrays.asList(ingredient.getItems())));
        iIngredients.setInputLists(VanillaTypes.ITEM, itemStacks);
        iIngredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());

    }

    @Override
    public void setRecipe(IRecipeLayout iRecipeLayout, DwarvenAnvilRecipe recipe, IIngredients iIngredients) {

        iRecipeLayout.getItemStacks().init(0, true, 0, 0);
        iRecipeLayout.getItemStacks().set(0, iIngredients.getInputs(VanillaTypes.ITEM).get(0));

        iRecipeLayout.getItemStacks().init(1, true, 22, 32);  // 0 32
        iRecipeLayout.getItemStacks().set(1, iIngredients.getInputs(VanillaTypes.ITEM).get(1));

        iRecipeLayout.getItemStacks().init(2, true, 32, 54);  //16 64
        iRecipeLayout.getItemStacks().set(2, iIngredients.getInputs(VanillaTypes.ITEM).get(2));

        iRecipeLayout.getItemStacks().init(3, true, 42, 12);
        iRecipeLayout.getItemStacks().set(3, iIngredients.getInputs(VanillaTypes.ITEM).get(3));

        iRecipeLayout.getItemStacks().init(4, true, 54, 54);
        iRecipeLayout.getItemStacks().set(4, iIngredients.getInputs(VanillaTypes.ITEM).get(4));

        iRecipeLayout.getItemStacks().init(5, true, 66, 32);
        iRecipeLayout.getItemStacks().set(5, iIngredients.getInputs(VanillaTypes.ITEM).get(5));

        iRecipeLayout.getItemStacks().init(iIngredients.getInputs(VanillaTypes.ITEM).size(), true, 32, -32);
        iRecipeLayout.getItemStacks().set(iIngredients.getInputs(VanillaTypes.ITEM).size(), iIngredients.getOutputs(VanillaTypes.ITEM).get(0));

    }
}
