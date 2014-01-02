package com.pahimar.ee3.item;

import com.pahimar.ee3.lib.Strings;

/**
 * Equivalent-Exchange-3
 * <p/>
 * ItemMiniumShard
 *
 * @author pahimar
 */
public class ItemMiniumShard extends ItemEE
{
    public ItemMiniumShard(int id)
    {
        super(id);
        this.setUnlocalizedName(Strings.MINIUM_SHARD_NAME);
        maxStackSize = 64;
    }
}
