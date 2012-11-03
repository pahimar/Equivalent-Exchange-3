package ee3.common.block;

import ee3.common.lib.Reference;
import net.minecraft.src.BlockStationary;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Material;

/**
 * BlockRedWaterFlowing
 * 
 * Class for the still version of Red Water
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockRedWaterStill extends BlockStationary {

    protected BlockRedWaterStill(int id, Material material) {
        super(id, material);
        this.blockHardness = 100F;
        this.setLightOpacity(3);
        this.setBlockName("redWaterStill");
        disableStats();
        setRequiresSelfNotify();
    }

    @Override
    public String getTextureFile() {
        return Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET;
    }

}
