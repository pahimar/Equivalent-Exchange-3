package ee3.common.block;

import ee3.common.EquivalentExchange3;
import ee3.common.lib.Reference;
import ee3.common.lib.Sprites;
import ee3.common.lib.Strings;
import net.minecraft.src.BlockFlowing;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

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
