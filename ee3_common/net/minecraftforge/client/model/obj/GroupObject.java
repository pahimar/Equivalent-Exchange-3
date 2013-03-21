package net.minecraftforge.client.model.obj;

import java.util.ArrayList;

import net.minecraft.client.renderer.Tessellator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GroupObject {

    public String name;
    public ArrayList<Face> faces = new ArrayList<Face>();
    
    public GroupObject() {
        
        name = "";
    }
    
    public GroupObject(String name) {

        this.name = name;
    }

    public void render(Tessellator tessellator, float scale) {

        for (Face face : faces) {
            face.render(tessellator, 0F, scale);
        }
    }

    public void render(Tessellator tessellator, float textureOffset, float scale) {

        for (Face face : faces) {
            face.render(tessellator, textureOffset, scale);
        }
    }
}
