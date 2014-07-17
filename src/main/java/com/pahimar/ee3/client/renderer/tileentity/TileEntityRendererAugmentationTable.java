package com.pahimar.ee3.client.renderer.tileentity;

import com.pahimar.ee3.client.renderer.model.ModelAugmentationTable;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.tileentity.TileEntityAugmentationTable;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class TileEntityRendererAugmentationTable extends TileEntitySpecialRenderer
{
    private final ModelAugmentationTable modelAugmentationTable = new ModelAugmentationTable();

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick)
    {
        if (tileEntity instanceof TileEntityAugmentationTable)
        {
            TileEntityAugmentationTable tileEntityAugmentationTable = (TileEntityAugmentationTable) tileEntity;
            ForgeDirection direction = tileEntityAugmentationTable.getOrientation();

            GL11.glPushMatrix();
            GL11.glDisable(GL11.GL_LIGHTING);

            // Scale, Translate, Rotate
            scaleTranslateRotate(x, y, z, direction);

            // Bind texture
            this.bindTexture(Textures.Model.AUGMENTATION_TABLE);

            // Render
            modelAugmentationTable.render();

            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }

    private void scaleTranslateRotate(double x, double y, double z, ForgeDirection orientation)
    {
        if (orientation == ForgeDirection.NORTH)
        {
            GL11.glTranslated(x + 0.4d, y + 0.42d, z + 0.5d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(180, 0.0F, 1.0F, 0.0F);
        }
        else if (orientation == ForgeDirection.EAST)
        {
            GL11.glTranslated(x + 0.5d, y + 0.42d, z + 0.4d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(90, 0.0F, 1.0F, 0.0F);
        }
        else if (orientation == ForgeDirection.SOUTH)
        {
            GL11.glTranslated(x + 0.6d, y + 0.42d, z + 0.5d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(0, 0.0F, 1.0F, 0.0F);
        }
        else if (orientation == ForgeDirection.WEST)
        {
            GL11.glTranslated(x + 0.5d, y + 0.42d, z + 0.6d);
            GL11.glScalef(0.25F, 0.27F, 0.25F);
            GL11.glRotatef(-90, 0.0F, 1.0F, 0.0F);
        }
    }
}
