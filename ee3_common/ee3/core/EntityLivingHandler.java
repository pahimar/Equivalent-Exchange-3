package ee3.core;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.forge.adaptors.EntityLivingHandlerAdaptor;

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
