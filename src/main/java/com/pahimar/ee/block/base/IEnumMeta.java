package com.pahimar.ee.block.base;

import net.minecraft.util.IStringSerializable;

public interface IEnumMeta extends IStringSerializable {

    int getMeta();

    default String getName() {
        return ((Enum) this).name().toLowerCase();
    }
}
