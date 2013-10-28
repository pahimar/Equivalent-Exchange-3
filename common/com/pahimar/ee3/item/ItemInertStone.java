package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Strings;

/**
 * Equivalent-Exchange-3
 * 
 * ItemInertStone
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemInertStone extends ItemEE {

    public ItemInertStone(int id) {

        super(id);
        this.setUnlocalizedName(Strings.RESOURCE_PREFIX + Strings.INERT_STONE_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
    }
}
