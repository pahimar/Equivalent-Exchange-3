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
        this.legFrontLeft.addBox(-1F, -8F, -1F, 2, 8, 2);
        this.legFrontLeft.setRotationPoint(2, 8, 2);
        this.legFrontLeft.rotateAngleY = ((float) Math.PI / 4F);
        this.legFrontRight = new ModelRenderer(this, 0, 0);
        this.legFrontRight.addBox(-1F, -8F, -1F, 2, 8, 2);
        this.legFrontRight.setRotationPoint(2, 8, 14);
        this.legFrontRight.rotateAngleY = ((float) Math.PI / 4F);
        this.legBackLeft = new ModelRenderer(this, 0, 0);
        this.legBackLeft.addBox(-1F, -8F, -1F, 2, 8, 2);
        this.legBackLeft.setRotationPoint(14, 8, 2);
        this.legBackLeft.rotateAngleY = ((float) Math.PI / 4F);
        this.legBackRight = new ModelRenderer(this, 0, 0);
        this.legBackRight.addBox(-1F, -8F, -1F, 2, 8, 2);
        this.legBackRight.setRotationPoint(14, 8, 14);
        this.legBackRight.rotateAngleY = ((float) Math.PI / 4F);
        
        this.armFrontLeft = new ModelRenderer(this, 8, 0);
        this.armFrontLeft.addBox(-2F, -0.5F, -0.5F, 4, 1, 1);
        this.armFrontLeft.setRotationPoint(4, 3, 4);
        this.armFrontLeft.rotateAngleY = ((float) Math.PI * 3 / 4F);
        this.armFrontRight = new ModelRenderer(this, 8, 0);
        this.armFrontRight.addBox(-2F, -0.5F, -0.5F, 4, 1, 1);
        this.armFrontRight.setRotationPoint(12, 3, 4);
        this.armFrontRight.rotateAngleY = ((float) Math.PI / 4F);
        this.armBackLeft = new ModelRenderer(this, 8, 0);
        this.armBackLeft.addBox(-2F, -0.5F, -0.5F, 4, 1, 1);
        this.armBackLeft.setRotationPoint(12, 3, 12);
        this.armBackLeft.rotateAngleY = ((float) Math.PI * 7 / 4F);
        this.armBackRight = new ModelRenderer(this, 8, 0);
        this.armBackRight.addBox(-2F, -0.5F, -0.5F, 4, 1, 1);
        this.armBackRight.setRotationPoint(4, 3, 12);
        this.armBackRight.rotateAngleY = ((float) Math.PI * 5 / 4F);
        
        this.firePlate = new ModelRenderer(this, 0, 10);
        this.firePlate.addBox(-4F, -0.5F, -4F, 8, 1, 8);
        this.firePlate.setRotationPoint(8, 3, 8);
        this.firePlate.rotateAngleY = ((float) Math.PI / 4F);
        
        this.bowlBottom = new ModelRenderer(this, 0, 19);
        this.bowlBottom.addBox(-8F, -1F, -8F, 16, 1, 16);
        this.bowlBottom.setRotationPoint(8, 9, 8);
        this.bowlBack = new ModelRenderer(this, 0, 36);
        this.bowlBack.addBox(-8F, -3.5F, -0.5F, 16, 7, 1);
        this.bowlBack.setRotationPoint(8F, 12.5F, 0.5F);
        this.bowlFront = new ModelRenderer(this, 0, 36);
        this.bowlFront.addBox(-8F, -3.5F, -0.5F, 16, 7, 1);
        this.bowlFront.setRotationPoint(8, 12.5F, 15.5F);
        this.bowlLeft = new ModelRenderer(this, 0, 44);
        this.bowlLeft.addBox(-0.5F, -3.5F, -7F, 1, 7, 14);
        this.bowlLeft.setRotationPoint(0.5F, 12.5F, 8);
        this.bowlRight = new ModelRenderer(this, 0, 44);
        this.bowlRight.addBox(-0.5F, -3.5F, -7F, 1, 7, 14);
        this.bowlRight.setRotationPoint(15.5F, 12.5F, 8);
        
        this.ashLayer = new ModelRenderer(this, 0, 65);
        this.ashLayer.addBox(-7F, -0.5F, -7F, 14, 1, 14);
        this.ashLayer.setRotationPoint(8, 9, 8);
        this.ashLayer.mirror = true;
    }

    public void render(TileCalcinator calcinator, double x, double y, double z) {
        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);

        GL11.glTranslated(x, y, z);
        ForgeHooksClient.bindTexture(Reference.SPRITE_SHEET_LOCATION + "calcinator.png", 0);
        
        legFrontLeft.render(scale);
        legFrontRight.render(scale);
        legBackLeft.render(scale);
        legBackRight.render(scale);
        armFrontLeft.render(scale);
        armFrontRight.render(scale);
        armBackLeft.render(scale);
        armBackRight.render(scale);
        firePlate.render(scale);
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
