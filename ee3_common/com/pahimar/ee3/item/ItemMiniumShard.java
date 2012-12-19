package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Strings;

/**
 * ItemMiniumShard
 * 
 * A shard of Minium
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemMiniumShard extends ItemEE {

    public ItemMiniumShard(int id) {

        super(id);
        this.setIconCoord(0, 0);
        this.setItemName(Strings.MINIUM_SHARD_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        maxStackSize = 64;
    }

}
