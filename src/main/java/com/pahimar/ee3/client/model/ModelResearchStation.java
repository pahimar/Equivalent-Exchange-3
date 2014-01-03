package com.pahimar.ee3.client.model;

import com.pahimar.ee3.lib.Models;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelResearchStation
{
    private IModelCustom modelResearchStation;

    public ModelResearchStation()
    {
        modelResearchStation = AdvancedModelLoader.loadModel(Models.RESEARCH_STATION);
    }

    public void render()
    {
        modelResearchStation.renderAll();
    }
}
