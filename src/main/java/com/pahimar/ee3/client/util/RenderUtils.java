package com.pahimar.ee3.client.util;

import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.DrawBlockHighlightEvent;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class RenderUtils
{
    public static void renderItemIntoGUI(FontRenderer fontRenderer, ItemStack itemStack, int x, int y, float opacity, float scale, int zLevel)
    {
        IIcon icon = itemStack.getIconIndex();
        GL11.glDisable(GL11.GL_LIGHTING);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(TextureMap.locationItemsTexture);
        int overlayColour = itemStack.getItem().getColorFromItemStack(itemStack, 0);
        float red = (overlayColour >> 16 & 255) / 255.0F;
        float green = (overlayColour >> 8 & 255) / 255.0F;
        float blue = (overlayColour & 255) / 255.0F;
        GL11.glColor4f(red, green, blue, opacity);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + 16 * scale, zLevel, icon.getMinU(), icon.getMaxV());
        tessellator.addVertexWithUV(x + 16 * scale, y + 16 * scale, zLevel, icon.getMaxU(), icon.getMaxV());
        tessellator.addVertexWithUV(x + 16 * scale, y, zLevel, icon.getMaxU(), icon.getMinV());
        tessellator.addVertexWithUV(x, y, zLevel, icon.getMinU(), icon.getMinV());
        tessellator.draw();
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    public static void drawInWorldTransmutationOverlay(DrawBlockHighlightEvent event, ResourceLocation texture, int size, int rotation)
    {
        // TODO: Only render glyphs if they can be placed

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
        renderQuad(texture);
        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glDepthMask(true);
    }

    public static void renderQuad(ResourceLocation texture)
    {
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1, 1, 1, 1);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(-0.5D, 0.5D, 0F, 0, 1);
        tessellator.addVertexWithUV(0.5D, 0.5D, 0F, 1, 1);
        tessellator.addVertexWithUV(0.5D, -0.5D, 0F, 1, 0);
        tessellator.addVertexWithUV(-0.5D, -0.5D, 0F, 0, 0);
        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
    }
}
