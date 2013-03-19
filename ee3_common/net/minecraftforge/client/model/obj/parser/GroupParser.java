package net.minecraftforge.client.model.obj.parser;

import net.minecraftforge.client.model.obj.Group;
import net.minecraftforge.client.model.obj.WavefrontObject;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GroupParser extends LineParser {

    Group newGroup = null;

    @Override
    public void incoporateResults(WavefrontObject wavefrontObject) {

        wavefrontObject.getGroups().add(newGroup);
        wavefrontObject.getGroupsDirectAccess().put(newGroup.name, newGroup);

        wavefrontObject.setCurrentGroup(newGroup);
    }

    @Override
    public void parse() {

        String groupName = words[1];
        newGroup = new Group(groupName);
    }

}
