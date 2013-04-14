package com.pahimar.ee3.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.block.ModBlocks;
import com.pahimar.ee3.core.helper.NBTHelper;
import com.pahimar.ee3.lib.Colours;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.Reference;
import com.pahimar.ee3.lib.Strings;
import cpw.mods.fml.common.FMLCommonHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Equivalent-Exchange-3
 *
 * ItemAlchemicalBag
 *
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 */
public class ItemAlchemicalBag extends ItemEE {

    private static final String[] ALCHEMICAL_BAG_SUBTYPES = new String[] { "Open", "OpenDrawString", "Closed", "ClosedDrawString" };

    @SideOnly(Side.CLIENT)
    private Icon[] icons;



    public ItemAlchemicalBag(int id) {

        super(id);
        this.setUnlocalizedName(Strings.ALCHEMICAL_BAG_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {

        icons = new Icon[ALCHEMICAL_BAG_SUBTYPES.length];

        for (int i = 0; i < ALCHEMICAL_BAG_SUBTYPES.length; ++i) {
            icons[i] = iconRegister.registerIcon(Reference.MOD_ID.toLowerCase() + ":" + Strings.ALCHEMICAL_BAG_NAME + ALCHEMICAL_BAG_SUBTYPES[i]);
        }
    }

    /*
     * Allows the player to connect the bag to the specfic chest that was shift clicked on.
     */
    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        int blockId = world.getBlockId(x, y, z);
        if(player.isSneaking() && blockId == ModBlocks.alchemicalChest.blockID){
            NBTTagCompound tag = player.getCurrentEquippedItem().getTagCompound();
            if(tag == null){
                tag = new NBTTagCompound();
                player.getCurrentEquippedItem().setTagCompound(tag);
            }
            NBTTagCompound nbt = new NBTTagCompound("ConnectedChestLoc");
            nbt.setInteger("x", x);
            nbt.setInteger("y", y);
            nbt.setInteger("z", z);
            tag.setCompoundTag(Strings.NBT_ITEM_ALCHEMICAL_BAG_CONNECTED_CHEST, nbt);

            tag.setBoolean(NBT_TEMP_BAG_LOCK, true);//Tag is used to keep the bags gui from opening when shift clicking.

            if(!world.isRemote) return true;
        }
        return false;
    }

    private final String NBT_TEMP_BAG_LOCK = "TempNBTForLock";
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        boolean t = NBTHelper.hasTag(itemStack, NBT_TEMP_BAG_LOCK);

        //If the player is sneaking(a.k.a holding shift) will open the sharing window.
        if(entityPlayer.isSneaking() && !t && NBTHelper.hasTag(entityPlayer.getCurrentEquippedItem(), Strings.NBT_ITEM_ALCHEMICAL_BAG_CONNECTED_CHEST))
            NBTHelper.setBoolean(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_SHARE, true);

        if (!world.isRemote && !t) {
            NBTHelper.setBoolean(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN, true);
            entityPlayer.openGui(EquivalentExchange3.instance, GuiIds.ALCHEMICAL_BAG, entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }

        if(t)
            NBTHelper.removeTag(itemStack, NBT_TEMP_BAG_LOCK);

        return itemStack;
    }

    @Override
    public boolean getShareTag() {

        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {

        return true;
    }

    @Override
    public Icon getIcon(ItemStack itemStack, int renderPass) {

        // If the bag is open
        if (NBTHelper.hasTag(itemStack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_OPEN)) {
            if (renderPass != 1)
                return icons[0];
            else
                return icons[1];
        }
        // Else, the bag is closed
        else {
            if (renderPass != 1)
                return icons[2];
            else
                return icons[3];
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int renderPass) {

        if (renderPass == 1)
            return Integer.parseInt(Colours.PURE_WHITE, 16);
        else {
            int bagColor = this.getColor(itemStack);

            if (bagColor < 0) {
                bagColor = Integer.parseInt(Colours.PURE_WHITE, 16);
            }

            return bagColor;
        }
    }

    public boolean isSharing(ItemStack stack) {
        if(NBTHelper.hasTag(stack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_SHARE))
            return NBTHelper.getBoolean(stack, Strings.NBT_ITEM_ALCHEMICAL_BAG_GUI_SHARE);
        return false;
    }

    public boolean hasColor(ItemStack itemStack) {

        return !itemStack.hasTagCompound() ? false : !itemStack.getTagCompound().hasKey(Strings.NBT_ITEM_DISPLAY) ? false : itemStack.getTagCompound().getCompoundTag(Strings.NBT_ITEM_DISPLAY).hasKey(Strings.NBT_ITEM_COLOR);
    }

    public int getColor(ItemStack itemStack) {

        NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

        if (nbtTagCompound == null)
            return Integer.parseInt(Colours.PURE_WHITE, 16);
        else {

            NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);
            return displayTagCompound == null ? Integer.parseInt(Colours.PURE_WHITE, 16) : displayTagCompound.hasKey(Strings.NBT_ITEM_COLOR) ? displayTagCompound.getInteger(Strings.NBT_ITEM_COLOR) : Integer.parseInt(Colours.PURE_WHITE, 16);
        }
    }

    public void setColor(ItemStack itemStack, int color) {

        if (itemStack != null) {
            if (!(itemStack.getItem() instanceof ItemAlchemicalBag))
                // TODO Localize
                throw new UnsupportedOperationException("Can\'t dye non-bags!");
            else {

                NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

                if (nbtTagCompound == null) {

                    nbtTagCompound = new NBTTagCompound();
                    itemStack.setTagCompound(nbtTagCompound);
                }

                NBTTagCompound colourTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);

                if (!nbtTagCompound.hasKey(Strings.NBT_ITEM_DISPLAY)) {
                    nbtTagCompound.setCompoundTag(Strings.NBT_ITEM_DISPLAY, colourTagCompound);
                }

                colourTagCompound.setInteger(Strings.NBT_ITEM_COLOR, color);
            }
        }
    }

    public void removeColor(ItemStack itemStack) {

        if (itemStack != null) {

            NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

            if (nbtTagCompound != null) {

                NBTTagCompound displayTagCompound = nbtTagCompound.getCompoundTag(Strings.NBT_ITEM_DISPLAY);

                if (displayTagCompound.hasKey(Strings.NBT_ITEM_COLOR)) {

                    displayTagCompound.removeTag(Strings.NBT_ITEM_COLOR);
                }
            }
        }
    }
}
