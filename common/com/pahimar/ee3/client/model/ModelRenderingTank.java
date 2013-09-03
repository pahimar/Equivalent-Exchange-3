package com.pahimar.ee3.client.model;

import com.pahimar.ee3.lib.Models;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class ModelRenderingTank {

	private IModelCustom modelRenderingTank;
	
	public ModelRenderingTank() {

		modelRenderingTank = AdvancedModelLoader.loadModel(Models.RENDERING_TANK);
    }

    public void render() {

    	modelRenderingTank.renderAll();
    }
    
    public void renderPart(String partName) {

    	modelRenderingTank.renderPart(partName);
    }
}
