package ee3.client.render;

import org.lwjgl.opengl.GL11;

import ee3.common.lib.Reference;
import ee3.common.tile.TileCalcinator;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;
import net.minecraftforge.client.ForgeHooksClient;

public class ModelCalcinator extends ModelBase {
    
    private static final int TEXTURE_HEIGHT = 128;
    private static final int TEXTURE_WIDTH = 128;
    
    private ModelRenderer legFrontLeft, legFrontRight, legBackLeft, legBackRight;
    private ModelRenderer armFrontLeft, armFrontRight, armBackLeft, armBackRight;
    private ModelRenderer firePlate;
    private ModelRenderer bowlBottom, bowlLeft, bowlRight, bowlFront, bowlBack;
    private ModelRenderer ashLayer;
    private float scale;
    
    public ModelCalcinator(float scale) {
        this.scale = scale;
        this.textureHeight = TEXTURE_HEIGHT;
        this.textureWidth = TEXTURE_WIDTH;
        
        this.legFrontLeft = new ModelRenderer(this, 0, 0);
        this.legFrontLeft.addBox(-1F, -8F, -1F, 2, 8, 2, scale);
        this.legFrontLeft.setRotationPoint(-9F, 4, 0);
        this.legFrontRight = new ModelRenderer(this, 0, 0);
        this.legFrontRight.addBox(-1F, -8F, -1F, 2, 8, 2, scale);
        this.legFrontRight.setRotationPoint(9F, 4, 0);
        this.legBackLeft = new ModelRenderer(this, 0, 0);
        this.legBackLeft.addBox(-1F, -8F, -1F, 2, 8, 2, scale);
        this.legBackLeft.setRotationPoint(0, 4, -9F);
        this.legBackRight = new ModelRenderer(this, 0, 0);
        this.legBackRight.addBox(-1F, -8F, -1F, 2, 8, 2, scale);
        this.legBackRight.setRotationPoint(0, 4, 9F);
        
        this.armFrontLeft = new ModelRenderer(this, 0, 10);
        this.armFrontLeft.addBox(-2F, -1F, -1F, 4, 2, 2, scale);
        this.armFrontLeft.setRotationPoint(6, 0, 0);
        this.armFrontRight = new ModelRenderer(this, 0, 10);
        this.armFrontRight.addBox(-2F, -1F, -1F, 4, 2, 2, scale);
        this.armFrontRight.setRotationPoint(-6, 0, 0);
        this.armBackLeft = new ModelRenderer(this, 12, 10);
        this.armBackLeft.addBox(-1F, -1F, -2.0F, 2, 2, 4, scale);
        this.armBackLeft.setRotationPoint(0, 0, 6);
        this.armBackRight = new ModelRenderer(this, 12, 10);
        this.armBackRight.addBox(-1F, -1F, -2.0F, 2, 2, 4, scale);
        this.armBackRight.setRotationPoint(0, 0, -6);
        
        this.firePlate = new ModelRenderer(this, 8, 0);
        this.firePlate.addBox(-4F, -1F, -4F, 8, 2, 8, scale);
        this.firePlate.setRotationPoint(8, 4, 8);
        this.firePlate.rotateAngleY = ((float) Math.PI / 4F);
        
        this.firePlate.addChild(this.legFrontLeft);        
        this.firePlate.addChild(this.legFrontRight);
        this.firePlate.addChild(this.legBackLeft);
        this.firePlate.addChild(this.legBackRight);
        this.firePlate.addChild(this.armFrontLeft);
        this.firePlate.addChild(this.armFrontRight);
        this.firePlate.addChild(this.armBackLeft);
        this.firePlate.addChild(this.armBackRight);
        
        
        this.bowlBottom = new ModelRenderer(this, 0, 19);
        this.bowlBottom.addBox(-8F, -1F, -8F, 16, 1, 16, scale);
        this.bowlBottom.setRotationPoint(8, 9, 8);
        this.bowlBack = new ModelRenderer(this, 0, 36);
        this.bowlBack.addBox(-8F, -3.5F, -0.5F, 16, 7, 1, scale);
        this.bowlBack.setRotationPoint(8F, 12.5F, 0.5F);
        this.bowlFront = new ModelRenderer(this, 0, 36);
        this.bowlFront.addBox(-8F, -3.5F, -0.5F, 16, 7, 1, scale);
        this.bowlFront.setRotationPoint(8, 12.5F, 15.5F);
        this.bowlLeft = new ModelRenderer(this, 0, 44);
        this.bowlLeft.addBox(-0.5F, -3.5F, -7F, 1, 7, 14, scale);
        this.bowlLeft.setRotationPoint(0.5F, 12.5F, 8);
        this.bowlRight = new ModelRenderer(this, 0, 44);
        this.bowlRight.addBox(-0.5F, -3.5F, -7F, 1, 7, 14, scale);
        this.bowlRight.setRotationPoint(15.5F, 12.5F, 8);
        
        this.ashLayer = new ModelRenderer(this, 0, 65);
        this.ashLayer.addBox(-7F, -0.5F, -7F, 14, 1, 14, scale);
        this.ashLayer.setRotationPoint(8, 9, 8);
        this.ashLayer.mirror = true;
    }

    public void render(TileCalcinator calcinator, double x, double y, double z) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslated(x, y, z);
        ForgeHooksClient.bindTexture(Reference.SPRITE_SHEET_LOCATION + "calcinator.png", 0);
        
        // TODO Make a fire pot instead of a plate
        firePlate.render(scale);
        
        // TODO Merge this together better
        bowlBottom.render(scale);
        bowlBack.render(scale);
        bowlFront.render(scale);
        bowlLeft.render(scale);
        bowlRight.render(scale);
        
        ashLayer.render(scale);
        

        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    
}
