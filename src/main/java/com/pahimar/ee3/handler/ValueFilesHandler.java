package com.pahimar.ee3.handler;

import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.helper.LogHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ConfigCategory;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ValueFilesHandler
{
    public static final String CATEGORY_EMC_VALUES = "emcvalues";
    private static File valueFileFolder;

    private static File getValueFileFolder()
    {
        if (valueFileFolder == null)
        {
            valueFileFolder = new File("config" + File.separator + "ee3" + File.separator + "emc");
            if (!valueFileFolder.exists())
            {
                valueFileFolder.mkdirs();
            }
        }
        return valueFileFolder;
    }

    private static File getValueFile(String modid)
    {
        return new File(getValueFileFolder().getPath() + File.separator + modid + ".emc");
    }

    public static void addFileValue(String modid, ItemStack itemStack, EmcValue emcValue)
    {
        File emcFile = getValueFile(modid);
        Configuration emcConfiguration = new Configuration(emcFile);

        try
        {
            emcConfiguration.load();

            emcConfiguration.get(CATEGORY_EMC_VALUES, itemStack.itemID + "|" + itemStack.getItemDamage(), emcValue.getValue()).set(emcValue.getValue());
        }
        catch (Exception e)
        {
            LogHelper.severe(String.format("Unable to save EMC Value(%s) for (%s)!", emcValue.getValue(), itemStack.getDisplayName()));
        }
        finally
        {
            emcConfiguration.save();
        }
    }

    public static EmcValue getFileValue(String modid, ItemStack itemStack)
    {
        File emcFile = getValueFile(modid);
        Configuration emcConfiguration = new Configuration(emcFile);

        EmcValue emcValue = null;

        try
        {
            emcConfiguration.load();

            Float value = (float)emcConfiguration.get(CATEGORY_EMC_VALUES, itemStack.itemID + "|" + itemStack.getItemDamage(), 0.0F).getDouble(0.0F);
            emcValue = new EmcValue(value);
        }
        catch (Exception e)
        {
            LogHelper.severe(String.format("Unable to load EMC Value for (%s)!",  itemStack.getDisplayName()));
        }
        finally
        {
            emcConfiguration.save();
        }
        return emcValue;
    }

    public static Map<WrappedStack, EmcValue> getFileValues(File emcFile)
    {
        Map<WrappedStack, EmcValue> valueMap = new HashMap<WrappedStack, EmcValue>();

        try
        {
            Configuration emcConfiguration = new Configuration(emcFile);

            ConfigCategory category = emcConfiguration.getCategory(CATEGORY_EMC_VALUES);

            for(Map.Entry<String, Property> entry : category.entrySet())
            {
                ItemStack stack = null;
                String[] stackStrings = entry.getKey().split("\\|");
                if (stackStrings.length >= 2)
                {
                    int id = Integer.parseInt(stackStrings[0]);
                    int metadata = Integer.parseInt(stackStrings[1]);

                    stack = new ItemStack(id, 1, metadata);
                }

                float value = (float)entry.getValue().getDouble(0.0F);
                EmcValue emcValue = new EmcValue(value);

                if (stack != null && emcValue.getValue() != 0.0F)
                {
                    valueMap.put(new WrappedStack(stack), emcValue);
                }
            }
        }
        catch (Exception e)
        {
            LogHelper.severe("Unable to load EMC Value file!");
        }
        return valueMap;
    }

    public static Map<WrappedStack, EmcValue> getFileValues(String modid)
    {
        File emcFile = getValueFile(modid);
        return getFileValues(getValueFile(modid));
    }

    public static Map<WrappedStack, EmcValue> getFileValues()
    {
        Map<WrappedStack, EmcValue> valueMap = new HashMap<WrappedStack, EmcValue>();

        for (File file : getValueFileFolder().listFiles())
        {
            if (file.getName().endsWith(".emc"))
            {
                valueMap.putAll(getFileValues(file));
            }
        }
        return valueMap;
    }
}
