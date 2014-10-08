package com.pahimar.ee3.item;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.array.GlyphTextureRegistry;
import com.pahimar.ee3.init.ModBlocks;
import com.pahimar.ee3.reference.GUIs;
import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Sounds;
import com.pahimar.ee3.util.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemChalk extends ItemEE implements IKeyBound, IChargeable, IOverlayItem
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
     * @param stack
     *         The item stack that was used to place the block. This can be changed inside the method.
     * @param player
     *         The player who is placing the block. Can be null if the block is not being placed by a player.
     * @param side
     *         The side the player (or machine) right-clicked on.
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
        NBTTagCompound playerCustomData = EntityHelper.getCustomEntityData(entityPlayer);
        NBTTagCompound chalkSettings = playerCustomData.getCompoundTag("chalk_settings");

        int index = 0;
        int size = 1;
        int rotation = 0;

        if (chalkSettings.hasKey("index"))
        {
            index = chalkSettings.getInteger("index");
        }

        if (chalkSettings.hasKey("size"))
        {
            size = chalkSettings.getInteger("size");
        }

        if (key == Key.CHARGE)
        {
            if (!entityPlayer.isSneaking())
            {
//                if (getChargeLevel(itemStack) == this.getMaxChargeLevel())
//                {
//                    NetworkSoundHelper.playSoundAt(entityPlayer, Sounds.FAIL, 1.5f, 1.5f);
//                }
//                else
//                {
//                    increaseChargeLevel(itemStack);
//                    NetworkSoundHelper.playSoundAt(entityPlayer, Sounds.CHARGE_UP, 0.5F, 0.5F + 0.5F * (getChargeLevel(itemStack) * 1.0F / this.getMaxChargeLevel()));
//                }
            }
            else
            {
                if (getChargeLevel(itemStack) == 0)
                {
                    NetworkSoundHelper.playSoundAt(entityPlayer, Sounds.FAIL, 1.5f, 1.5f);
                }
                else
                {
                    decreaseChargeLevel(itemStack);
                    NetworkSoundHelper.playSoundAt(entityPlayer, Sounds.CHARGE_DOWN, 0.5F, 1.0F - (0.5F - 0.5F * (getChargeLevel(itemStack) * 1.0F / this.getMaxChargeLevel())));
                }
            }
        }
        else if (key == Key.TOGGLE)
        {
            entityPlayer.openGui(EquivalentExchange3.instance, GUIs.SYMBOL_SELECTION.ordinal(), entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);
        }
    }

    @Override
    public short getMaxChargeLevel()
    {
        return 6;
    }

    @Override
    public short getChargeLevel(ItemStack itemStack)
    {
        return NBTHelper.getShort(itemStack, Names.NBT.CHARGE_LEVEL);
    }

    @Override
    public void setChargeLevel(ItemStack itemStack, short chargeLevel)
    {
        if (chargeLevel <= this.getMaxChargeLevel())
        {
            NBTHelper.setShort(itemStack, Names.NBT.CHARGE_LEVEL, chargeLevel);
        }
    }

    @Override
    public void increaseChargeLevel(ItemStack itemStack)
    {
        if (NBTHelper.getShort(itemStack, Names.NBT.CHARGE_LEVEL) < this.getMaxChargeLevel())
        {
            NBTHelper.setShort(itemStack, Names.NBT.CHARGE_LEVEL, (short) (NBTHelper.getShort(itemStack, Names.NBT.CHARGE_LEVEL) + 1));
        }
    }

    @Override
    public void decreaseChargeLevel(ItemStack itemStack)
    {
        if (NBTHelper.getShort(itemStack, Names.NBT.CHARGE_LEVEL) > 0)
        {
            NBTHelper.setShort(itemStack, Names.NBT.CHARGE_LEVEL, (short) (NBTHelper.getShort(itemStack, Names.NBT.CHARGE_LEVEL) - 1));
        }
    }

    public class ChalkSettings implements INBTTaggable
    {
        private int index;
        private int size;
        private int rotation;

        private final int MAX_SIZE = 6;

        public ChalkSettings()
        {
            this(0, 1, 0);
        }

        public ChalkSettings(int index, int size, int rotation)
        {
            this.index = index;
            this.size = size;
            this.rotation = rotation;
        }

        public int getIndex()
        {
            return index;
        }

        public void setIndex(int index)
        {
            if (index < 0 || index >= GlyphTextureRegistry.getInstance().getGlyphs().size())
            {
                this.index = 0;
            }
            else
            {
                this.index = index;
            }
        }

        public int getSize()
        {
            return size;
        }

        public void setSize(int size)
        {
            if (size < 1)
            {
                this.size = 1;
            }
            else if (size > MAX_SIZE)
            {
                this.size = MAX_SIZE;
            }
            else
            {
                this.size = size;
            }
        }

        public int getRotation()
        {
            return rotation;
        }

        public void setRotation(int rotation)
        {
            // TODO: Pick up here in the morning
        }

        @Override
        public void readFromNBT(NBTTagCompound nbtTagCompound)
        {
            if (nbtTagCompound != null && nbtTagCompound.hasKey("chalk_settings") && nbtTagCompound.getTag("chalk_settings").getId() == (byte) 10)
            {
                NBTTagCompound chalkSettings = nbtTagCompound.getCompoundTag("chalk_settings");
                if (chalkSettings.hasKey("index"))
                {
                    this.index = chalkSettings.getInteger("index");

                    if (this.index < 0 || this.index >= GlyphTextureRegistry.getInstance().getGlyphs().size())
                    {
                        this.index = 0;
                    }
                }
                else
                {
                    this.index = 0;
                }

                if (chalkSettings.hasKey("size"))
                {
                    this.size = chalkSettings.getInteger("size");

                    if (this.size < 1)
                    {
                        this.size = 0;
                    }
                    else if (this.size > MAX_SIZE)
                    {
                        this.size = MAX_SIZE;
                    }
                }
                else
                {
                    this.size = 1;
                }

                if (chalkSettings.hasKey("rotation"))
                {
                    this.rotation = chalkSettings.getInteger("rotation");

                    if (this.rotation < 0)
                    {
                        this.rotation = 0;
                    }
                    else
                    {
                        this.rotation = this.rotation % 4;
                    }
                }
                else
                {
                    this.rotation = 0;
                }
            }
            else
            {
                this.index = 0;
                this.size = 1;
                this.rotation = 0;
            }
        }

        @Override
        public void writeToNBT(NBTTagCompound nbtTagCompound)
        {
            NBTTagCompound chalkSettings = new NBTTagCompound();
            chalkSettings.setInteger("index", index);
            chalkSettings.setInteger("size", size);
            chalkSettings.setInteger("rotation", rotation);
            nbtTagCompound.setTag("chalk_settings", chalkSettings);
        }
    }
}