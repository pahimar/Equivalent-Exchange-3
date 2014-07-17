package com.pahimar.ee3.client.renderer.item;

import com.pahimar.ee3.reference.Textures;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelChest;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class ItemRendererAlchemicalChest implements IItemRenderer
{
    private final ModelChest modelChest;

    public ItemRendererAlchemicalChest()
    {
        modelChest = new ModelChest();
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
                renderAlchemicalChest(0.5F, 0.5F, 0.5F, itemStack.getItemDamage());
                break;
            }
            case EQUIPPED:
            {
                renderAlchemicalChest(1.0F, 1.0F, 1.0F, itemStack.getItemDamage());
                break;
            }
            case EQUIPPED_FIRST_PERSON:
            {
                renderAlchemicalChest(1.0F, 1.0F, 1.0F, itemStack.getItemDamage());
                break;
            }
            case INVENTORY:
            {
                renderAlchemicalChest(0.0F, 0.075F, 0.0F, itemStack.getItemDamage());
                break;
            }
            default:
                break;
        }
    }

    private void renderAlchemicalChest(float x, float y, float z, int metaData)
    {
        if (metaData == 0)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.Model.ALCHEMICAL_CHEST_SMALL);
        }
        else if (metaData == 1)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.Model.ALCHEMICAL_CHEST_MEDIUM);
        }
        else if (metaData == 2)
        {
            FMLClientHandler.instance().getClient().renderEngine.bindTexture(Textures.Model.ALCHEMICAL_CHEST_LARGE);
        }

        GL11.glPushMatrix(); //start
        GL11.glTranslatef(x, y, z); //size
        GL11.glRotatef(180, 1, 0, 0);
        GL11.glRotatef(-90, 0, 1, 0);
        modelChest.renderAll();
        GL11.glPopMatrix(); //end
    }
}
