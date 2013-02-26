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
import com.pahimar.ee3.tileentity.TileAludel;

public class BlockAludel extends BlockEE {

    /**
     * Is the random generator used by aludel to drop the inventory contents in
     * random directions.
     */
    private Random rand = new Random();

    public BlockAludel(int id) {

        super(id, Material.rock);
        this.setBlockName(Strings.ALUDEL_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setHardness(5F);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileAludel();
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

        return RenderIds.aludelRenderId;
    }

    @Override
    public int getBlockTextureFromSide(int par1) {

        return 1;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta) {

        dropInventory(world, x, y, z);
        super.breakBlock(world, x, y, z, id, meta);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (player.isSneaking()) {
            return false;
        }
        else {
            if (!world.isRemote) {
                TileAludel tileAludel = (TileAludel) world.getBlockTileEntity(x, y, z);
    
                if (tileAludel != null) {
                    player.openGui(EquivalentExchange3.instance, GuiIds.ALUDEL, world, x, y, z);
                }
            }
    
            return true;
        }
    }

    private void dropInventory(World world, int x, int y, int z) {

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (!(tileEntity instanceof IInventory)) {

            return;
        }

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {

            ItemStack itemStack = inventory.getStackInSlot(i);

            if ((itemStack != null) && (itemStack.stackSize > 0)) {
                float dX = this.rand.nextFloat() * 0.8F + 0.1F;
                float dY = this.rand.nextFloat() * 0.8F + 0.1F;
                float dZ = this.rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, x + dX, y + dY, z + dZ, new ItemStack(itemStack.itemID, itemStack.stackSize, itemStack.getItemDamage()));

                if (itemStack.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = (this.rand.nextGaussian() * factor);
                entityItem.motionY = (this.rand.nextGaussian() * factor + 0.2F);
                entityItem.motionZ = (this.rand.nextGaussian() * factor);
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }
}
