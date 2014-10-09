package com.pahimar.ee3.item;

import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.network.PacketHandler;
import com.pahimar.ee3.network.message.MessageChalkSettings;
import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.settings.ChalkSettings;
import com.pahimar.ee3.util.EntityHelper;
import com.pahimar.ee3.util.IKeyBound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemChalk extends ItemEE implements IKeyBound
{
    public ItemChalk()
    {
        super();
        this.setUnlocalizedName(Names.Items.CHALK);
    }

    @Override
    public boolean getShareTag()
    {
        return true;
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (side == 0)
        {
            --y;
        }
        else if (side == 1)
        {
            ++y;
        }
        else if (side == 2)
        {
            --z;
        }
        else if (side == 3)
        {
            ++z;
        }
        else if (side == 4)
        {
            --x;
        }
        else if (side == 5)
        {
            ++x;
        }

        if (world.canPlaceEntityOnSide(ModBlocks.alchemyArray, x, y, z, false, side, entityPlayer, itemStack))
        {
            int sideHit = ModBlocks.alchemyArray.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, itemStack.getItemDamage());
            if (placeBlockAt(itemStack, entityPlayer, world, x, y, z, side, hitX, hitY, hitZ, sideHit))
            {
                world.playSoundEffect(x + 0.5d, y + 0.5d, z + 0.5d, ModBlocks.alchemyArray.stepSound.func_150496_b(), (ModBlocks.alchemyArray.stepSound.getVolume() + 1.0F) / 2.0F, ModBlocks.alchemyArray.stepSound.getPitch() * 0.8F);
                --itemStack.stackSize;
            }
            return true;
        }

        return false;
    }

    /**
     * Called to actually place the block, after the location is determined and all permission checks have been made.
     *
     * @param stack  The item stack that was used to place the block. This can be changed inside the method.
     * @param player The player who is placing the block. Can be null if the block is not being placed by a player.
     * @param side   The side the player (or machine) right-clicked on.
     */
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
    {

        if (!world.setBlock(x, y, z, ModBlocks.alchemyArray, metadata, 3))
        {
            return false;
        }

        if (world.getBlock(x, y, z) == ModBlocks.alchemyArray)
        {
            ModBlocks.alchemyArray.onBlockPlacedBy(world, x, y, z, player, stack);
            ModBlocks.alchemyArray.onPostBlockPlaced(world, x, y, z, metadata);
        }

        return true;
    }

    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key)
    {
        if (key != Key.UNKNOWN)
        {
            NBTTagCompound playerCustomData = EntityHelper.getCustomEntityData(entityPlayer);
            ChalkSettings chalkSettings = new ChalkSettings();
            chalkSettings.readFromNBT(playerCustomData);

            if (key == Key.CHARGE)
            {
                if (!entityPlayer.isSneaking())
                {
                    chalkSettings.incrementSize();
                }
                else
                {
                    chalkSettings.decrementSize();
                }
            }
            else if (key == Key.TOGGLE)
            {
                if (!entityPlayer.isSneaking())
                {
                    chalkSettings.incrementIndex();
                }
                else
                {
                    chalkSettings.decrementIndex();
                }
            }
            else if (key == Key.RELEASE)
            {
                if (!entityPlayer.isSneaking())
                {
                    chalkSettings.rotateClockwise();
                }
                else
                {
                    chalkSettings.rotateCounterClockwise();
                }
            }

            chalkSettings.writeToNBT(playerCustomData);
            EntityHelper.saveCustomEntityData(entityPlayer, playerCustomData);
            PacketHandler.INSTANCE.sendTo(new MessageChalkSettings(chalkSettings), (EntityPlayerMP) entityPlayer);
        }
    }

}