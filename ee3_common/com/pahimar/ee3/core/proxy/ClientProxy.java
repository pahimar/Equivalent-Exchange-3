package com.pahimar.ee3.core.proxy;

import static com.pahimar.ee3.lib.CustomItemRarity.COLOR_EPIC;
import static com.pahimar.ee3.lib.CustomItemRarity.COLOR_JUNK;
import static com.pahimar.ee3.lib.CustomItemRarity.COLOR_LEGENDARY;
import static com.pahimar.ee3.lib.CustomItemRarity.COLOR_MAGICAL;
import static com.pahimar.ee3.lib.CustomItemRarity.COLOR_NORMAL;
import static com.pahimar.ee3.lib.CustomItemRarity.COLOR_RARE;
import static com.pahimar.ee3.lib.CustomItemRarity.COLOR_UNCOMMON;
import static com.pahimar.ee3.lib.CustomItemRarity.DISPLAY_NAME_EPIC;
import static com.pahimar.ee3.lib.CustomItemRarity.DISPLAY_NAME_JUNK;
import static com.pahimar.ee3.lib.CustomItemRarity.DISPLAY_NAME_LEGENDARY;
import static com.pahimar.ee3.lib.CustomItemRarity.DISPLAY_NAME_MAGICAL;
import static com.pahimar.ee3.lib.CustomItemRarity.DISPLAY_NAME_NORMAL;
import static com.pahimar.ee3.lib.CustomItemRarity.DISPLAY_NAME_RARE;
import static com.pahimar.ee3.lib.CustomItemRarity.DISPLAY_NAME_UNCOMMON;
import static com.pahimar.ee3.lib.CustomItemRarity.EPIC;
import static com.pahimar.ee3.lib.CustomItemRarity.JUNK;
import static com.pahimar.ee3.lib.CustomItemRarity.LEGENDARY;
import static com.pahimar.ee3.lib.CustomItemRarity.MAGICAL;
import static com.pahimar.ee3.lib.CustomItemRarity.NORMAL;
import static com.pahimar.ee3.lib.CustomItemRarity.RARE;
import static com.pahimar.ee3.lib.CustomItemRarity.UNCOMMON;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.MinecraftForge;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.client.audio.SoundHandler;
import com.pahimar.ee3.client.renderer.ItemAlchemicalChestRenderer;
import com.pahimar.ee3.client.renderer.ItemAludelRenderer;
import com.pahimar.ee3.client.renderer.ItemCalcinatorRenderer;
import com.pahimar.ee3.client.renderer.tileentity.TileEntityAlchemicalChestRenderer;
import com.pahimar.ee3.client.renderer.tileentity.TileEntityAludelRenderer;
import com.pahimar.ee3.client.renderer.tileentity.TileEntityCalcinatorRenderer;
import com.pahimar.ee3.core.handlers.DrawBlockHighlightHandler;
import com.pahimar.ee3.core.handlers.KeyBindingHandler;
import com.pahimar.ee3.core.handlers.TransmutationTargetOverlayHandler;
import com.pahimar.ee3.core.helper.KeyBindingHelper;
import com.pahimar.ee3.core.helper.TransmutationHelper;
import com.pahimar.ee3.item.IChargeable;
import com.pahimar.ee3.lib.ActionTypes;
import com.pahimar.ee3.lib.BlockIds;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Sprites;
import com.pahimar.ee3.network.PacketTypeHandler;
import com.pahimar.ee3.network.packet.PacketRequestEvent;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileCalcinator;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * ClientProxy
 * 
 * Client specific functionality that cannot be put into CommonProxy
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerKeyBindingHandler() {

        KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
    }

    @Override
    public void registerRenderTickHandler() {

        TickRegistry.registerTickHandler(new TransmutationTargetOverlayHandler(), Side.CLIENT);
    }

    @Override
    public void registerDrawBlockHighlightHandler() {

        MinecraftForge.EVENT_BUS.register(new DrawBlockHighlightHandler());
    }

    @Override
    public void setKeyBinding(String name, int value) {

        KeyBindingHelper.addKeyBinding(name, value);
        KeyBindingHelper.addIsRepeating(false);
    }

    @Override
    public void registerSoundHandler() {

        MinecraftForge.EVENT_BUS.register(new SoundHandler());
    }

    @Override
    public void initCustomRarityTypes() {

        EnumHelperClient.addRarity(JUNK, COLOR_JUNK, DISPLAY_NAME_JUNK);
        EnumHelperClient.addRarity(NORMAL, COLOR_NORMAL, DISPLAY_NAME_NORMAL);
        EnumHelperClient.addRarity(UNCOMMON, COLOR_UNCOMMON, DISPLAY_NAME_UNCOMMON);
        EnumHelperClient.addRarity(MAGICAL, COLOR_MAGICAL, DISPLAY_NAME_MAGICAL);
        EnumHelperClient.addRarity(RARE, COLOR_RARE, DISPLAY_NAME_RARE);
        EnumHelperClient.addRarity(EPIC, COLOR_EPIC, DISPLAY_NAME_EPIC);
        EnumHelperClient.addRarity(LEGENDARY, COLOR_LEGENDARY, DISPLAY_NAME_LEGENDARY);
    }

    @Override
    public EnumRarity getCustomRarityType(String customRarity) {

        for (EnumRarity rarity : EnumRarity.class.getEnumConstants()) {
            if (rarity.name().equals(customRarity))
                return rarity;
        }
        return EnumRarity.common;
    }

    @Override
    public void initRenderingAndTextures() {

        RenderIds.calcinatorRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.aludelRenderId = RenderingRegistry.getNextAvailableRenderId();
        RenderIds.alchemicalChestRenderId = RenderingRegistry.getNextAvailableRenderId();

        MinecraftForgeClient.preloadTexture(Sprites.SPRITE_SHEET_LOCATION + Sprites.BLOCK_SPRITE_SHEET);
        MinecraftForgeClient.preloadTexture(Sprites.SPRITE_SHEET_LOCATION + Sprites.ITEM_SPRITE_SHEET);

        MinecraftForgeClient.registerItemRenderer(BlockIds.CALCINATOR, new ItemCalcinatorRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockIds.ALUDEL, new ItemAludelRenderer());
        MinecraftForgeClient.registerItemRenderer(BlockIds.ALCHEMICAL_CHEST, new ItemAlchemicalChestRenderer());
    }

    @Override
    public void initTileEntities() {

        super.initTileEntities();

        ClientRegistry.bindTileEntitySpecialRenderer(TileCalcinator.class, new TileEntityCalcinatorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAludel.class, new TileEntityAludelRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAlchemicalChest.class, new TileEntityAlchemicalChestRenderer());
    }

    @Override
    public void sendRequestEventPacket(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data) {

        PacketDispatcher.sendPacketToServer(PacketTypeHandler.populatePacket(new PacketRequestEvent(eventType, originX, originY, originZ, sideHit, rangeX, rangeY, rangeZ, data)));
    }

    @Override
    public void transmuteBlock(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int sideHit) {

        if (TransmutationHelper.targetBlockStack != null) {
            if (itemStack != null) {
                int pnX = 1;
                int pnY = 1;
                int pnZ = 1;
                if (itemStack.getItem() instanceof IChargeable) {
                    int charge = ((IChargeable) itemStack.getItem()).getCharge(itemStack) * 2;
                    switch (ForgeDirection.getOrientation(sideHit)) {
                        case UP: {
                            pnX = 1 + charge;
                            pnZ = 1 + charge;
                            break;
                        }
                        case DOWN: {
                            pnX = 1 + charge;
                            pnZ = 1 + charge;
                            break;
                        }
                        case NORTH: {
                            pnX = 1 + charge;
                            pnY = 1 + charge;
                            break;
                        }
                        case SOUTH: {
                            pnX = 1 + charge;
                            pnY = 1 + charge;
                            break;
                        }
                        case EAST: {
                            pnY = 1 + charge;
                            pnZ = 1 + charge;
                            break;
                        }
                        case WEST: {
                            pnY = 1 + charge;
                            pnZ = 1 + charge;
                            break;
                        }
                        case UNKNOWN: {
                            pnX = 0;
                            pnY = 0;
                            pnZ = 0;
                            break;
                        }
                        default:
                            break;
                    }
                }

                EquivalentExchange3.proxy.sendRequestEventPacket(ActionTypes.TRANSMUTATION, x, y, z, (byte) sideHit, (byte) pnX, (byte) pnY, (byte) pnZ, TransmutationHelper.formatTargetBlockInfo(TransmutationHelper.targetBlockStack));
            }
        }
    }

}
