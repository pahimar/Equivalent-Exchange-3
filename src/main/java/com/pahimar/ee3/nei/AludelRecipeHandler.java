package com.pahimar.ee3.nei;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.FurnaceRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.pahimar.ee3.client.gui.inventory.GuiAludel;
import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.item.crafting.RecipeAludel;
import com.pahimar.ee3.recipe.RecipesAludel;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.reference.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static codechicken.lib.gui.GuiDraw.changeTexture;
import static codechicken.lib.gui.GuiDraw.drawTexturedModalRect;

public class AludelRecipeHandler extends TemplateRecipeHandler
{
    public class CachedAludelRecipe extends CachedRecipe
    {
        public List<PositionedStack> inputs;

        public PositionedStack output;

        public CachedAludelRecipe(RecipeAludel recipe)
        {
            WrappedStack[] wrappedInputs = recipe.getRecipeInputs();
            inputs = Arrays.asList(new PositionedStack(new ItemStack(((ItemStack) wrappedInputs[0].getWrappedStack()).getItem(), wrappedInputs[0].getStackSize(), ((ItemStack) wrappedInputs[0].getWrappedStack()).getItemDamage()), 37, 7), new PositionedStack(new ItemStack(((ItemStack) wrappedInputs[1].getWrappedStack()).getItem(), wrappedInputs[1].getStackSize(), ((ItemStack) wrappedInputs[1].getWrappedStack()).getItemDamage()), 37, 28));

            output = new PositionedStack(recipe.getRecipeOutput(), 113, 28);

        }

        @Override
        public List<PositionedStack> getIngredients()
        {
            return inputs;
        }

        public PositionedStack getOtherStack()
        {
            return new PositionedStack(FurnaceRecipeHandler.afuels.get((cycleticks / 48) % FurnaceRecipeHandler.afuels.size()).stack.item, 37, 63);
        }

        @Override
        public PositionedStack getResult()
        {
            return output;
        }
    }

    @Override
    public void drawBackground(int recipe)
    {
        GL11.glColor4f(1, 1, 1, 1);
        changeTexture(getGuiTexture());
        drawTexturedModalRect(1, -1, 8, 10, 164, 91);
    }

    @Override
    public void drawExtras(int recipe)
    {
        drawProgressBar(37, 46, 176, 0, 14, 14, 48, 7);
        drawProgressBar(73, 29, 176, 14, 24, 16, 48, 0);
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiAludel.class;
    }

    @Override
    public String getGuiTexture()
    {
        return Textures.Gui.ALUDEL.toString();
    }

    public String getRecipeID()
    {
        return Reference.MOD_ID + ":" + Names.Blocks.ALUDEL;
    }

    @Override
    public String getRecipeName()
    {
        return StatCollector.translateToLocal("gui.nei.ee3:aludel");
    }

    @Override
    public void loadCraftingRecipes(ItemStack result)
    {
        for (RecipeAludel recipe : RecipesAludel.getInstance().getRecipes())
        {
            if (NEIServerUtils.areStacksSameTypeCrafting(recipe.getRecipeOutput(), result))
            {
                arecipes.add(new CachedAludelRecipe(recipe));
            }
        }
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (outputId.equals(getRecipeID()))
        {
            for (RecipeAludel recipe : RecipesAludel.getInstance().getRecipes())
            {
                arecipes.add(new CachedAludelRecipe(recipe));
            }

        }
        else
        {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadTransferRects()
    {
        transferRects.add(new RecipeTransferRect(new Rectangle(36, 44, 18, 18), "fuel"));
        transferRects.add(new RecipeTransferRect(new Rectangle(74, 28, 24, 16), getRecipeID()));
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        for (RecipeAludel recipe : RecipesAludel.getInstance().getRecipes())
        {
            for (WrappedStack wrappedStack : recipe.getRecipeInputs())
            {
                if (NEIServerUtils.areStacksSameTypeCrafting((ItemStack) wrappedStack.getWrappedStack(), ingredient))
                {
                    arecipes.add(new CachedAludelRecipe(recipe));
                }
            }
        }
    }

    @Override
    public int recipiesPerPage()
    {
        return 1;
    }
}
