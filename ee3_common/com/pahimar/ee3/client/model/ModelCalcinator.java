package com.pahimar.ee3.client.model;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import com.pahimar.ee3.lib.Models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * ModelCalcinator
 * 
 * Model for the Calcinator
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class ModelCalcinator {

    private IModelCustom modelCalcinator;

    public ModelCalcinator() {

        modelCalcinator = AdvancedModelLoader.loadModel(Models.CALCINATOR);
    }

    public void render() {

        modelCalcinator.renderAll();
    }

    public void renderPart(String partName) {

        modelCalcinator.renderPart(partName);
    }
}
