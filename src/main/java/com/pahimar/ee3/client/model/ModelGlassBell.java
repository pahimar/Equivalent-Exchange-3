package com.pahimar.ee3.client.model;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import com.pahimar.ee3.lib.Models;

public class ModelGlassBell {

    private IModelCustom modelGlassBell;

    public ModelGlassBell() {

        modelGlassBell = AdvancedModelLoader.loadModel(Models.GLASS_BELL);
    }

    public void render() {

        modelGlassBell.renderPart("Bell");
    }
}
