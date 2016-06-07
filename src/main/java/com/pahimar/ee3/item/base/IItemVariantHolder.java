package com.pahimar.ee3.item.base;

import net.minecraft.client.renderer.ItemMeshDefinition;

public interface IItemVariantHolder<T extends ItemEE> {

    T getItem();

    String[] getVariants();

    ItemMeshDefinition getCustomMeshDefinition();
}
