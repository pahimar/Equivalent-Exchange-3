package com.pahimar.ee3.item.tool;


import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.pahimar.ee3.creativetab.CreativeTab;
import com.pahimar.ee3.item.IKeyBound;
import com.pahimar.ee3.item.ItemEE;
import com.pahimar.ee3.reference.IChargeable;
import com.pahimar.ee3.reference.Key;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Reference;
import com.pahimar.ee3.reference.Textures;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class DMSword extends ItemSword implements IChargeable, IKeyBound
{
	public DMSword()
	{
		super(DMUtils.MATERIALDARKMATTER);
		
		setUnlocalizedName(Names.Tools.SWORD_DARK_MATTER);
		setCreativeTab(CreativeTab.EE3_TAB);
		
		setNoRepair();
		
		maxStackSize = 1;
	}
	
	//not repairable... because it never breaks
	@Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
        return false;
    }
	
	//do not decrease durability on block destroyed
	@Override
    public boolean onBlockDestroyed(ItemStack p_150894_1_, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_)
    {

        return true;
    }
	
	//thanks to Modular Powersuits for the code this is based off of
	 public boolean hitEntity(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase)
    {
    	
		float damageToDo = Reference.DM_SWORD_BASE_DAMAGE + DMUtils.computeEfficiencyBonus(par1ItemStack.getItemDamage());
		 
    	DamageSource damagesource = DamageSource.causePlayerDamage((EntityPlayer)par3EntityLivingBase);
    	
    	par2EntityLivingBase.attackEntityFrom(damagesource, damageToDo);
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
