package com.pahimar.ee3.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class AlchemyArray implements Comparable<AlchemyArray>
{
    private ResourceLocation texture;
    private String unLocalizedName;

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

    public String getUnLocalizedName()
    {
        return unLocalizedName;
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound != null) {
            if (nbtTagCompound.hasKey("textureDomain") && nbtTagCompound.hasKey("texturePath")) {
                this.texture = new ResourceLocation(nbtTagCompound.getString("textureDomain"), nbtTagCompound.getString("texturePath"));
            } else {
                this.texture = new ResourceLocation("");
            }

            if (nbtTagCompound.hasKey("unLocalizedName")) {
                this.unLocalizedName = nbtTagCompound.getString("unLocalizedName");
            } else {
                this.unLocalizedName = "";
            }
        } else
        {
            this.texture = new ResourceLocation("");
            this.unLocalizedName = "";
        }
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.setString("textureDomain", texture.getResourceDomain());
        nbtTagCompound.setString("texturePath", texture.getResourcePath());
        nbtTagCompound.setString("unLocalizedName", unLocalizedName);
    }

    public static AlchemyArray readGlyphFromNBT(NBTTagCompound nbtTagCompound) {
        AlchemyArray alchemyArray = new AlchemyArray();
        alchemyArray.readFromNBT(nbtTagCompound);
        return alchemyArray;
    }

    public void onAlchemyArrayActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int sideHit, float hitX, float hitY, float hitZ) {

    }

    @Override
    public String toString() {
        return StatCollector.translateToLocal(unLocalizedName);
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
            } else {
                return this.texture.getResourcePath().compareToIgnoreCase(alchemyArray.getTexture().getResourcePath());
            }
        }
        else
        {
            return this.texture.getResourceDomain().compareToIgnoreCase(alchemyArray.getTexture().getResourceDomain());
        }
    }
}
