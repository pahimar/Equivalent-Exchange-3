package com.pahimar.ee3.block;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Strings;

/**
 * Equivalent-Exchange-3
 * 
 * BlockRedWaterStill
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockRedWaterStill extends BlockStationary {

    protected BlockRedWaterStill(int id) {

        super(id, Material.water);
        blockHardness = 100F;
        this.setLightOpacity(3);
        this.setUnlocalizedName(Strings.RED_WATER_STILL_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.disableStats();
    }

    @Override
    public String getUnlocalizedName() {

        StringBuilder unlocalizedName = new StringBuilder();

        unlocalizedName.append("tile.");
        unlocalizedName.append(Strings.RESOURCE_PREFIX);
        unlocalizedName.append(Strings.RED_WATER_STILL_NAME);

        return unlocalizedName.toString();
    }
}
