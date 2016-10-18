package com.pahimar.ee3.item;

import com.pahimar.ee3.item.base.ItemBase;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.IOwnable;
import com.pahimar.ee3.util.ItemStackUtils;
import com.pahimar.ee3.util.NBTUtils;
import com.pahimar.ee3.util.ResourceLocationHelper;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemAlchemicalBag extends ItemBase implements IOwnable, IItemColor {

    private static final String[] VARIANTS = {"alchemical_bag_closed", "alchemical_bag_open"};

    public ItemAlchemicalBag() {
        super("alchemical_bag", VARIANTS);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer, EnumHand hand) {

        if (!world.isRemote) {
            if (!ItemStackUtils.hasOwner(itemStack)) {
                ItemStackUtils.setOwner(itemStack, entityPlayer);
                entityPlayer.addChatComponentMessage(new TextComponentTranslation(Messages.OWNER_SET_TO_SELF, itemStack.getTextComponent()));
            }

            // Set a UUID on the Alchemical Bag, if one doesn't exist already
            if (!NBTUtils.hasUUID(itemStack, Names.NBT.UUID)) {
                NBTUtils.setUUID(itemStack, Names.NBT.UUID);
            }
            else {
                // TODO Scan player inventory and if we find a bag with the same UUID, change it's UUID
            }

            if (isBagOpen(itemStack)) {
                closeBag(itemStack);
            }
            else {
                openBag(itemStack);
            }

            // TODO Get the Gui working again
//            entityPlayer.openGui(EquivalentExchange3.instance, GUIs.ALCHEMICAL_BAG.ordinal(), entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }

        return new ActionResult<>(EnumActionResult.PASS, itemStack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemstack(ItemStack itemStack, int renderPass) {
        return NBTUtils.getColor(itemStack);
    }

    @Override
    public ItemMeshDefinition getCustomMeshDefinition() {

        return itemStack -> isBagOpen(itemStack)
                ? ResourceLocationHelper.getModelResourceLocation(VARIANTS[1])
                : ResourceLocationHelper.getModelResourceLocation(VARIANTS[0]);
    }

    private static boolean isBagOpen(ItemStack itemStack) {
        return NBTUtils.getBoolean(itemStack, Names.NBT.GUI_OPEN);
    }

    private static void openBag(ItemStack itemStack) {
        NBTUtils.setBoolean(itemStack, Names.NBT.GUI_OPEN, true);
    }

    private static void closeBag(ItemStack itemStack) {
        NBTUtils.setBoolean(itemStack, Names.NBT.GUI_OPEN, false);
    }
}
