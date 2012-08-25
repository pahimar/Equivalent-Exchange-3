package ee3.common.item;

/**
* ItemEEStackable
* 
* Stackable version of mod items
* 
* @author pahimar
* @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
*
*/
public class ItemEEStackable extends ItemEE {

	public ItemEEStackable(int id) {
		super(id);
		maxStackSize = 64;
	}

}
