package com.pahimar.ee3.client.model;

import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import com.pahimar.ee3.lib.Models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 * 
 * ModelAludel
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
@SideOnly(Side.CLIENT)
public class ModelAludel {

    private IModelCustom modelAludel;

    public ModelAludel() {

        modelAludel = AdvancedModelLoader.loadModel(Models.ALUDEL);
    }

    public void render() {

        modelAludel.renderPart("Base");
    }
}
