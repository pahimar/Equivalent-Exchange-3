package com.pahimar.ee3.block;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;

import org.junit.Test;

import com.pahimar.ee3.EquivalentExchange3;

public class RenderingTankTest {
	private float minY, maxY;
	@Test
	public void testSetBlockBoundsBasedOnStateSetsMixAndMaxYGivenMetadataZero(){
		this.setBlockBoundsBasedOnState(this, 0, 0, 0);
		assertEquals(0.0F, this.minY, 0.1F);
		assertEquals(3.0F, this.maxY, 0.1F);
	}
	@Test
	public void testSetBlockBoundsBasedOnStateSetsMixAndMaxYGivenMetadataOne(){
		this.setBlockBoundsBasedOnState(this, 0, 1, 0);
		assertEquals(-1.0F, this.minY, 0.1F);
		assertEquals(2.0F, this.maxY, 0.1F);
	}
	@Test
	public void testSetBlockBoundsBasedOnStateSetsMixAndMaxYGivenMetadataTwo(){
		this.setBlockBoundsBasedOnState(this, 0, 2, 0);
		assertEquals(-2.0F, this.minY, 0.1F);
		assertEquals(1.0F, this.maxY, 0.1F);
	}
	@Test
	public void testOnBlockPlacedBySetsBlock2GivenData(){
		World world = new World();
		BlockRenderingTank block = new BlockRenderingTank();
		block.onBlockPlacedBy(world, 0, 0, 0, null, null);
		assertEquals(1700, world.blockid2);
	}
	@Test
	public void testOnBlockPlacedBySetsBlockMetaGivenData(){
		World world = new World();
		BlockRenderingTank block = new BlockRenderingTank();
		block.onBlockPlacedBy(world, 0, 0, 0, null, null);
		assertEquals(0, world.blockdata0);
		assertEquals(1, world.blockdata1);
		assertEquals(2, world.blockdata2);
	}
	@Test
	public void testOnBlockPlacedBySetsItemStackIDGivienBlockInTheWay(){
		World world = new World();
		world.setreturnvalueforGetBlockId(1);
		BlockRenderingTank block = new BlockRenderingTank();
		ItemStack stack = new ItemStack(block, 1);
		block.onBlockPlacedBy(world, 0, 0, 0, new EntityLivingBase(world, 0,10,0), stack);
		assertTrue(!stack.equals(new ItemStack(block, 1)));
		assertEquals(2, stack.stackSize);
	}
	@Test
	public void testOnBlockPlacedBySpawnEntityGivienBlockInTheWay(){
		World world = new World();
		world.setreturnvalueforGetBlockId(1);
		BlockRenderingTank block = new BlockRenderingTank();
		ItemStack stack = new ItemStack(new BlockTwo(), 1);
		block.onBlockPlacedBy(world, 0, 0, 0, new EntityLivingBase(world, 0,10,0), stack);
		assertNotEquals(null, world.item);
	}
	@Test
	public void testOnBlockPlacedBySetsBlock1GivenData(){
		World world = new World();
		BlockRenderingTank block = new BlockRenderingTank();
		block.onBlockPlacedBy(world, 0, 0, 0, null, null);
		assertEquals(1700, world.blockid1);
	}
	@Test
	public void testOnBlockPlacedByDoseNothingGivenDataBlockInWayAndNull(){
		World world = new World();
		world.setreturnvalueforGetBlockId(1);
		BlockRenderingTank block = new BlockRenderingTank();
		ItemStack item = null;
		block.onBlockPlacedBy(world, 0, 0, 0, null, item);
		assertEquals(null, item);
	}
	@Test
	public void testOnBlockPlacedBySetsBlock0GivenData(){
		World world = new World();
		BlockRenderingTank block = new BlockRenderingTank();
		block.onBlockPlacedBy(world, 0, 0, 0, null, null);
		assertEquals(0, world.blockid0);
	}
	public void setBlockBounds(float minX, float minY, float minZ, float maxX, float maxY,float maxZ){
		this.minY = minY;
		this.maxY = maxY;
	}
	public int getBlockMetadata(int x, int y, int z){
		return (int)y;
	}
	public void setBlockBoundsBasedOnState(RenderingTankTest world, int x, int y, int z) {
		int blockIdentifier = world.getBlockMetadata(x, y, z);
		this.setBlockBounds(0, -blockIdentifier, 0, 1, 3 - blockIdentifier, 1);
	}
	private class BlockRenderingTank extends BlockEE{
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
	}
	private class World{
		public EntityItem item = null;
		public int blockid0 = 0;
		public int blockdata0 = 0;
		public int blockid1 = 0;
		public int blockdata1 = 0;
		public int blockid2 = 0;
		public int blockdata2 = 0;
		public int returnval = 0;
		public void setreturnvalueforGetBlockId(int returnval){
			this.returnval = returnval;
		}
		public int getBlockId(int x,int y,int z){
			return this.returnval;
		}
		public void spawnEntityInWorld(EntityItem entity){
			this.item = entity;
		}
		public void setBlockMetadataWithNotify(int x, int y, int z, int metadata, int flag){
			if(y == 0){
				this.blockdata0 = metadata;
			}
			if(y == 1){
				this.blockdata1 = metadata;
			}
			if(y == 2){
				this.blockdata2 = metadata;
			}
		}
		public void setBlock(int x, int y, int z, int blockid){
			if(y == 0){
				this.blockid0 = blockid;
			}
			if(y == 1){
				this.blockid1 = blockid;
			}
			if(y == 2){
				this.blockid2 = blockid;
			}
		}
		public void setBlockToAir(int x, int y, int z){

		}
	}
	public class EntityItem{
		public double x,y,z;
		public ItemStack item;
		public EntityItem(World world, double x, double y, double z, ItemStack item){
			this.x = x;
			this.y = y;
			this.z = z;
			this.item = item;
		}
	}
	private class BlockEE{
		public int blockID = 1700;
		public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack itemStack) {

		}
	}
	private class EntityLivingBase{
		private double posX, posY, posZ;
		public EntityLivingBase(World world, double x, double y, double z){
			this.posX = x;
			this.posY = y;
			this.posZ = z;
		}
	}
	private class BlockTwo extends BlockEE{
		public BlockTwo(){
			this.blockID = 100;
		}
	}
	private class ItemStack{
		public BlockEE block;
		public int stackSize = 1;
		public int itemID = 0;
		public ItemStack(BlockEE block, int amount){
			this.block = block;
			this.stackSize = amount;
			this.itemID = block.blockID;
		}
	}

}
