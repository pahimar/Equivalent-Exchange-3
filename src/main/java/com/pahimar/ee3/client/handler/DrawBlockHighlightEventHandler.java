package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.array.GlyphTextureRegistry;
import com.pahimar.ee3.item.*;
import com.pahimar.ee3.reference.ToolMode;
import com.pahimar.ee3.util.EntityHelper;
import com.pahimar.ee3.util.IModalTool;
import com.pahimar.ee3.util.LogHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class DrawBlockHighlightEventHandler
{
    @SubscribeEvent
    public void onDrawBlockHighlightEvent(DrawBlockHighlightEvent event)
    {
        if (event.currentItem != null)
        {
            if (event.target.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                if (event.currentItem.getItem() instanceof ItemDarkMatterShovel)
                {
                    drawSelectionBoxForShovel(event, (IModalTool) event.currentItem.getItem());
                }
                else if (event.currentItem.getItem() instanceof ItemDarkMatterPickAxe)
                {
                    drawSelectionBoxForPickAxe(event, (IModalTool) event.currentItem.getItem());
                }
                else if (event.currentItem.getItem() instanceof ItemDarkMatterHammer)
                {
                    drawSelectionBoxForHammer(event, (IModalTool) event.currentItem.getItem());
                }
                else if (event.currentItem.getItem() instanceof ItemDarkMatterAxe)
                {
                    drawSelectionBoxForAxe(event, (IModalTool) event.currentItem.getItem());
                }
                else if (event.currentItem.getItem() instanceof ItemDarkMatterHoe)
                {
                    drawSelectionBoxForHoe(event, (IModalTool) event.currentItem.getItem());
                }
                else if (event.currentItem.getItem() instanceof ItemChalk)
                {
                    drawGlyphOverlay(event);
                }
            }
        }
    }

    private void drawSelectionBoxForShovel(DrawBlockHighlightEvent event, IModalTool modalTool)
    {
        ToolMode toolMode = modalTool.getCurrentToolMode(event.currentItem);
        int facing = MathHelper.floor_double(event.player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (toolMode != ToolMode.UNKNOWN)
        {
            event.setCanceled(true);
            if (toolMode == ToolMode.STANDARD)
            {
                drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
            }
        }
    }

    private void drawSelectionBoxForPickAxe(DrawBlockHighlightEvent event, IModalTool modalTool)
    {
        ToolMode toolMode = modalTool.getCurrentToolMode(event.currentItem);
        int facing = MathHelper.floor_double(event.player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (toolMode != ToolMode.UNKNOWN)
        {
            event.setCanceled(true);
            if (toolMode == ToolMode.STANDARD)
            {
                drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
            }
            else if (toolMode == ToolMode.WIDE)
            {
                drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);

                if (event.target.sideHit == ForgeDirection.NORTH.ordinal() || event.target.sideHit == ForgeDirection.SOUTH.ordinal())
                {
                    drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX - 1, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                    drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX + 1, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                }
                else if (event.target.sideHit == ForgeDirection.EAST.ordinal() || event.target.sideHit == ForgeDirection.WEST.ordinal())
                {
                    drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ - 1, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                    drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ + 1, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                }
                else
                {
                    if (facing == 0 || facing == 2)
                    {
                        drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX - 1, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                        drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX + 1, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                    }
                    else
                    {
                        drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ - 1, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                        drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ + 1, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                    }
                }
            }
            else if (toolMode == ToolMode.TALL)
            {
                drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);

                if (event.target.sideHit == ForgeDirection.NORTH.ordinal() || event.target.sideHit == ForgeDirection.SOUTH.ordinal() || event.target.sideHit == ForgeDirection.EAST.ordinal() || event.target.sideHit == ForgeDirection.WEST.ordinal())
                {
                    drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY - 1, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                    drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY + 1, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                }
                else
                {
                    if (facing == 1 || facing == 3)
                    {
                        drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX - 1, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                        drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX + 1, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                    }
                    else
                    {
                        drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ - 1, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                        drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ + 1, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
                    }
                }
            }
        }
    }

    private void drawSelectionBoxForHammer(DrawBlockHighlightEvent event, IModalTool modalTool)
    {
        ToolMode toolMode = modalTool.getCurrentToolMode(event.currentItem);
        int facing = MathHelper.floor_double(event.player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (toolMode != ToolMode.UNKNOWN)
        {
            event.setCanceled(true);
            if (toolMode == ToolMode.STANDARD)
            {
                drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
            }
        }
    }

    private void drawSelectionBoxForAxe(DrawBlockHighlightEvent event, IModalTool modalTool)
    {
        ToolMode toolMode = modalTool.getCurrentToolMode(event.currentItem);
        int facing = MathHelper.floor_double(event.player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (toolMode != ToolMode.UNKNOWN)
        {
            event.setCanceled(true);
            if (toolMode == ToolMode.STANDARD)
            {
                drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
            }
        }
    }

    private void drawSelectionBoxForHoe(DrawBlockHighlightEvent event, IModalTool modalTool)
    {
        ToolMode toolMode = modalTool.getCurrentToolMode(event.currentItem);
        int facing = MathHelper.floor_double(event.player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (toolMode != ToolMode.UNKNOWN)
        {
            event.setCanceled(true);
            if (toolMode == ToolMode.STANDARD)
            {
                drawSelectionBox(event.context, event.player, new MovingObjectPosition(event.target.blockX, event.target.blockY, event.target.blockZ, event.target.sideHit, event.target.hitVec), 0, event.partialTicks);
            }
        }
    }

    private void drawSelectionBox(RenderGlobal context, EntityPlayer entityPlayer, MovingObjectPosition rayTrace, int i, float partialTicks)
    {
        if (i == 0 && rayTrace.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
        {
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glColor4f(1f, 1f, 1f, 0.5f);
            GL11.glLineWidth(3.0F);
            GL11.glDisable(GL11.GL_TEXTURE_2D);
            GL11.glDepthMask(false);
            float f1 = 0.002F;
            Block block = entityPlayer.worldObj.getBlock(rayTrace.blockX, rayTrace.blockY, rayTrace.blockZ);

            if (block.getMaterial() != Material.air)
            {
                block.setBlockBoundsBasedOnState(entityPlayer.worldObj, rayTrace.blockX, rayTrace.blockY, rayTrace.blockZ);
                double d0 = entityPlayer.lastTickPosX + (entityPlayer.posX - entityPlayer.lastTickPosX) * (double) partialTicks;
                double d1 = entityPlayer.lastTickPosY + (entityPlayer.posY - entityPlayer.lastTickPosY) * (double) partialTicks;
                double d2 = entityPlayer.lastTickPosZ + (entityPlayer.posZ - entityPlayer.lastTickPosZ) * (double) partialTicks;
                context.drawOutlinedBoundingBox(block.getSelectedBoundingBoxFromPool(entityPlayer.worldObj, rayTrace.blockX, rayTrace.blockY, rayTrace.blockZ).expand((double) f1, (double) f1, (double) f1).getOffsetBoundingBox(-d0, -d1, -d2), -1);
            }

            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glDisable(GL11.GL_BLEND);
        }
    }

    private void drawGlyphOverlay(DrawBlockHighlightEvent event)
    {
        NBTTagCompound customData = EntityHelper.getCustomEntityData(event.player);

        int index = 0;
        int size = 1;
        int rotation = 0;

        if (customData.hasKey("chalk_settings"))
        {
            NBTTagCompound chalkSettings = customData.getCompoundTag("chalk_settings");

            if (chalkSettings.hasKey("index"))
            {
                index = chalkSettings.getInteger("index");

                if (index >= GlyphTextureRegistry.getInstance().getGlyphs().size())
                {
                    index = 0;
                }
            }

            if (chalkSettings.hasKey("size"))
            {
                size = chalkSettings.getInteger("size");
            }

            if (chalkSettings.hasKey("rotation"))
            {
                rotation = chalkSettings.getInteger("rotation");
            }
        }

        LogHelper.info(String.format("index: %s, size: %s, rotation: %s", index, size, rotation));
    }
}
