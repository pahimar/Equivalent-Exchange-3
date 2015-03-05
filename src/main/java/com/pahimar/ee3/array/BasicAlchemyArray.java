package com.pahimar.ee3.array;

import com.pahimar.ee3.api.AlchemyArray;
import com.pahimar.ee3.reference.Names;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.ee3.util.LogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BasicAlchemyArray extends AlchemyArray
{
    public BasicAlchemyArray()
    {
        super(Textures.AlchemyArray.BASIC_ALCHEMY_ARRAY, Names.AlchemyArrays.BASIC_ALCHEMY_ARRAY);
    }

    @Override
    public void onArrayPlacedBy(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, EntityLivingBase entityLiving, ItemStack itemStack)
    {
        LogHelper.info("Array Placed");
    }

    @Override
    public void onArrayActivated(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {
        LogHelper.info("Array Activated");
    }

    @Override
    public void onArrayClicked(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, EntityPlayer entityPlayer)
    {
        LogHelper.info("Array Clicked");
    }

    @Override
    public void onArrayDestroyedByExplosion(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, Explosion explosion)
    {
        LogHelper.info("Array Destroyed By Explosion");
    }

    @Override
    public void onArrayDestroyedByPlayer(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, int metaData)
    {
        LogHelper.info("Array Destroyed By Player");
    }

    @Override
    public void onEntityCollidedWithArray(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, Entity entity)
    {
        LogHelper.info("Array Collided With");
    }

    @Override
    public void onArrayFallenUpon(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, Entity entity, float fallDistance)
    {
        LogHelper.info("Array Fallen Upon");
    }
}
