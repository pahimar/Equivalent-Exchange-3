package com.pahimar.ee3.block;

import java.util.ArrayList;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.pahimar.ee3.EquivalentExchange3;
import com.pahimar.ee3.lib.RenderIds;
import com.pahimar.ee3.lib.Strings;
import com.pahimar.ee3.tileentity.TileRenderingTank;

public class BlockRenderingTank extends BlockEE {

	public BlockRenderingTank(int id) {

		super(id, Material.anvil);
		this.setUnlocalizedName(Strings.RENDERING_TANK_NAME);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 3.0F, 1.0F);
		this.setCreativeTab(EquivalentExchange3.tabsEE3);
	}

	@Override
	public String getUnlocalizedName() {

		StringBuilder unlocalizedName = new StringBuilder();

		unlocalizedName.append("tile.");
		unlocalizedName.append(Strings.RESOURCE_PREFIX);
		unlocalizedName.append(Strings.RENDERING_TANK_NAME);

		return unlocalizedName.toString();
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

		return RenderIds.renderingTank;
	}

	@Override
	public TileEntity createNewTileEntity(World world) {

		return new TileRenderingTank();
	}
	
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {
		int blockIdentifier = world.getBlockMetadata(x, y, z);
		this.setBlockBounds(0, -blockIdentifier, 0, 1, 3 - blockIdentifier, 1);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {
		super.onBlockPlacedBy(world, x, y, z, entityLiving, itemStack);
		if(world.getBlockId(x, y + 1, z) == 0 && world.getBlockId(x, y + 2, z) == 0){
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
			world.setBlock(x, y + 1, z, this.blockID);
			world.setBlockMetadataWithNotify(x, y + 1, z, 1, 2);
			world.setBlock(x, y + 2, z, this.blockID);
			world.setBlockMetadataWithNotify(x, y + 2, z, 2, 2);
		}else{
			world.setBlockToAir(x, y, z);
			if(itemStack == null){
				itemStack = new ItemStack(this, 0);
			}
			if(itemStack.itemID == this.blockID)
				itemStack.stackSize ++;
			else
				world.spawnEntityInWorld(new EntityItem(world, entityLiving.posX, entityLiving.posY, entityLiving.posZ, new ItemStack(this, 1)));
		}
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z,int tick, EntityPlayer player) {
		if(!player.capabilities.isCreativeMode)
		world.spawnEntityInWorld(new EntityItem(world, x, y, z, new ItemStack(this, 1)));
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int blockID, int metadata) {
		super.breakBlock(world, x, y, z, blockID, metadata);
		switch(metadata){
		case 0:
			if(world.getBlockId(x, y + 1, z) == this.blockID)
			world.setBlockToAir(x, y + 1, z);
			if(world.getBlockId(x, y + 2, z) == this.blockID)
			world.setBlockToAir(x, y + 2, z);
			break;
		case 1:
			if(world.getBlockId(x, y - 1, z) == this.blockID)
			world.setBlockToAir(x, y - 1, z);
			if(world.getBlockId(x, y + 1, z) == this.blockID)
			world.setBlockToAir(x, y + 1, z);
			break;
		case 2: 
			if(world.getBlockId(x, y - 1, z) == this.blockID)
			world.setBlockToAir(x, y - 1, z);
			if(world.getBlockId(x, y - 2, z) == this.blockID)
			world.setBlockToAir(x, y - 2, z);
			break;
		default:
			if(world.getBlockId(x, y + 1, z) == this.blockID)
			world.setBlockToAir(x, y + 1, z);
			if(world.getBlockId(x, y + 2, z) == this.blockID)
			world.setBlockToAir(x, y + 2, z);
		}
	}


}
