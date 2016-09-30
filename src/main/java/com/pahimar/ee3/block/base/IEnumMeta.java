package com.pahimar.ee3.block.base;

import net.minecraft.util.IStringSerializable;

public interface IEnumMeta extends IStringSerializable {

    int getMetadata();

    default String getName() {
        return ((Enum) this).name().toLowerCase();
    }
}
