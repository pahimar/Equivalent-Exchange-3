package ee3;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.network.NetworkMod;
import ee3.lib.Reference;
import ee3.network.PacketHandler;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class EquivalentExchange3 {

}
