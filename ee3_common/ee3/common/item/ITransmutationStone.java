package ee3.common.item;

import net.minecraft.entity.player.EntityPlayer;

/**
 * ITransmutationStone
 * 
 * Transmutation Stone interface, for use amongst all different kinds of transmutation stones
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public interface ITransmutationStone {

	public abstract void openPortableCrafting(EntityPlayer thePlayer);
	
}
