package com.pahimar.ee3.client.model;

import com.pahimar.ee3.lib.Models;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ModelAludel
 *
 * @author pahimar
 */
@SideOnly(Side.CLIENT)
public class ModelAludel
{
    private IModelCustom modelAludel;

    public ModelAludel()
    {
        modelAludel = AdvancedModelLoader.loadModel(Models.ALUDEL);
    }

    public void render()
    {
        modelAludel.renderPart("Base");
    }
}
