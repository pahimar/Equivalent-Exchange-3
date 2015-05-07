package com.pahimar.ee3.api.array;

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
    private String unlocalizedName;
    private String className;
    private int lightLevel;
    private int chalkPerBlockCost;

    private AlchemyArray()
    {

    }

    public AlchemyArray(ResourceLocation texture, String unlocalizedName)
    {
        this.texture = texture;
        this.unlocalizedName = unlocalizedName;
        this.chalkPerBlockCost = 1;
        this.lightLevel = 0;
    }

    public ResourceLocation getTexture()
    {
        return texture;
    }

    public void setTexture(ResourceLocation texture)
    {
        this.texture = texture;
    }

    public String getUnlocalizedName()
    {
        return unlocalizedName;
    }

    public void setUnlocalizedName(String unlocalizedName)
    {
        this.unlocalizedName = unlocalizedName;
    }

    public String getDisplayName()
    {
        return StatCollector.translateToLocal(unlocalizedName);
    }

    public int getChalkCostPerBlock()
    {
        return chalkPerBlockCost;
    }

    public void setChalkPerBlockCost(int chalkPerBlockCost)
    {
        this.chalkPerBlockCost = Math.abs(chalkPerBlockCost);
    }

    public int getLightLevel()
    {
        return lightLevel;
    }

    public String getClassName()
    {
        return className;
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

            if (nbtTagCompound.hasKey("unlocalizedName"))
            {
                this.unlocalizedName = nbtTagCompound.getString("unlocalizedName");
            }
            else
            {
                this.unlocalizedName = "";
            }

            if (nbtTagCompound.hasKey("className"))
            {
                this.className = nbtTagCompound.getString("className");
            }
            else
            {
                this.className = "";
            }

            if (nbtTagCompound.hasKey("lightLevel"))
            {
                this.lightLevel = nbtTagCompound.getInteger("lightLevel");
            }
            else
            {
                this.lightLevel = 0;
            }
        }
        else
        {
            this.texture = new ResourceLocation("");
            this.unlocalizedName = "";
            this.className = "";
            this.lightLevel = 0;
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        nbtTagCompound.setString("textureDomain", texture.getResourceDomain());
        nbtTagCompound.setString("texturePath", texture.getResourcePath());
        nbtTagCompound.setString("unlocalizedName", unlocalizedName);
        nbtTagCompound.setString("className", this.getClass().getCanonicalName());
        nbtTagCompound.setInteger("lightLevel", lightLevel);
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

    public void onUpdate(World world, int arrayX, int arrayY, int arrayZ, int tickCount)
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
