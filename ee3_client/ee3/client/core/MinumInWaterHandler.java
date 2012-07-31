package ee3.client.core;

import net.minecraft.client.Minecraft;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.ModLoader;
import ee3.core.helper.Helper;
import ee3.item.ModItems;

public class MinumInWaterHandler {
	private static Minecraft mc = ModLoader.getMinecraftInstance();
	public static void HandleItems(){
		if(!mc.theWorld.isRemote){
			for(int i = 0; i < mc.theWorld.loadedEntityList.size(); i++) {
				Entity entity = (Entity)mc.theWorld.loadedEntityList.get(i);
				if(entity instanceof EntityItem){
					if(((EntityItem)entity).item.getItem().shiftedIndex == ModItems.miniumShard.shiftedIndex){
						//If the helper has replaced the water with red water
						if(Helper.handleWaterMovement((EntityItem)entity)){
							//Destroy the minum shard
							entity.setDead();
						}
					}
				}
			}
		}
	}
}
