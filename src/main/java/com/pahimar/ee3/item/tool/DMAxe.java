package com.pahimar.ee3.item.tool;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.pahimar.ee3.reference.IChargeable;
import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.creativetab.CreativeTab;
import com.pahimar.ee3.item.IKeyBound;
import com.pahimar.ee3.item.ItemEE;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

//[RANT] If Java had multiple inheritance, I wouldn't have to copy-paste
//the dark-matter-izing override code between DM tools. [/RANT]

public class DMAxe extends ItemAxe implements IChargeable, IKeyBound
{

	public DMAxe()
	{
		super(DMUtils.MATERIALDARKMATTER);
		
		setUnlocalizedName(Names.Tools.AXE_DARK_MATTER);
		setCreativeTab(CreativeTab.EE3_TAB);
		
		setNoRepair();
		
		maxStackSize = 1;
	}
	
	//do not decrease durability on block destroyed
	@Override
    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {

        return true;
    }
	
	//dig speed increases with durability
	@Override
	public float getDigSpeed(ItemStack itemstack, Block block, int metadata)
    {
		
        if(block.getMaterial() != Material.wood && block.getMaterial() != Material.plants && block.getMaterial() != Material.vine)
        {
      		return super.getDigSpeed(itemstack, block, metadata);
        }
        
        //for every charge level, efficiency increases by 4
        return efficiencyOnProperMaterial + DMUtils.computeEfficiencyBonus(itemstack.getItemDamage());
  
    }
	
	//do not damage tool
    public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
        return true;
    }
    
    @Override
    public void doKeyBindingAction(EntityPlayer entityPlayer, ItemStack itemStack, Key key)
    {
    	if(key == Key.CHARGE)
    	{
    		DMUtils.bumpChargeOnItem(itemStack);
    	}
    }
    
    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, ItemEE.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return String.format("item.%s%s", Textures.RESOURCE_PREFIX, ItemEE.getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(ItemEE.getUnwrappedUnlocalizedName(this.getUnlocalizedName()));
    }


}
