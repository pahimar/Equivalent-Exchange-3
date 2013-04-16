package com.pahimar.ee3.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileGlassDome;


public class BlockGlassDome extends BlockEE {

    /**
     * Is the random generator used by glass dome to drop the inventory contents in
     * random directions.
     */
    private Random rand = new Random();
    
    public BlockGlassDome(int id) {

        super(id, Material.glass);
        this.setUnlocalizedName(Strings.GLASS_DOME_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.66F, 0.875F);
        this.setHardness(1.0F);
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileGlassDome();
    }

    @Override
    public boolean renderAsNormalBlock() {

        return false;
    }

    @Override
    public boolean isOpaqueCube() {

        return false;
    }

    @Override
    public int getRenderType() {

        return RenderIds.glassDomeId;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta) {

        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, id, meta);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (player.isSneaking())
            return false;
        else {
            if (!world.isRemote) {
                TileGlassDome tileGlassDome = (TileGlassDome) world.getBlockTileEntity(x, y, z);

                if (tileGlassDome != null) {
                    player.openGui(EquivalentExchange3.instance, GuiIds.GLASS_DOME, world, x, y, z);
                }
            }

            return true;
        }
    }

    private void dropInventory(World world, int x, int y, int z) {

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory))
            return;

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {

            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0) {
                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, new ItemStack(itemStack.itemID, itemStack.stackSize, itemStack.getItemDamage()));

                if (itemStack.hasTagCompound()) {
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
