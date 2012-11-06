package ee3.common.item;

import ee3.common.EquivalentExchange3;
import ee3.common.lib.Strings;

/**
 * ItemInertStone
 * 
 * An inert stone
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemInertStone extends ItemEE {

	public ItemInertStone(int id) {
		super(id);
		this.setIconCoord(1, 0);
		this.setItemName(Strings.INERT_STONE_NAME);
		this.setCreativeTab(EquivalentExchange3.tabsEE3);
	}

}
