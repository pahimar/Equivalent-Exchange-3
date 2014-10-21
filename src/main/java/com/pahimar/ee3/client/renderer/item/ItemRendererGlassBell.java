package com.pahimar.ee3.client.renderer.item;

import com.pahimar.ee3.client.renderer.model.ModelGlassBell;
import com.pahimar.ee3.reference.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ItemRendererGlassBell implements IItemRenderer
{
    private final ModelGlassBell modelGlassBell;

    public ItemRendererGlassBell()
    {
        modelGlassBell = new ModelGlassBell();
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
                renderGlassBell(-0.5F, -1.2F, 0.5F);
                return;
            }
            case EQUIPPED:
            {
                renderGlassBell(-0.2F, -0.85F, 0.8F);
                return;
            }
            case EQUIPPED_FIRST_PERSON:
            {
                renderGlassBell(-0.2F, -0.85F, 0.8F);
                return;
            }
            case INVENTORY:
            {
                renderGlassBell(-1.0F, -1.675F, 0.0F);
                return;
            }
            default:
            {
            }
        }
    }

    private void renderGlassBell(float x, float y, float z)
    {
        GL11.glEnable(GL11.GL_ALPHA_TEST);

        GL11.glPushMatrix();

        // Scale, Translate, Rotate
        GL11.glScalef(1.4F, 1.4F, 1.4F);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(-90F, 1F, 0, 0);

        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.Model.GLASS_BELL);

        // Render
        modelGlassBell.render();

        GL11.glPopMatrix();

    }
}
