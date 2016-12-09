package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.item.base.ItemBase;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.reference.Messages;
import com.pahimar.ee3.util.ItemStackUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemAlchenomicon extends ItemBase {

    public ItemAlchenomicon() {
        super("alchenomicon");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityPlayer, EnumHand hand) {

        if (!world.isRemote) {
            ItemStack itemStack = entityPlayer.getHeldItem(hand);
            if (!ItemStackUtils.hasOwner(itemStack)) {
                ItemStackUtils.setOwner(itemStack, entityPlayer);
                entityPlayer.sendMessage(new TextComponentTranslation(Messages.OWNER_SET_TO_SELF, itemStack.getTextComponent()));
            }
            else {
                entityPlayer.openGui(EquivalentExchange3.instance, GUIs.ALCHENOMICON.ordinal(), entityPlayer.getEntityWorld(), (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
            }
        }

        return new ActionResult(EnumActionResult.SUCCESS, entityPlayer.getHeldItem(hand));
    }
}
