package com.pahimar.ee3.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileCalcinator;

/**
 * BlockCalcinator
 * 
 * Block class for the Calcinator
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockCalcinator extends BlockEE {

    public BlockCalcinator(int id) {

        super(id, Material.rock);
        this.setBlockName(Strings.CALCINATOR_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setHardness(5F);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileCalcinator();
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

        return RenderIds.calcinatorRenderId;
    }

    @Override
    public int getBlockTextureFromSide(int par1) {

        return 1;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta) {
        
        super.breakBlock(world, x, y, z, id, meta);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (!world.isRemote) {
            TileCalcinator tileCalcinator = (TileCalcinator) world.getBlockTileEntity(x, y, z);

            if (tileCalcinator != null) {
                player.openGui(EquivalentExchange3.instance, GuiIds.CALCINATOR, world, x, y, z);
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
                
            }
        }
    }

}
