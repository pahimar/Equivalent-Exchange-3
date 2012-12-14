package ee3.common.item;

import ee3.common.lib.Reference;
import ee3.common.lib.Sprites;
import net.minecraft.item.Item;

/**
 * ItemEE
 * 
 * Wrapper for mod items
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemEE extends Item {

    public ItemEE(int id) {

        super(id - Reference.SHIFTED_ID_RANGE_CORRECTION);
        maxStackSize = 1;
        setTextureFile(Sprites.SPRITE_SHEET_LOCATION + Sprites.ITEM_SPRITE_SHEET);
        setNoRepair();
    }

}
