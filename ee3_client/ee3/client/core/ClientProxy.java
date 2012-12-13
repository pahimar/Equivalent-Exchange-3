package ee3.client.core;

import net.minecraft.item.EnumRarity;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import ee3.client.core.handlers.DrawBlockHighlightHandler;
import ee3.client.core.handlers.KeyBindingHandler;
import ee3.client.core.handlers.RenderTickHandler;
import ee3.client.core.handlers.SoundHandler;
import ee3.client.core.helper.KeyBindingHelper;
import ee3.client.render.RenderCalcinator;
import ee3.client.render.RenderItemCalcinator;
import ee3.client.render.TextureRedWaterFX;
import ee3.client.render.TextureRedWaterFlowFX;
import ee3.common.core.CommonProxy;
import ee3.common.lib.BlockIds;
import ee3.common.lib.RenderIds;
import ee3.common.lib.Sprites;
import ee3.common.network.PacketTypeHandler;
import ee3.common.network.PacketWorldEvent;
import ee3.common.tile.TileCalcinator;
import static ee3.common.lib.CustomItemRarity.*;

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

        TickRegistry.registerTickHandler(new RenderTickHandler(), Side.CLIENT);
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

        MinecraftForgeClient.preloadTexture(Sprites.SPRITE_SHEET_LOCATION + Sprites.BLOCK_SPRITE_SHEET);
        MinecraftForgeClient.preloadTexture(Sprites.SPRITE_SHEET_LOCATION + Sprites.ITEM_SPRITE_SHEET);

        FMLClientHandler.instance().getClient().renderEngine.registerTextureFX(new TextureRedWaterFX());
        FMLClientHandler.instance().getClient().renderEngine.registerTextureFX(new TextureRedWaterFlowFX());

        MinecraftForgeClient.registerItemRenderer(BlockIds.CALCINATOR, new RenderItemCalcinator());
    }

    @Override
    public void initTileEntities() {

        super.initTileEntities();

        ClientRegistry.bindTileEntitySpecialRenderer(TileCalcinator.class, new RenderCalcinator());
    }

    @Override
    public void sendWorldEventPacket(byte eventType, int originX, int originY, int originZ, byte sideHit, byte rangeX, byte rangeY, byte rangeZ, String data) {

        PacketDispatcher.sendPacketToServer(PacketTypeHandler.populatePacket(new PacketWorldEvent(eventType, originX, originY, originZ, sideHit, rangeX, rangeY, rangeZ, data)));
    }

}
