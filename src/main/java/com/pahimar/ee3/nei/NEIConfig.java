package com.pahimar.ee3.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.reference.Reference;
import net.minecraft.item.ItemStack;

public class NEIConfig implements IConfigureNEI
{
    @Override
    public String getName()
    {
        return Reference.MOD_NAME;
    }

    @Override
    public String getVersion()
    {
        return Reference.MOD_VERSION;
    }

    @Override
    public void loadConfig()
    {
        AludelRecipeHandler aludelRecipeHandler = new AludelRecipeHandler();

        API.registerRecipeHandler(aludelRecipeHandler);
        API.registerUsageHandler(aludelRecipeHandler);

        API.hideItem(new ItemStack(ModBlocks.transmutationTablet));
        API.hideItem(new ItemStack(ModBlocks.dummyArray));
        API.hideItem(new ItemStack(ModBlocks.alchemyArray));
    }
}
