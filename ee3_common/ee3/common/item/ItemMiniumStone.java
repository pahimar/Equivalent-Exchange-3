package ee3.common.item;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.network.PacketDispatcher;
import ee3.common.EquivalentExchange3;
import ee3.common.core.helper.TransmutationHelper;
import ee3.common.lib.Colours;
import ee3.common.lib.ConfigurationSettings;
import ee3.common.lib.CustomItemRarity;
import ee3.common.lib.GuiIds;
import ee3.common.lib.Strings;
import ee3.common.network.PacketKeyPressed;
import ee3.common.network.PacketTypeHandler;

/**
 * ItemMiniumStone
 * 
 * The "lesser" or "imperfect" Philosophers Stone
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class ItemMiniumStone extends ItemEE implements ITransmutationStone {
    
    public ItemMiniumStone(int id) {
        super(id);
        this.setIconCoord(1, 0);
        this.setItemName(Strings.MINIUM_STONE_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setMaxDamage(ConfigurationSettings.MINIUM_STONE_MAX_DURABILITY - 1);
    }

    @SideOnly(Side.CLIENT)
    public EnumRarity getRarity(ItemStack stack) {
        return EquivalentExchange3.proxy.getCustomRarityType(CustomItemRarity.MAGICAL);
    }
    
    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
    	return false;
    }
    
    @Override
    public boolean getShareTag() {
    	return true;
    }
    
    @Override
    public ItemStack getContainerItemStack(ItemStack itemStack) {
    	itemStack.setItemDamage(itemStack.getItemDamage() + 1);
    	
		return itemStack;
    }
    
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int l, float f1, float f2, float f3) {
		boolean result = TransmutationHelper.transmuteInWorld(world, entityPlayer, itemStack, x, y, z);
		
		if (result) {
			itemStack.damageItem(1, entityPlayer);
		}
		
		return result;
    }
	
	@SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2)
    {
        return Integer.parseInt(Colours.PURE_RED, 16);
    }

    @Override
    public void openPortableCrafting(String keyPressed) {
        /*
         * Notify the Server that we opened the GUI. When the server receives the packet, it will open the Gui
         * server side, and notify the client to open the Gui client side on its own. Magic!
         */
        PacketDispatcher.sendPacketToServer(PacketTypeHandler.populatePacket(new PacketKeyPressed(keyPressed)));
    }
    
}
