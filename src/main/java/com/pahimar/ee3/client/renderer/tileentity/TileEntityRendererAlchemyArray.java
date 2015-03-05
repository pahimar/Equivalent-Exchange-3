package com.pahimar.ee3.client.renderer.tileentity;

import com.pahimar.ee3.api.AlchemyArray;
import com.pahimar.ee3.client.util.RenderUtils;
import com.pahimar.ee3.tileentity.TileEntityAlchemyArray;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityRendererAlchemyArray extends TileEntitySpecialRenderer
{
    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        if (tileEntity instanceof TileEntityAlchemyArray)
        {
            TileEntityAlchemyArray tileEntityAlchemyArray = (TileEntityAlchemyArray) FMLClientHandler.instance().getClient().theWorld.getTileEntity(tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);

            int scale = 1;
            double xShift = 0.5d, yShift = 0.5d, zShift = 0.5d;
            float xRotate = 0, yRotate = 0, zRotate = 0;
            int rotationAngle = 0;

            AlchemyArray alchemyArray = tileEntityAlchemyArray.getAlchemyArray();

            if (tileEntityAlchemyArray.getSize() == 1)
            {
                scale = 1;
            }
            else if (tileEntityAlchemyArray.getSize() == 2)
            {
                scale = 3;
            }
            else if (tileEntityAlchemyArray.getSize() == 3)
            {
                scale = 5;
            }

            if (alchemyArray != null)
            {
                GL11.glDisable(GL11.GL_CULL_FACE);
                GL11.glDisable(GL11.GL_LIGHTING);
                GL11.glPushMatrix();

                GL11.glDepthMask(false);
                GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT);

                if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.UP)
                {
                    if (tileEntityAlchemyArray.getRotation() == ForgeDirection.NORTH)
                    {
                        rotationAngle = 0;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.EAST)
                    {
                        rotationAngle = -90;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.SOUTH)
                    {
                        rotationAngle = 180;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.WEST)
                    {
                        rotationAngle = 90;
                    }

                    yShift = 0.001d;
                    xRotate = -1;
                }
                else if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.DOWN)
                {
                    if (tileEntityAlchemyArray.getRotation() == ForgeDirection.NORTH)
                    {
                        rotationAngle = 0;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.EAST)
                    {
                        rotationAngle = -90;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.SOUTH)
                    {
                        rotationAngle = 180;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.WEST)
                    {
                        rotationAngle = 90;
                    }

                    yShift = 0.999d;
                    xRotate = 1;
                }
                else if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.NORTH)
                {
                    if (tileEntityAlchemyArray.getRotation() == ForgeDirection.UP)
                    {
                        rotationAngle = -90;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.EAST)
                    {
                        rotationAngle = -180;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.DOWN)
                    {
                        rotationAngle = 90;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.WEST)
                    {
                        rotationAngle = 0;
                    }

                    zRotate = 1;
                    zShift = 0.999d;
                }
                else if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.SOUTH)
                {
                    if (tileEntityAlchemyArray.getRotation() == ForgeDirection.UP)
                    {
                        rotationAngle = -90;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.EAST)
                    {
                        rotationAngle = 0;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.DOWN)
                    {
                        rotationAngle = 90;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.WEST)
                    {
                        rotationAngle = -180;
                    }

                    zRotate = -1;
                    zShift = 0.001d;
                }
                else if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.EAST)
                {
                    if (tileEntityAlchemyArray.getRotation() == ForgeDirection.UP)
                    {
                        rotationAngle = 180;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.DOWN)
                    {
                        rotationAngle = 0;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.NORTH)
                    {
                        rotationAngle = -90;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.SOUTH)
                    {
                        rotationAngle = 90;
                    }

                    yRotate = 1;
                    xShift = 0.001d;
                }
                else if (tileEntityAlchemyArray.getOrientation() == ForgeDirection.WEST)
                {
                    if (tileEntityAlchemyArray.getRotation() == ForgeDirection.UP)
                    {
                        rotationAngle = 180;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.DOWN)
                    {
                        rotationAngle = 0;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.NORTH)
                    {
                        rotationAngle = 90;
                    }
                    else if (tileEntityAlchemyArray.getRotation() == ForgeDirection.SOUTH)
                    {
                        rotationAngle = -90;
                    }

                    yRotate = -1;
                    xShift = 0.999d;
                }

                GL11.glPushMatrix();
                GL11.glTranslated(x + xShift, y + yShift, z + zShift);
                GL11.glScalef(1f * scale, 1f * scale, 1f * scale);
                GL11.glRotatef(rotationAngle, tileEntityAlchemyArray.getOrientation().offsetX, tileEntityAlchemyArray.getOrientation().offsetY, tileEntityAlchemyArray.getOrientation().offsetZ);
                GL11.glRotatef(90, xRotate, yRotate, zRotate);
                RenderUtils.renderQuad(alchemyArray.getTexture());
                GL11.glPopMatrix();

                GL11.glDepthMask(true);

                GL11.glPopMatrix();
                GL11.glEnable(GL11.GL_LIGHTING);
                GL11.glEnable(GL11.GL_CULL_FACE);
            }
        }
    }
}
