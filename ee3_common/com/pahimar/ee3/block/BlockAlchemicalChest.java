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
import com.pahimar.ee3.tileentity.TileAlchemicalChest;
import com.pahimar.ee3.tileentity.TileAludel;

public class BlockAlchemicalChest extends BlockEE {

    /**
     * Is the random generator used by alchemical chest to drop the inventory contents in random directions.
     */
    private Random rand = new Random();
    
    public BlockAlchemicalChest(int id) {

        super(id, Material.wood);
        this.setBlockName(Strings.ALCHEMICAL_CHEST_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileAlchemicalChest();
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

        return RenderIds.alchemicalChestRenderId;
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

        if (!world.isRemote) {
            TileAlchemicalChest tileAlchemicalChest = (TileAlchemicalChest) world.getBlockTileEntity(x, y, z);

            if (tileAlchemicalChest != null) {
                player.openGui(EquivalentExchange3.instance, GuiIds.ALCHEMICAL_CHEST, world, x, y, z);
            }
        }

        return true;

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
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound)itemStack.getTagCompound().copy());
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
