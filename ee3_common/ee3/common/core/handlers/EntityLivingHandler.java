package ee3.common.core.handlers;

import ee3.common.core.helper.RedWaterHelper;
import ee3.common.lib.Reference;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.Potion;
import net.minecraft.src.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class EntityLivingHandler {

	@ForgeSubscribe
	public void onEntityLivingUpdate(LivingUpdateEvent event) {
		EntityLiving entity = event.entityLiving;
		
		if (entity.worldObj.getWorldTime() % 4 == 0) {
			System.out.println(entity.toString());
			
			if (RedWaterHelper.handleRedWaterDetection(entity)) {
				entity.addPotionEffect(new PotionEffect(Potion.weakness.id, Reference.BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER * Reference.BLOCK_RED_WATER_EFFECT_DURATION_BASE * Reference.SECOND_IN_TICKS, 0));
				entity.addPotionEffect(new PotionEffect(Potion.poison.id, Reference.BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER * Reference.BLOCK_RED_WATER_EFFECT_DURATION_BASE * Reference.SECOND_IN_TICKS, 0));
				entity.addPotionEffect(new PotionEffect(Potion.blindness.id, Reference.BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER * Reference.BLOCK_RED_WATER_EFFECT_DURATION_BASE * Reference.SECOND_IN_TICKS, 0));
				entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, Reference.BLOCK_RED_WATER_EFFECT_DURATION_MODIFIER * Reference.BLOCK_RED_WATER_EFFECT_DURATION_BASE * Reference.SECOND_IN_TICKS, 0));
			}
		}
	}
	
	@ForgeSubscribe
	public void onEntityLivingDeath(LivingDeathEvent event) {
		
	}
	
}
