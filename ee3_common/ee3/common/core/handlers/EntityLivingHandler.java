package ee3.common.core.handlers;

import ee3.common.core.helper.ItemDropHelper;
import ee3.common.core.helper.RedWaterHelper;
import ee3.common.lib.Reference;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

/**
 * EntityLivingHandler
 * 
 * Class containing all EE3 custom event related logic for EntityLiving events
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class EntityLivingHandler {

	@ForgeSubscribe
	public void onEntityLivingUpdate(LivingUpdateEvent event) {
		/* Disabled because of horribly inefficient code
	    EntityLiving entity = event.entityLiving;
		
		if (entity.worldObj.getWorldTime() % 4 == 0) {
			
			if (RedWaterHelper.handleRedWaterDetection(entity)) {
				entity.addPotionEffect(new PotionEffect(Potion.weakness.id, Reference.BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER * Reference.BLOCK_RED_WATER_EFFECT_DURATION_BASE * Reference.SECOND_IN_TICKS, 0));
				entity.addPotionEffect(new PotionEffect(Potion.poison.id, Reference.BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER * Reference.BLOCK_RED_WATER_EFFECT_DURATION_BASE * Reference.SECOND_IN_TICKS, 0));
				entity.addPotionEffect(new PotionEffect(Potion.blindness.id, Reference.BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER * Reference.BLOCK_RED_WATER_EFFECT_DURATION_BASE * Reference.SECOND_IN_TICKS, 0));
				entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, Reference.BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER * Reference.BLOCK_RED_WATER_EFFECT_DURATION_BASE * Reference.SECOND_IN_TICKS, 0));
			}
		}
		*/
	}
	
	@ForgeSubscribe
	public void onEntityLivingDeath(LivingDeathEvent event) {
		if (event.source.getDamageType().equals("player")) {
			ItemDropHelper.dropMiniumShard((EntityPlayer)event.source.getSourceOfDamage(), event.entityLiving);
		}
	}
	
}
