package com.pahimar.ee3.client.renderer.item;

import com.pahimar.ee3.client.renderer.model.ModelAugmentationTable;
import com.pahimar.ee3.reference.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ItemRendererAugmentationTable implements IItemRenderer
{
    private final ModelAugmentationTable modelAugmentationTable;

    public ItemRendererAugmentationTable()
    {
        modelAugmentationTable = new ModelAugmentationTable();
    }

    @Override
    public boolean handleRenderType(ItemStack itemStack, ItemRenderType itemRenderType)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType itemRenderType, ItemStack itemStack, ItemRendererHelper itemRendererHelper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType itemRenderType, ItemStack itemStack, Object... data)
    {
        switch (itemRenderType)
        {
            case ENTITY:
            {
                renderAugmentationTable(0.0F, 0.0F, 0.0F);
                return;
            }
            case EQUIPPED:
            {
                renderAugmentationTable(1.0F, 1.0F, 1.0F);
                return;
            }
            case EQUIPPED_FIRST_PERSON:
            {
                renderAugmentationTable(1.5F, 1.5F, 1.5F);
                return;
            }
            case INVENTORY:
            {
                renderAugmentationTable(0.0F, 0.0F, 0.0F);
                return;
            }
            default:
            {
            }
        }
    }

    private void renderAugmentationTable(float x, float y, float z)
    {
        GL11.glPushMatrix();

        // Scale, Translate, Rotate
        GL11.glScalef(0.33F, 0.33F, 0.33F);
        GL11.glTranslatef(x, y, z);

        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.Model.AUGMENTATION_TABLE);

        // Render
        modelAugmentationTable.render();

        GL11.glPopMatrix();
    }
}
