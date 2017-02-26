package com.pahimar.ee3.handler;

import com.pahimar.ee3.client.gui.GuiAlchenomicon;
import com.pahimar.ee3.reference.GuiIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {

        BlockPos blockPos = new BlockPos(x, y, z);

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer entityPlayer, World world, int x, int y, int z) {

        BlockPos blockPos = new BlockPos(x, y, z);

        if (id == GuiIds.ALCHENOMICON) {
            return new GuiAlchenomicon();
        }

        return null;
    }
}
