package com.pahimar.ee3.api;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

// TODO Switch bare Strings to String constants
public class AlchemyArray implements Comparable<AlchemyArray>
{
    private ResourceLocation texture;
    private String unLocalizedName;
    private String className;

    private AlchemyArray()
    {

    }

    public AlchemyArray(ResourceLocation texture, String unLocalizedName)
    {
        this.texture = texture;
        this.unLocalizedName = unLocalizedName;
    }

    public ResourceLocation getTexture()
    {
        return texture;
    }

    public void setTexture(ResourceLocation texture)
    {
        this.texture = texture;
    }

    public String getUnLocalizedName()
    {
        return unLocalizedName;
    }

    public void setUnLocalizedName(String unLocalizedName)
    {
        this.unLocalizedName = unLocalizedName;
    }

    public String getDisplayName()
    {
        return StatCollector.translateToLocal(unLocalizedName);
    }

    public String getClassName()
    {
        return className;
    }

    public int getChalkCostPerBlock()
    {
        return 1;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound != null)
        {
            if (nbtTagCompound.hasKey("textureDomain") && nbtTagCompound.hasKey("texturePath"))
            {
                this.texture = new ResourceLocation(nbtTagCompound.getString("textureDomain"), nbtTagCompound.getString("texturePath"));
            }
            else
            {
                this.texture = new ResourceLocation("");
            }

            if (nbtTagCompound.hasKey("unLocalizedName"))
            {
                this.unLocalizedName = nbtTagCompound.getString("unLocalizedName");
            }
            else
            {
                this.unLocalizedName = "";
            }

            if (nbtTagCompound.hasKey("className"))
            {
                this.className = nbtTagCompound.getString("className");
            }
            else
            {
                this.className = "";
            }
        }
        else
        {
            this.texture = new ResourceLocation("");
            this.unLocalizedName = "";
            this.className = "";
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        nbtTagCompound.setString("textureDomain", texture.getResourceDomain());
        nbtTagCompound.setString("texturePath", texture.getResourcePath());
        nbtTagCompound.setString("unLocalizedName", unLocalizedName);
        nbtTagCompound.setString("className", this.getClass().getCanonicalName());
    }

    public static AlchemyArray readArrayFromNBT(NBTTagCompound nbtTagCompound)
    {
        AlchemyArray alchemyArray = new AlchemyArray();
        alchemyArray.readFromNBT(nbtTagCompound);
        return alchemyArray;
    }

    public void onArrayPlacedBy(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, EntityLivingBase entityLiving, ItemStack itemStack)
    {

    }

    public void onArrayActivated(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ)
    {

    }

    public void onArrayClicked(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, EntityPlayer entityPlayer)
    {

    }

    public void onArrayDestroyedByExplosion(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, Explosion explosion)
    {

    }

    public void onArrayDestroyedByPlayer(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, int metaData)
    {

    }

    public void onEntityCollidedWithArray(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, Entity entity)
    {

    }

    public void onArrayFallenUpon(World world, int eventX, int eventY, int eventZ, int arrayX, int arrayY, int arrayZ, Entity entity, float fallDistance)
    {

    }

    public void onUpdate(World world, int arrayX, int arrayY, int arrayZ)
    {

    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof AlchemyArray)
        {
            return this.compareTo((AlchemyArray) object) == 0;
        }

        return false;
    }

    @Override
    public int compareTo(AlchemyArray alchemyArray)
    {
        if (this.texture.getResourceDomain().equalsIgnoreCase(alchemyArray.getTexture().getResourceDomain()))
        {
            if (this.texture.getResourcePath().equalsIgnoreCase(alchemyArray.getTexture().getResourcePath()))
            {
                return 0;
            }
            else
            {
                return this.texture.getResourcePath().compareToIgnoreCase(alchemyArray.getTexture().getResourcePath());
            }
        }
        else
        {
            return this.texture.getResourceDomain().compareToIgnoreCase(alchemyArray.getTexture().getResourceDomain());
        }
    }
}
