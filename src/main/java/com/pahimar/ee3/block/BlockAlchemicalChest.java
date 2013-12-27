package com.pahimar.ee3.block;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import java.util.Random;

/**
 * Equivalent-Exchange-3
 * <p/>
 * BlockAlchemicalChest
 *
 * @author pahimar
 */
public class BlockAlchemicalChest extends BlockContainerEE
{

    /**
     * Is the random generator used by alchemical chest to drop the inventory contents in random directions.
     */
    private Random rand = new Random();

    public BlockAlchemicalChest(int id)
    {

        super(id, Material.wood);
        this.setUnlocalizedName(Strings.ALCHEMICAL_CHEST_NAME);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("tile.%s%s", Strings.RESOURCE_PREFIX, Strings.ALCHEMICAL_CHEST_NAME);
    }

    @Override
    public TileEntity createNewTileEntity(World world)
    {

        return new TileAlchemicalChest();
    }

    @Override
    public boolean renderAsNormalBlock()
    {

        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {

        return false;
    }

    @Override
    public int getRenderType()
    {

        return RenderIds.alchemicalChestRender;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta)
    {

        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, id, meta);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {

        if (player.isSneaking())
        {
            return true;
        }
        else if (world.isBlockSolidOnSide(x, y + 1, z, ForgeDirection.DOWN))
        {
            return true;
        }
        else
        {
            if (!world.isRemote)
            {
                TileAlchemicalChest tileAlchemicalChest = (TileAlchemicalChest) world.getBlockTileEntity(x, y, z);

                if (tileAlchemicalChest != null)
                {
                    player.openGui(EquivalentExchange3.instance, GuiIds.ALCHEMICAL_CHEST, world, x, y, z);
                }
            }

            return true;
        }
    }

    private void dropInventory(World world, int x, int y, int z)
    {

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory))
        {
            return;
        }

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {

            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0)
            {
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, new ItemStack(itemStack.itemID, itemStack.stackSize, itemStack.getItemDamage()));

                if (itemStack.hasTagCompound())
                {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }
}
