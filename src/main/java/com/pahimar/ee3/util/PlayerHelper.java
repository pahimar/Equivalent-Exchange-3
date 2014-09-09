package com.pahimar.ee3.util;

import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.util.FakePlayer;

public class PlayerHelper
{
    public static boolean isPlayerOp(EntityPlayer entityPlayer)
    {
        if (FMLCommonHandler.instance().getMinecraftServerInstance() != null)
        {
            return FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().func_152596_g((entityPlayer).getGameProfile());
        }

        return false;
    }

    public static boolean isFakePlayer(EntityPlayer entityPlayer)
    {
        if (entityPlayer instanceof FakePlayer)
        {
            return true;
        }
        else if (entityPlayer.getGameProfile() == null || entityPlayer.getGameProfile().getId() == null)
        {
            return true;
        }
        else if (entityPlayer instanceof EntityPlayerMP)
        {
            EntityPlayerMP entityPlayerMP = (EntityPlayerMP) entityPlayer;
            if (entityPlayerMP.playerNetServerHandler == null)
            {
                return true;
            }

            try
            {
                entityPlayerMP.getPlayerIP();
            }
            catch (Exception e)
            {
                return true;
            }

            return !FMLCommonHandler.instance().getMinecraftServerInstance().getConfigurationManager().playerEntityList.contains(entityPlayer);
        }

        return false;
    }
}
