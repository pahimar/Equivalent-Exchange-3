package com.pahimar.ee3.core.handlers;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;

import com.pahimar.ee3.configuration.ConfigurationSettings;
import com.pahimar.ee3.core.helper.TransmutationHelper;
import com.pahimar.ee3.event.ActionEvent;
import com.pahimar.ee3.event.ActionEvent.ActionResult;
import com.pahimar.ee3.event.ActionRequestEvent;
import com.pahimar.ee3.event.WorldTransmutationEvent;
import com.pahimar.ee3.lib.ActionTypes;
import com.pahimar.ee3.lib.ItemUpdateTypes;
import com.pahimar.ee3.lib.Particles;
import com.pahimar.ee3.lib.Sounds;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketItemUpdate;
import com.pahimar.ee3.network.packet.PacketSoundEvent;
import com.pahimar.ee3.network.packet.PacketSpawnParticle;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

/**
 * Equivalent-Exchange-3
 * 
 * WorldTransmutationHandler
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class WorldTransmutationHandler {

    public static void handleWorldTransmutation(EntityPlayer thePlayer, int originX, int originY, int originZ, byte rangeX, byte rangeY, byte rangeZ, byte sideHit, String data) {

        ActionRequestEvent actionRequestEvent = null;
        ActionEvent actionEvent = null;

        int lowerBoundX = -1 * rangeX / 2;
        int upperBoundX = -1 * lowerBoundX;
        int lowerBoundY = -1 * rangeY / 2;
        int upperBoundY = -1 * lowerBoundY;
        int lowerBoundZ = -1 * rangeZ / 2;
        int upperBoundZ = -1 * lowerBoundZ;
        boolean anySuccess = false;

        double xShift = 0;
        double yShift = 0;
        double zShift = 0;

        int xSign = 1;
        int ySign = 1;
        int zSign = 1;

        switch (ForgeDirection.getOrientation(sideHit)) {
            case UP: {
                yShift = 1.5D;
                break;
            }
            case DOWN: {
                yShift = 0.1D;
                ySign = -1;
                break;
            }
            case NORTH: {
                zShift = 1D;
                zSign = -1;
                break;
            }
            case SOUTH: {
                zShift = 1D;
                break;
            }
            case EAST: {
                xShift = 1D;
                break;
            }
            case WEST: {
                xShift = 1D;
                xSign = -1;
                break;
            }
            case UNKNOWN: {
                break;
            }
            default:
                break;
        }

        for (int x = lowerBoundX; x <= upperBoundX; x++) {
            for (int y = lowerBoundY; y <= upperBoundY; y++) {
                for (int z = lowerBoundZ; z <= upperBoundZ; z++) {

                    actionEvent = new WorldTransmutationEvent(ActionTypes.TRANSMUTATION, thePlayer.getCurrentEquippedItem(), thePlayer, thePlayer.worldObj, originX + x, originY + y, originZ + z, false, data);

                    if (actionEvent != null) {
                        actionRequestEvent = new ActionRequestEvent(thePlayer, actionEvent, originX + x, originY + y, originZ + z, sideHit);
                        MinecraftForge.EVENT_BUS.post(actionRequestEvent);

                        if (actionRequestEvent.allowEvent != Result.DENY) {
                            MinecraftForge.EVENT_BUS.post(actionEvent);
                        }

                        if (actionEvent.actionResult == ActionResult.SUCCESS) {
                            if (!anySuccess) {
                                anySuccess = true;
                            }

                            PacketDispatcher.sendPacketToAllAround(originX + x, originY + y, originZ + z, 64D, thePlayer.worldObj.provider.dimensionId, PacketTypeHandler.populatePacket(new PacketSpawnParticle(Particles.LARGE_SMOKE, originX + x + xShift * xSign, originY + y + yShift * ySign, originZ + z + zShift * zSign, 0D * xSign, 0.05D * ySign, 0D * zSign)));
                            PacketDispatcher.sendPacketToAllAround(originX + x, originY + y, originZ + z, 64D, thePlayer.worldObj.provider.dimensionId, PacketTypeHandler.populatePacket(new PacketSpawnParticle(Particles.LARGE_EXPLODE, originX + x + xShift * xSign, originY + y + yShift * ySign, originZ + z + zShift * zSign, 0D * xSign, 0.15D * ySign, 0D * zSign)));
                        }
                        else if (actionEvent.actionResult == ActionResult.FAILURE) {
                            if (!(actionEvent.world.getBlockId(originX + x, originY + y, originZ + z) == 0)) {
                                // TODO Fancy particle motion
                                PacketDispatcher.sendPacketToAllAround(originX + x, originY + y, originZ + z, 64D, thePlayer.worldObj.provider.dimensionId, PacketTypeHandler.populatePacket(new PacketSpawnParticle(Particles.RED_DUST, originX + x + xShift * xSign, originY + y + yShift * ySign, originZ + z + zShift * zSign, 0D * xSign, 0.05D * ySign, 0D * zSign)));
                                PacketDispatcher.sendPacketToAllAround(originX + x, originY + y, originZ + z, 64D, thePlayer.worldObj.provider.dimensionId, PacketTypeHandler.populatePacket(new PacketSpawnParticle(Particles.WITCH_MAGIC, originX + x + xShift * xSign, originY + y + yShift * ySign, originZ + z + zShift * zSign, 0D * xSign, 0.05D * ySign, 0D * zSign)));
                            }
                        }
                    }
                }
            }
        }

        if (anySuccess) {
            PacketDispatcher.sendPacketToAllAround(originX, originY, originZ, 64D, thePlayer.worldObj.provider.dimensionId, PacketTypeHandler.populatePacket(new PacketSoundEvent(thePlayer.username, Sounds.TRANSMUTE, originX, originY, originZ, 0.5F, 1.0F)));
        }
        else {
            PacketDispatcher.sendPacketToAllAround(originX, originY, originZ, 64D, thePlayer.worldObj.provider.dimensionId, PacketTypeHandler.populatePacket(new PacketSoundEvent(thePlayer.username, Sounds.FAIL, originX, originY, originZ, 1.5F, 1.5F)));
        }
    }

    @ForgeSubscribe
    public void onWorldTransmutationEvent(WorldTransmutationEvent event) {

        int id = event.world.getBlockId(event.x, event.y, event.z);
        int meta = event.world.getBlockMetadata(event.x, event.y, event.z);
        boolean result = false;

        Block currentBlock = Block.blocksList[id];

        if (currentBlock != null) {
            meta = currentBlock.damageDropped(meta);
        }

        ItemStack worldStack = new ItemStack(id, 1, meta);
        ItemStack targetStack = new ItemStack(event.targetID, 1, event.targetMeta);

        if (!worldStack.isItemEqual(targetStack)) {
            if (EquivalencyHandler.instance().areWorldEquivalent(worldStack, targetStack)) {
                if (event.itemStack != null) {
                    if (event.itemStack.getItemDamage() <= event.itemStack.getMaxDamage()) {
                        result = TransmutationHelper.transmuteInWorld(event.world, event.player, event.player.getCurrentEquippedItem(), event.x, event.y, event.z, event.targetID, event.targetMeta);
                    }
                }
            }
        }

        if (result) {
            event.actionResult = ActionResult.SUCCESS;

            int currentSlot = event.player.inventory.currentItem;
            event.itemStack.damageItem(ConfigurationSettings.TRANSMUTE_COST_BLOCK, event.player);

            if (event.itemStack.stackSize < 1) {
                event.player.inventory.setInventorySlotContents(currentSlot, null);
                PacketDispatcher.sendPacketToPlayer(PacketTypeHandler.populatePacket(new PacketItemUpdate((byte) currentSlot, ItemUpdateTypes.DESTROYED)), (Player) event.player);
                event.player.worldObj.playSoundAtEntity(event.player, "random.break", 0.8F, 0.8F + event.player.worldObj.rand.nextFloat() * 0.4F);
            }
        }
        else {
            event.actionResult = ActionResult.FAILURE;
        }
    }

}
