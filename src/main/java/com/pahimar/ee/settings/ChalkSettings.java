package com.pahimar.ee.settings;

import com.pahimar.ee.api.array.AlchemyArrayRegistryProxy;
import com.pahimar.ee.util.INBTTaggable;
import net.minecraft.nbt.NBTTagCompound;

// TODO Set the NBT tag names to constants
public class ChalkSettings implements INBTTaggable
{
    private int index;
    private int size;
    private int rotation;

    private final int MAX_SIZE = 5;

    public ChalkSettings()
    {
        this(0, 1, 0);
    }

    public ChalkSettings(int index, int size, int rotation)
    {
        this.index = index;
        this.size = size;
        this.rotation = rotation;
    }

    public int getIndex()
    {
        return index;
    }

    public void setIndex(int index)
    {
        this.index = index;

        if (this.index < 0)
        {
            this.index = 0;
        }
        else if (this.index >= AlchemyArrayRegistryProxy.getAlchemyArrays().size())
        {
            this.index = AlchemyArrayRegistryProxy.getAlchemyArrays().size() - 1;
        }
    }

    public void incrementIndex()
    {
        index += 1;

        if (index >= AlchemyArrayRegistryProxy.getAlchemyArrays().size())
        {
            index = 0;
        }
    }

    public void decrementIndex()
    {
        index -= 1;

        if (index < 0)
        {
            this.index = AlchemyArrayRegistryProxy.getAlchemyArrays().size() - 1;
        }
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        if (size < 1)
        {
            this.size = 1;
        }
        else if (size > MAX_SIZE)
        {
            this.size = MAX_SIZE;
        }
        else
        {
            this.size = size;
        }
    }

    public void incrementSize()
    {
        if (size < MAX_SIZE)
        {
            size += 1;
        }
    }

    public void decrementSize()
    {
        if (size > 1)
        {
            size -= 1;
        }
    }

    public int getRotation()
    {
        return rotation;
    }

    public void setRotation(int rotation)
    {
        if (rotation < 0)
        {
            this.rotation = 0;
        }
        else
        {
            this.rotation = rotation % 4;
        }
    }

    public void rotateClockwise()
    {
        this.rotation = (rotation + 1) % 4;
    }

    public void rotateCounterClockwise()
    {
        this.rotation -= 1;

        if (this.rotation < 0)
        {
            this.rotation = 3;
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        if (nbtTagCompound != null && nbtTagCompound.hasKey("chalk_settings") && nbtTagCompound.getTag("chalk_settings").getId() == (byte) 10)
        {
            NBTTagCompound chalkSettings = nbtTagCompound.getCompoundTag("chalk_settings");
            if (chalkSettings.hasKey("index"))
            {
                this.index = chalkSettings.getInteger("index");

                if (this.index < 0 || this.index >= AlchemyArrayRegistryProxy.getAlchemyArrays().size())
                {
                    this.index = 0;
                }
            }
            else
            {
                this.index = 0;
            }

            if (chalkSettings.hasKey("size"))
            {
                this.size = chalkSettings.getInteger("size");

                if (this.size < 1)
                {
                    this.size = 1;
                }
                else if (this.size > MAX_SIZE)
                {
                    this.size = MAX_SIZE;
                }
            }
            else
            {
                this.size = 1;
            }

            if (chalkSettings.hasKey("rotation"))
            {
                this.rotation = chalkSettings.getInteger("rotation");

                if (this.rotation < 0)
                {
                    this.rotation = 0;
                }
                else
                {
                    this.rotation = this.rotation % 4;
                }
            }
            else
            {
                this.rotation = 0;
            }
        }
        else
        {
            this.index = 0;
            this.size = 1;
            this.rotation = 0;
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        NBTTagCompound chalkSettings = new NBTTagCompound();
        chalkSettings.setInteger("index", index);
        chalkSettings.setInteger("size", size);
        chalkSettings.setInteger("rotation", rotation);
        nbtTagCompound.setTag("chalk_settings", chalkSettings);
    }

    @Override
    public String getTagLabel()
    {
        return this.getClass().getName();
    }
}
