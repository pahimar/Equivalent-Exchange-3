package ee3.common.item;

import ee3.common.lib.Reference;
import net.minecraft.src.Item;

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
        super(id - Reference.SHIFTED_ID_SIZE);
        maxStackSize = 1;
        setTextureFile(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
        setNoRepair();
    }

}
