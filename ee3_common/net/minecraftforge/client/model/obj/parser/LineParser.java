package net.minecraftforge.client.model.obj.parser;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.obj.WavefrontObject;

@SideOnly(Side.CLIENT)
public abstract class LineParser {

    protected String[] words = null;

    public void setWords(String[] words) {

        this.words = words;
    }

    public abstract void parse();

    public abstract void incoporateResults(WavefrontObject wavefrontObject);

}
