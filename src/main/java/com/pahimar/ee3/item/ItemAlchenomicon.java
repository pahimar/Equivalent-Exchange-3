package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.item.base.ItemBase;
import com.pahimar.ee3.reference.GuiIds;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemAlchenomicon extends ItemBase {

    public ItemAlchenomicon() {
        super("alchenomicon");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer entityPlayer, EnumHand hand) {
        entityPlayer.openGui(EquivalentExchange3.instance, GuiIds.ALCHENOMICON, entityPlayer.getEntityWorld(), (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        return new ActionResult<>(EnumActionResult.SUCCESS, entityPlayer.getHeldItem(hand));
    }
}
