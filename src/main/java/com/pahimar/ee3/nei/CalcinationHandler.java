package com.pahimar.ee3.nei;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.FurnaceRecipeHandler;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.pahimar.ee3.api.exchange.EnergyValue;
import com.pahimar.ee3.api.exchange.EnergyValueRegistryProxy;
import com.pahimar.ee3.client.gui.inventory.GuiCalcinator;
import com.pahimar.ee3.item.ItemAlchemicalDust;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.util.CalcinationHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static codechicken.lib.gui.GuiDraw.*;

public class CalcinationHandler extends TemplateRecipeHandler
{
    private static final DecimalFormat energyValueDecimalFormat = new DecimalFormat("###,###,###,###,###.###");

    public class CachedCalcinationRecipe extends CachedRecipe
    {
        public List<PositionedStack> inputs;
        public PositionedStack output;

        public EnergyValue minEnergyValue;
        public EnergyValue maxEnergyValue;

        public CachedCalcinationRecipe(ItemStack outputDust)
        {
            output = new PositionedStack(outputDust, 101, 19);

            inputs = new ArrayList<PositionedStack>();

            minEnergyValue = EnergyValueRegistryProxy.getEnergyValue(outputDust);
            maxEnergyValue = (outputDust.getItemDamage() < (ItemAlchemicalDust.getAlchemicalDusts().size() - 1) ? EnergyValueRegistryProxy.getEnergyValue(ItemAlchemicalDust.getAlchemicalDusts().get(outputDust.getItemDamage() + 1)) : new EnergyValue(Float.MAX_VALUE));

            for (Object obj : EnergyValueRegistryProxy.getStacksInRange(minEnergyValue, maxEnergyValue))
            {
                if (obj instanceof ItemStack)
                {
                    inputs.add(new PositionedStack((ItemStack) obj, 40, 0));
                }
            }
        }

        public CachedCalcinationRecipe(ItemStack inputStack, ItemStack outputDust)
        {
            inputStack.stackSize = 1;
            inputs = Arrays.asList(new PositionedStack[]{new PositionedStack(inputStack, 40, 0)});

            output = new PositionedStack(outputDust, 101, 19);

            minEnergyValue = EnergyValueRegistryProxy.getEnergyValue(outputDust);
            maxEnergyValue = (outputDust.getItemDamage() < (ItemAlchemicalDust.getAlchemicalDusts().size() - 1) ? EnergyValueRegistryProxy.getEnergyValue(ItemAlchemicalDust.getAlchemicalDusts().get(outputDust.getItemDamage() + 1)) : new EnergyValue(Float.MAX_VALUE));
        }

        @Override
        public PositionedStack getIngredient()
        {
            return inputs.get((cycleticks / 48) % inputs.size());
        }

        public PositionedStack getOtherStack()
        {
            return new PositionedStack(FurnaceRecipeHandler.afuels.get((cycleticks / 48) % FurnaceRecipeHandler.afuels.size()).stack.item, 40, 45);
        }

        @Override
        public PositionedStack getResult()
        {
            return output;
        }
    }

    @Override
    public Class<? extends GuiContainer> getGuiClass()
    {
        return GuiCalcinator.class;
    }

    @Override
    public String getGuiTexture()
    {
        return Textures.Gui.CALCINATOR.toString();
    }

    @Override
    public String getRecipeName()
    {
        return StatCollector.translateToLocal("gui.nei.ee3:calcination");
    }

    public String getRecipeID()
    {
        return Reference.MOD_ID + ":" + Names.Blocks.CALCINATOR;
    }

    @Override
    public void loadCraftingRecipes(ItemStack result)
    {
        for (ItemStack stack : ItemAlchemicalDust.getAlchemicalDusts())
        {
            if (NEIServerUtils.areStacksSameTypeCrafting(stack, result))
            {
                arecipes.add(new CachedCalcinationRecipe(stack));
            }
        }
    }

    @Override
    public void loadCraftingRecipes(String outputId, Object... results)
    {
        if (outputId.equals(getRecipeID()))
        {
            for (ItemStack stack : ItemAlchemicalDust.getAlchemicalDusts())
            {
                arecipes.add(new CachedCalcinationRecipe(stack));
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
        transferRects.add(new RecipeTransferRect(new Rectangle(39, 20, 18, 18), "fuel"));
        transferRects.add(new RecipeTransferRect(new Rectangle(69, 19, 24, 16), getRecipeID()));
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient)
    {
        for (ItemStack stack : ItemAlchemicalDust.getAlchemicalDusts())
        {
            if (NEIServerUtils.areStacksSameTypeCrafting(stack, CalcinationHelper.getCalcinationResult(ingredient)))
            {
                arecipes.add(new CachedCalcinationRecipe(ingredient, stack));
            }
        }

    }

    @Override
    public int recipiesPerPage()
    {
        return 1;
    }

    @Override
    public void drawBackground(int recipe)
    {
        GL11.glColor4f(1, 1, 1, 1);
        changeTexture(getGuiTexture());
        drawTexturedModalRect(14, -3, 19, 7, 143, 68);
    }

    @Override
    public void drawExtras(int recipe)
    {
        drawProgressBar(41, 23, 176, 0, 14, 14, 48, 7);
        drawProgressBar(70, 20, 176, 14, 24, 16, 48, 0);
        if(arecipes.size() <= recipe)
            return;

        CachedCalcinationRecipe cRecipe = (CachedCalcinationRecipe) arecipes.get(recipe);
        drawStringC(StatCollector.translateToLocal("gui.nei.ee3:calcination.tooltip.1"), 83, 75, 0x404040, false);
        drawStringC(cRecipe.getResult().item.getDisplayName() + ":", 83, 85, 0x404040, false);

        if (cRecipe.minEnergyValue != null && cRecipe.maxEnergyValue != null && EnergyValueRegistryProxy.getEnergyValue(ItemAlchemicalDust.getAlchemicalDusts().get(ItemAlchemicalDust.getAlchemicalDusts().size() - 1)) != null)
        {
            drawStringC(StatCollector.translateToLocalFormatted("gui.nei.ee3:calcination.tooltip.2",
                    (cRecipe.minEnergyValue.getValue() > 1
                            ? energyValueDecimalFormat.format(cRecipe.minEnergyValue.getValue())
                            : "0"),
                    (cRecipe.maxEnergyValue.getValue() <= EnergyValueRegistryProxy
                            .getEnergyValue(ItemAlchemicalDust.getAlchemicalDusts()
                                    .get(ItemAlchemicalDust.getAlchemicalDusts().size() - 1)).getValue()
                            ? energyValueDecimalFormat.format(cRecipe.maxEnergyValue.getValue())
                            : "\u221E")),
                    83, 95, 0x404040, false);
        }
    }

}
