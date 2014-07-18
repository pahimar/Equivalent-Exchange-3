package com.pahimar.ee3.client.renderer.item;

import com.pahimar.ee3.client.renderer.model.ModelAludel;
import com.pahimar.ee3.reference.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ItemRendererAludel implements IItemRenderer
{
    private final ModelAludel modelAludel;

    public ItemRendererAludel()
    {
        modelAludel = new ModelAludel();
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
                renderAludel(-0.5F, -0.38F, 0.5F);
                return;
            }
            case EQUIPPED:
            {
                renderAludel(0.0F, 0.0F, 1.0F);
                return;
            }
            case EQUIPPED_FIRST_PERSON:
            {
                renderAludel(0.0F, 0.0F, 1.0F);
                return;
            }
            case INVENTORY:
            {
                renderAludel(-1.0F, -0.9F, 0.0F);
                return;
            }
            default:
        }
    }

    private void renderAludel(float x, float y, float z)
    {
        GL11.glPushMatrix();

        // Scale, Translate, Rotate
        GL11.glScalef(1F, 1F, 1F);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(-90F, 1F, 0, 0);

        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.Model.ALUDEL);

        // Render
        modelAludel.render();

        GL11.glPopMatrix();
    }
}
