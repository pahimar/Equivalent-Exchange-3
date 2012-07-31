package ee3.server.core;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityItem;
import net.minecraft.src.ModLoader;
import net.minecraft.src.WorldServer;
import net.minecraft.src.forge.DimensionManager;
import ee3.core.helper.Helper;
import ee3.item.ModItems;

public class MinumInWaterHandler {
	public static void HandleItems(){
		for (Integer id : DimensionManager.getIDs()){
			WorldServer theWorld = (WorldServer)DimensionManager.getWorld(id);
			if(theWorld != null){
				for(int i = 0; i < theWorld.loadedEntityList.size(); i++) {
					Entity entity = (Entity)theWorld.loadedEntityList.get(i);
					if(entity instanceof EntityItem){
						EntityItem item = (EntityItem)entity;
						if(item.item.getItem().shiftedIndex == ModItems.miniumShard.shiftedIndex){
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
}
