package net.minecraftforge.client.model.obj;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TextureCoordinate {

    private float u;
    private float v;
    private float w;

    public float getU() {

        return u;
    }

    public void setU(float u) {

        this.u = u;
    }

    public float getV() {

        return v;
    }

    public void setV(float v) {

        this.v = v;
    }

    public float getW() {

        return w;
    }

    public void setW(float w) {

        this.w = w;
    }

}
