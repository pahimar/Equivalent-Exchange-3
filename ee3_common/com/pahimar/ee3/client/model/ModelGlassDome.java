package com.pahimar.ee3.client.model;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import com.pahimar.ee3.lib.Models;


public class ModelGlassDome {

    private IModelCustom modelGlassDome;

    public ModelGlassDome() {

        modelGlassDome = AdvancedModelLoader.loadModel(Models.GLASS_DOME);
    }

    public void render() {

        modelGlassDome.renderPart("Dome");
    }
}
