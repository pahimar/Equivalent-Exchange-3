package com.pahimar.ee.item.base;

import net.minecraft.client.renderer.ItemMeshDefinition;

public interface IItemVariantHolder<T extends ItemBase> {

    T getItem();

    String[] getVariants();

    default ItemMeshDefinition getCustomMeshDefinition() {
        return null;
    }
}
