package com.pahimar.ee3.init;

import com.pahimar.ee3.item.ItemAlchemicalFuel;
import com.pahimar.ee3.item.ItemAlchenomicon;
import com.pahimar.ee3.item.ItemChalk;
import com.pahimar.ee3.item.base.ItemEE;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;

public class ModItems {

    public static final List<ItemEE> ITEMS = new ArrayList<>();

    public static final ItemEE CHALK = new ItemChalk();
    public static final ItemEE ALCHEMICAL_FUEL = new ItemAlchemicalFuel();
    public static final ItemEE ALCHENOMICON = new ItemAlchenomicon();

    public static void register() {
        GameRegistry.register(CHALK);
        GameRegistry.register(ALCHEMICAL_FUEL);
        GameRegistry.register(ALCHENOMICON);
    }

    @SideOnly(Side.CLIENT)
    public static void initModelsAndVariants() {
        ITEMS.forEach(ItemEE::initModelsAndVariants);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemColors() {

        for (ItemEE itemEE : ITEMS) {
            if (itemEE instanceof IItemColor) {
                FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new IItemColor() {
                    @Override
                    public int getColorFromItemstack(ItemStack itemStack, int tintIndex) {
                        return ((IItemColor) itemEE).getColorFromItemstack(itemStack, tintIndex);
                    }
                }, itemEE);
            }
        }
    }
}
