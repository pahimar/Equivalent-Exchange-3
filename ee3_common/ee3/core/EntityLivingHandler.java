package ee3.core;

import java.util.ArrayList;

import ee3.block.ModBlocks;
import ee3.core.helper.Helper;
import ee3.lib.Reference;

import net.minecraft.src.DamageSource;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ModLoader;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
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
	
	@Override
	public boolean onEntityLivingUpdate(EntityLiving entity) {		
		if (entity.worldObj.getWorldTime() % 4 == 0) {
			if (Helper.handleRedWaterDetection(entity)) {
				if (entity instanceof EntityPlayer) {
					entity.addPotionEffect(new PotionEffect(Potion.weakness.id, Reference.BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER * Reference.BLOCK_RED_WATER_EFFECT_DURATION_BASE * Reference.SECOND_IN_TICKS, 0));
					entity.addPotionEffect(new PotionEffect(Potion.poison.id, Reference.BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER * Reference.BLOCK_RED_WATER_EFFECT_DURATION_BASE * Reference.SECOND_IN_TICKS, 0));
					entity.addPotionEffect(new PotionEffect(Potion.blindness.id, Reference.BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER * Reference.BLOCK_RED_WATER_EFFECT_DURATION_BASE * Reference.SECOND_IN_TICKS, 0));
					entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, Reference.BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER * Reference.BLOCK_RED_WATER_EFFECT_DURATION_BASE * Reference.SECOND_IN_TICKS, 0));
				}
			}
		}
		return false;
	}
	
}
