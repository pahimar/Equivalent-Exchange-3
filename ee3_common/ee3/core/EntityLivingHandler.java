package ee3.core;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.forge.adaptors.EntityLivingHandlerAdaptor;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class EntityLivingHandler extends EntityLivingHandlerAdaptor {

	@Override
    public boolean onEntityLivingDeath(EntityLiving entity, DamageSource killer)
    {
		if (killer.getDamageType().equals("player")) {
			EntityPlayer player = (EntityPlayer)killer.getSourceOfDamage();
			// We can do some really neat stuff here in the future!
		}
        return false;
    }
}
