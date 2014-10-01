package com.pahimar.ee3.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;
import com.pahimar.ee3.reference.Reference;

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
        return Reference.VERSION;
    }

    @Override
    public void loadConfig()
    {

        AludelRecipeHandler aludelRecipeHandler = new AludelRecipeHandler();
        CalcinationHandler calcinationHandler = new CalcinationHandler();

        API.registerRecipeHandler(aludelRecipeHandler);
        API.registerUsageHandler(aludelRecipeHandler);

        API.registerRecipeHandler(calcinationHandler);
        API.registerUsageHandler(calcinationHandler);

    }
}
