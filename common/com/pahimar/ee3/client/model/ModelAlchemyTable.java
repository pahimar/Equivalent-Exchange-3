package com.pahimar.ee3.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import com.pahimar.ee3.lib.Models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelAlchemyTable extends ModelBase {

    private IModelCustom modelAlchemyTable;

    public ModelAlchemyTable() {

        modelAlchemyTable = AdvancedModelLoader.loadModel(Models.ALCHEMY_TABLE);
    }

    public void render() {

        modelAlchemyTable.renderAll();
    }

    public void renderPart(String partName) {

        modelAlchemyTable.renderPart(partName);
    }
}
