package com.pahimar.ee3.item;

import com.pahimar.ee3.exchange.WrappedStack;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.util.EnergyValueHelper;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ItemDiviningRod extends ItemEE
{


    public ItemDiviningRod()
    {
        super();
        this.setHasSubtypes(true);
        this.setUnlocalizedName(Names.Items.DIVINING_ROD);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int facing, float par8, float par9, float par10)
    {
        if (!world.isRemote)
        {
            float value = 0;
            int blockZ;
            int blockY;
            int blockX;

            switch (facing)
            {
                case 0:
                    //UP
                    z = z - 1;
                    x = x - 1;
                    break;
                case 1:
                    //DOWN
                    z = z - 1;
                    x = x - 1;
                    break;
                case 2:
                    //SOUTH
                    y = y - 1;
                    x = x + 1;
                    break;
                case 3:
                    //NORTH
                    y = y - 1;
                    x = x - 1;
                    break;
                case 4:
                    //EAST
                    y = y - 1;
                    z = z - 1;
                    break;
                case 5:
                    //WEST
                    y = y - 1;
                    z = z + 1;
                    break;
            }

            for (int i = 0; i < 3; i++)
            {
                if (facing == 3 || facing == 5)
                {
                    blockZ = z - i;
                } else
                {
                    blockZ = z + i;
                }
                for (int o = 0; o < 3; o++)
                {
                    if (facing == 1)
                    {
                        blockY = y - o;
                    } else
                    {
                        blockY = y + o;
                    }
                    for (int s = 0; s < 3; s++)
                    {
                        if (facing == 2 || facing == 5)
                        {
                            blockX = x - s;
                        } else
                        {
                            blockX = x + s;
                        }
                        Block block = world.getBlock(blockX, blockY, blockZ);
                        LogHelper.info(block.getUnlocalizedName());
                        if (block != Blocks.air)
                        {
                            List<WrappedStack> get = new ArrayList<WrappedStack>();
                            get.clear();
                            get.add(new WrappedStack(block));
                            value = value + EnergyValueHelper.computeEnergyValueFromList(get).getValue();
                        }
                    }
                }
            }
            ChatComponentText T = new ChatComponentText("Value = " + String.valueOf((int) value / 27));
            entityPlayer.addChatMessage(T);
        }
        return super.onItemUse(itemStack, entityPlayer, world, x, z, y, facing, par8, par9, par10);
    }
}
