package com.pahimar.ee3.item.base;

import net.minecraft.client.renderer.ItemMeshDefinition;

public interface IItemVariantHolder<T extends ItemBase> {

    T getItem();

    String[] getVariants();

    default ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }
}
