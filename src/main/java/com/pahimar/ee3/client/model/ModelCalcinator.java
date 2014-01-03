package com.pahimar.ee3.client.model;

import com.pahimar.ee3.lib.Models;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

/**
 * ModelCalcinator
 * <p/>
 * Model for the Calcinator
 *
 * @author pahimar
 */
@SideOnly(Side.CLIENT)
public class ModelCalcinator
{
    private IModelCustom modelCalcinator;

    public ModelCalcinator()
    {
        modelCalcinator = AdvancedModelLoader.loadModel(Models.CALCINATOR);
    }

    public void render()
    {
        modelCalcinator.renderAll();
    }

    public void renderPart(String partName)
    {
        modelCalcinator.renderPart(partName);
    }
}
