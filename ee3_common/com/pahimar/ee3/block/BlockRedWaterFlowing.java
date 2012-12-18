package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Sprites;
import com.pahimar.ee3.lib.Strings;

import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;

/**
 * BlockRedWaterFlowing
 * 
 * Class for the flowing version of Red Water
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockRedWaterFlowing extends BlockFlowing {

    protected BlockRedWaterFlowing(int id) {
        super(id, Material.water);
        this.blockHardness = 100F;
        this.setLightOpacity(3);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setBlockName(Strings.RED_WATER_FLOWING_NAME);
    }

    @Override
    public String getTextureFile() {
        return Sprites.SPRITE_SHEET_LOCATION + Sprites.BLOCK_SPRITE_SHEET;
    }

}
