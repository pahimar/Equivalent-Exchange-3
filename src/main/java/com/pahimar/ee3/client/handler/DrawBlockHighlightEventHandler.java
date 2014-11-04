package com.pahimar.ee3.client.handler;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.array.GlyphTextureRegistry;
import com.pahimar.ee3.client.util.RenderUtils;
import com.pahimar.ee3.item.*;
import com.pahimar.ee3.reference.ToolMode;
import com.pahimar.ee3.settings.ChalkSettings;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import com.pahimar.ee3.tileentity.TileEntityDummyArray;
import com.pahimar.ee3.tileentity.TileEntityEE;
import com.pahimar.ee3.util.IModalTool;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
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
        drawGlyphInWorld(event);
    }

    private static void drawGlyphInWorld(DrawBlockHighlightEvent event)
    {
        ChalkSettings chalkSettings = EquivalentExchange3.proxy.getClientProxy().chalkSettings;
        ResourceLocation texture = GlyphTextureRegistry.getInstance().getRegisteredGlyphAt(chalkSettings.getIndex());
        int rotation = chalkSettings.getRotation();

        double x = event.target.blockX + 0.5F;
        double y = event.target.blockY + 0.5F;
        double z = event.target.blockZ + 0.5F;
        double iPX = event.player.prevPosX + (event.player.posX - event.player.prevPosX) * event.partialTicks;
        double iPY = event.player.prevPosY + (event.player.posY - event.player.prevPosY) * event.partialTicks;
        double iPZ = event.player.prevPosZ + (event.player.posZ - event.player.prevPosZ) * event.partialTicks;

        float xScale, yScale, zScale;
        float xShift, yShift, zShift;
        float xRotate, yRotate, zRotate;
        int zCorrection = 1;
        int rotationAngle = 0;
        int playerFacing = MathHelper.floor_double(event.player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int facingCorrectionAngle = 0;

        xScale = yScale = zScale = 1;
        xShift = yShift = zShift = 0;
        xRotate = yRotate = zRotate = 0;

        int chargeLevel = chalkSettings.getSize();
        ForgeDirection sideHit = ForgeDirection.getOrientation(event.target.sideHit);
        TileEntity tileEntity = event.player.worldObj.getTileEntity(event.target.blockX, event.target.blockY, event.target.blockZ);
        boolean shouldRender = true;

        if (tileEntity instanceof TileEntityEE)
        {
            if (((TileEntityEE) tileEntity).getOrientation() != sideHit)
            {
                shouldRender = false;
            }
        }

        switch (sideHit)
        {
            case UP:
            {
                xScale = zScale = chargeLevel;
                yShift = 0.001f;
                xRotate = -1;
                rotationAngle = (-90 * (rotation + 2)) % 360;
                facingCorrectionAngle = (-90 * (playerFacing + 2)) % 360;
                if (tileEntity instanceof TileEntityAlchemyArray)
                {
                    y -= 1;
                }

                if (tileEntity instanceof TileEntityDummyArray)
                {
                    x = ((TileEntityDummyArray) tileEntity).getTrueXCoord() + 0.5f;
                    y = ((TileEntityDummyArray) tileEntity).getTrueYCoord() + 0.5f - 1;
                    z = ((TileEntityDummyArray) tileEntity).getTrueXCoord() + 0.5f;
                }
                break;
            }
            case DOWN:
            {
                xScale = zScale = chargeLevel;
                yShift = -0.001f;
                xRotate = 1;
                rotationAngle = (-90 * (rotation + 2)) % 360;
                facingCorrectionAngle = (-90 * (playerFacing + 2)) % 360;
                if (tileEntity instanceof TileEntityAlchemyArray)
                {
                    y += 1;
                }
                break;
            }
            case NORTH:
            {
                xScale = yScale = chargeLevel;
                zCorrection = -1;
                zShift = -0.001f;
                zRotate = 1;
                rotationAngle = (-90 * (rotation + 1)) % 360;
                if (tileEntity instanceof TileEntityAlchemyArray)
                {
                    z += 1;
                }
                break;
            }
            case SOUTH:
            {
                xScale = yScale = chargeLevel;
                zShift = 0.001f;
                zRotate = -1;
                rotationAngle = (-90 * (rotation + 1)) % 360;
                if (tileEntity instanceof TileEntityAlchemyArray)
                {
                    z -= 1;
                }
                break;
            }
            case EAST:
            {
                yScale = zScale = chargeLevel;
                xShift = 0.001f;
                yRotate = 1;
                rotationAngle = (-90 * (rotation + 2)) % 360;
                if (tileEntity instanceof TileEntityAlchemyArray)
                {
                    x -= 1;
                }
                break;
            }
            case WEST:
            {
                yScale = zScale = chargeLevel;
                xShift = -0.001f;
                yRotate = -1;
                rotationAngle = (-90 * (rotation + 2)) % 360;
                if (tileEntity instanceof TileEntityAlchemyArray)
                {
                    x += 1;
                }
                break;
            }
            default:
                break;
        }

        if (shouldRender)
        {
            GL11.glDepthMask(false);
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glPushMatrix();
            GL11.glTranslated(-iPX + x + xShift, -iPY + y + yShift, -iPZ + z + zShift);
            GL11.glScalef(1F * xScale, 1F * yScale, 1F * zScale);
            GL11.glRotatef(rotationAngle, sideHit.offsetX, sideHit.offsetY, sideHit.offsetZ);
            GL11.glRotatef(facingCorrectionAngle, sideHit.offsetX, sideHit.offsetY, sideHit.offsetZ);
            GL11.glRotatef(90, xRotate, yRotate, zRotate);
            GL11.glTranslated(0, 0, 0.5f * zCorrection);
            GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
            RenderUtils.renderPulsingQuad(texture, 1f);
            GL11.glPopMatrix();
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDepthMask(true);
        }
    }

    private static void drawGlyphInWorld(DrawBlockHighlightEvent event, ResourceLocation texture, int size, int rotation)
    {
        double x = event.target.blockX + 0.5F;
        double y = event.target.blockY + 0.5F;
        double z = event.target.blockZ + 0.5F;
        double iPX = event.player.prevPosX + (event.player.posX - event.player.prevPosX) * event.partialTicks;
        double iPY = event.player.prevPosY + (event.player.posY - event.player.prevPosY) * event.partialTicks;
        double iPZ = event.player.prevPosZ + (event.player.posZ - event.player.prevPosZ) * event.partialTicks;

        float xScale, yScale, zScale;
        float xShift, yShift, zShift;
        float xRotate, yRotate, zRotate;
        int zCorrection = 1;
        int rotationAngle = 0;
        int playerFacing = MathHelper.floor_double(event.player.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
        int facingCorrectionAngle = 0;

        xScale = yScale = zScale = 1;
        xShift = yShift = zShift = 0;
        xRotate = yRotate = zRotate = 0;

        int chargeLevel = size;
        ForgeDirection sideHit = ForgeDirection.getOrientation(event.target.sideHit);
        TileEntity tileEntity = event.player.worldObj.getTileEntity(event.target.blockX, event.target.blockY, event.target.blockZ);

        switch (sideHit)
        {
            case UP:
            {
                xScale = zScale = chargeLevel;
                yShift = 0.001f;
                xRotate = -1;
                rotationAngle = (-90 * (rotation + 2)) % 360;
                facingCorrectionAngle = (-90 * (playerFacing + 2)) % 360;
                if (tileEntity instanceof TileEntityAlchemyArray)
                {
                    y -= 1;
                }
                break;
            }
            case DOWN:
            {
                xScale = zScale = chargeLevel;
                yShift = -0.001f;
                xRotate = 1;
                rotationAngle = (-90 * (rotation + 2)) % 360;
                facingCorrectionAngle = (-90 * (playerFacing + 2)) % 360;
                if (tileEntity instanceof TileEntityAlchemyArray)
                {
                    y += 1;
                }
                break;
            }
            case NORTH:
            {
                xScale = yScale = chargeLevel;
                zCorrection = -1;
                zShift = -0.001f;
                zRotate = 1;
                rotationAngle = (-90 * (rotation + 1)) % 360;
                if (tileEntity instanceof TileEntityAlchemyArray)
                {
                    z += 1;
                }
                break;
            }
            case SOUTH:
            {
                xScale = yScale = chargeLevel;
                zShift = 0.001f;
                zRotate = -1;
                rotationAngle = (-90 * (rotation + 1)) % 360;
                if (tileEntity instanceof TileEntityAlchemyArray)
                {
                    z -= 1;
                }
                break;
            }
            case EAST:
            {
                yScale = zScale = chargeLevel;
                xShift = 0.001f;
                yRotate = 1;
                rotationAngle = (-90 * (rotation + 2)) % 360;
                if (tileEntity instanceof TileEntityAlchemyArray)
                {
                    x -= 1;
                }
                break;
            }
            case WEST:
            {
                yScale = zScale = chargeLevel;
                xShift = -0.001f;
                yRotate = -1;
                rotationAngle = (-90 * (rotation + 2)) % 360;
                if (tileEntity instanceof TileEntityAlchemyArray)
                {
                    x += 1;
                }
                break;
            }
            default:
                break;
        }

        GL11.glDepthMask(false);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glPushMatrix();
        GL11.glTranslated(-iPX + x + xShift, -iPY + y + yShift, -iPZ + z + zShift);
        GL11.glScalef(1F * xScale, 1F * yScale, 1F * zScale);
        GL11.glRotatef(rotationAngle, sideHit.offsetX, sideHit.offsetY, sideHit.offsetZ);
        GL11.glRotatef(facingCorrectionAngle, sideHit.offsetX, sideHit.offsetY, sideHit.offsetZ);
        GL11.glRotatef(90, xRotate, yRotate, zRotate);
        GL11.glTranslated(0, 0, 0.5f * zCorrection);
        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);
        RenderUtils.renderPulsingQuad(texture, 1f);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(true);
    }
}
