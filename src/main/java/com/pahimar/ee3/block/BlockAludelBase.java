package com.pahimar.ee3.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileGlassBell;

/**
 * Equivalent-Exchange-3
 * 
 * BlockAludel
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class BlockAludelBase extends BlockEE {

    /**
     * Is the random generator used by aludel to drop the inventory contents in
     * random directions.
     */
    private Random rand = new Random();

    public BlockAludelBase(int id) {

        super(id, Material.anvil);
        this.setUnlocalizedName(Strings.ALUDEL_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setBlockBounds(0.0625F, 0.0F, 0.0625F, 0.9375F, 1.0F, 0.9375F);
        this.setHardness(5F);
    }

    @Override
    public String getUnlocalizedName() {

        StringBuilder unlocalizedName = new StringBuilder();

        unlocalizedName.append("tile.");
        unlocalizedName.append(Strings.RESOURCE_PREFIX);
        unlocalizedName.append(Strings.ALUDEL_NAME);

        return unlocalizedName.toString();
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

        return RenderIds.aludelRender;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, int id, int meta) {

        dropInventory(world, x, y, z);

        if (world.getBlockTileEntity(x, y + 1, z) instanceof TileGlassBell) {
            world.markBlockForUpdate(x, y + 1, z);
            world.updateAllLightTypes(x, y + 1, z);
        }

        super.breakBlock(world, x, y, z, id, meta);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

        if (player.isSneaking())
            return false;
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

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {

        super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);

        if (world.getBlockTileEntity(x, y + 1, z) != null && world.getBlockTileEntity(x, y + 1, z) instanceof TileGlassBell) {

            TileGlassBell tileGlassBell = (TileGlassBell) world.getBlockTileEntity(x, y + 1, z);

            tileGlassBell.setOrientation(ForgeDirection.UP);

            if (world.getBlockTileEntity(x, y, z) != null && world.getBlockTileEntity(x, y, z) instanceof TileAludel) {

                TileAludel tileAludel = (TileAludel) world.getBlockTileEntity(x, y, z);

                ItemStack itemStackGlassBell = tileGlassBell.getStackInSlot(TileGlassBell.DISPLAY_SLOT_INVENTORY_INDEX);

                tileGlassBell.setInventorySlotContents(TileGlassBell.DISPLAY_SLOT_INVENTORY_INDEX, null);

                tileAludel.setInventorySlotContents(TileAludel.INPUT_INVENTORY_INDEX, itemStackGlassBell);
            }
        }
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        return 0;
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
