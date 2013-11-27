package com.pahimar.ee3.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.GuiIds;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileAludel;
import com.pahimar.ee3.tileentity.TileEE;
import com.pahimar.ee3.tileentity.TileGlassBell;

public class BlockGlassBell extends BlockEE {

    /**
     * Is the random generator used by glass bell to drop the inventory contents
     * in random directions.
     */
    private Random rand = new Random();

    public BlockGlassBell(int id) {

        super(id, Material.glass);
        this.setUnlocalizedName(Strings.GLASS_BELL_NAME);
        this.setCreativeTab(EquivalentExchange3.tabsEE3);
        this.setHardness(1.0F);
    }

    @Override
    public String getUnlocalizedName() {

        StringBuilder unlocalizedName = new StringBuilder();

        unlocalizedName.append("tile.");
        unlocalizedName.append(Strings.RESOURCE_PREFIX);
        unlocalizedName.append(Strings.GLASS_BELL_NAME);

        return unlocalizedName.toString();
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileGlassBell();
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

        return RenderIds.glassBell;
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
                TileEntity tileEntityGlassBell = world.getBlockTileEntity(x, y, z);
                TileEntity tileEntityAludel = world.getBlockTileEntity(x, y - 1, z);

                if (tileEntityAludel instanceof TileAludel && tileEntityGlassBell instanceof TileGlassBell) {
                    player.openGui(EquivalentExchange3.instance, GuiIds.ALUDEL, world, x, y - 1, z);
                    return true;
                }

                if (tileEntityGlassBell != null) {
                    player.openGui(EquivalentExchange3.instance, GuiIds.GLASS_BELL, world, x, y, z);
                }
            }

            return true;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {

        if (itemStack.hasDisplayName()) {
            ((TileEE) world.getBlockTileEntity(x, y, z)).setCustomName(itemStack.getDisplayName());
        }

        if (world.getBlockTileEntity(x, y - 1, z) != null && world.getBlockTileEntity(x, y - 1, z) instanceof TileAludel) {
            ((TileEE) world.getBlockTileEntity(x, y, z)).setOrientation(ForgeDirection.UP);
        }
        else {
            ((TileEE) world.getBlockTileEntity(x, y, z)).setOrientation(world.getBlockMetadata(x, y, z));
        }

        world.setBlockMetadataWithNotify(x, y, z, 0, 3);
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit, float hitX, float hitY, float hitZ, int metaData) {

        return sideHit;
    }

    /**
     * Ray traces through the blocks collision from start vector to end vector
     * returning a ray trace hit. Args: world, x, y, z, startVec, endVec
     */
    @Override
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec) {

        TileEntity tileEntity = world.getBlockTileEntity(x, y, z);

        if (tileEntity != null) {
            if (tileEntity instanceof TileGlassBell) {

                TileGlassBell tileGlassBell = (TileGlassBell) tileEntity;

                switch (tileGlassBell.getOrientation()) {
                    case DOWN: {
                        this.setBlockBounds(0.125F, 0.33F, 0.125F, 0.875F, 1.0F, 0.875F);
                        break;
                    }
                    case UP: {
                        this.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 0.66F, 0.875F);
                        break;
                    }
                    case NORTH: {
                        this.setBlockBounds(0.125F, 0.125F, 0.33F, 0.875F, 0.875F, 1.0F);
                        break;
                    }
                    case SOUTH: {
                        this.setBlockBounds(0.125F, 0.125F, 0.0F, 0.875F, 0.875F, 0.66F);
                        break;
                    }
                    case EAST: {
                        this.setBlockBounds(0.0F, 0.125F, 0.125F, 0.66F, 0.875F, 0.875F);
                        break;
                    }
                    case WEST: {
                        this.setBlockBounds(0.33F, 0.125F, 0.125F, 1.0F, 0.875F, 0.875F);
                        break;
                    }
                    case UNKNOWN: {
                        break;
                    }
                }
            }
        }

        return super.collisionRayTrace(world, x, y, z, startVec, endVec);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {

        ItemStack itemStack;

        if (world.getBlockTileEntity(x, y, z) instanceof TileGlassBell) {

            TileGlassBell tileGlassBell = (TileGlassBell) world.getBlockTileEntity(x, y, z);

            if (world.getBlockTileEntity(x, y - 1, z) instanceof TileAludel) {
                TileAludel tileAludel = (TileAludel) world.getBlockTileEntity(x, y - 1, z);

                itemStack = tileAludel.getStackInSlot(TileAludel.INPUT_INVENTORY_INDEX);

                if (itemStack != null && itemStack.itemID < 4096)
                    return Block.lightValue[itemStack.itemID];
            }

            itemStack = tileGlassBell.getStackInSlot(TileGlassBell.DISPLAY_SLOT_INVENTORY_INDEX);

            if (itemStack != null && itemStack.itemID < 4096)
                return Block.lightValue[itemStack.itemID];
        }

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
