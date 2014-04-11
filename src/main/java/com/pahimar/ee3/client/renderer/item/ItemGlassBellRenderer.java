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
public class ItemGlassBellRenderer implements IItemRenderer
{
    private final ModelGlassBell modelGlassBell;

    public ItemGlassBellRenderer()
    {
        modelGlassBell = new ModelGlassBell();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        switch (type)
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
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        // Scale, Translate, Rotate
        GL11.glScalef(1.4F, 1.4F, 1.4F);
        GL11.glTranslatef(x, y, z);
        GL11.glRotatef(-90F, 1F, 0, 0);

        // Bind texture
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.MODEL_GLASS_BELL);

        // Render
        modelGlassBell.render();

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}
