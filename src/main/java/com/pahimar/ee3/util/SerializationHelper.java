package com.pahimar.ee3.util;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SerializationHelper
{
    public static String getModListMD5()
    {
        List<String> modList = new ArrayList<String>();

        for (ModContainer modContainer : Loader.instance().getModList())
        {
            modList.add("[" + modContainer.getModId() + "-" + modContainer.getName() + "-" + modContainer.getVersion() + "]");
        }

        Collections.sort(modList);

        StringBuilder modListString = new StringBuilder();
        for (String modEntry : modList)
        {
            modListString.append(modEntry);
        }

        return DigestUtils.md5Hex(modListString.toString());
    }
}
