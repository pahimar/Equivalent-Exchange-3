package ee3.client.render;

import org.lwjgl.opengl.GL11;

import ee3.common.lib.Reference;

import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;
import net.minecraft.src.Tessellator;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderCalcinator extends TileEntitySpecialRenderer {

	private ModelBase model = new ModelBase() {};
	static final float factor = (float) (1.0 / 16.0);

	/* The four support legs of the Calcinator */
	private final ModelRenderer[] legs = new ModelRenderer[4];
	
	/* The four support arms for the fire plateof  the Calcinator */
	private final ModelRenderer[] supportArms = new ModelRenderer[4];
	
	/* The five faces (four sides and the bottom) of the Calcinator bowl */
	private final ModelRenderer[] bowlParts = new ModelRenderer[5];
	
	/*  */
	private final ModelRenderer firePlate;
	private final ModelRenderer ashLayer;
	
	public RenderCalcinator() {
		legs[0] = new ModelRenderer(model, 0, 0);
		legs[0].addBox(-1F, -8F, -1F, 2, 8, 2).setRotationPoint(2, 8, 2);
		legs[0].rotateAngleY = ((float)Math.PI / 4F);
		legs[1] = new ModelRenderer(model, 0, 0);
		legs[1].addBox(-1F, -8F, -1F, 2, 8, 2).setRotationPoint(2, 8, 14);
		legs[1].rotateAngleY = ((float)Math.PI / 4F);
		legs[2] = new ModelRenderer(model, 0, 0);
		legs[2].addBox(-1F, -8F, -1F, 2, 8, 2).setRotationPoint(14, 8, 2);
		legs[2].rotateAngleY = ((float)Math.PI / 4F);
		legs[3] = new ModelRenderer(model, 0, 0);
		legs[3].addBox(-1F, -8F, -1F, 2, 8, 2).setRotationPoint(14, 8, 14);
		legs[3].rotateAngleY = ((float)Math.PI / 4F);
		
		firePlate = new ModelRenderer(model, 0, 0);
		firePlate.addBox(-3.5F, -1F, -3.5F, 8, 2, 8).setRotationPoint(8, 3, 8);
		firePlate.rotateAngleY = ((float)Math.PI / 4F);
		
		supportArms[0] = new ModelRenderer(model, 0, 0);
		supportArms[0].addBox(-5F, -1F, -1F, 4, 2, 2).setRotationPoint(2, 3, 2);
		supportArms[0].rotateAngleY = ((float)Math.PI * 3 / 4F);
		supportArms[1] = new ModelRenderer(model, 0, 0);
		supportArms[1].addBox(-5F, -1F, -1F, 4, 2, 2).setRotationPoint(14, 3, 2);
		supportArms[1].rotateAngleY = ((float)Math.PI / 4F);
		supportArms[2] = new ModelRenderer(model, 0, 0);
		supportArms[2].addBox(-5F, -1F, -1F, 4, 2, 2).setRotationPoint(14, 3, 14);
		supportArms[2].rotateAngleY = ((float)Math.PI * 7 / 4F);
		supportArms[3] = new ModelRenderer(model, 0, 0);
		supportArms[3].addBox(-5F, -1F, -1F, 4, 2, 2).setRotationPoint(2, 3, 14);
		supportArms[3].rotateAngleY = ((float)Math.PI * 5 / 4F);
		
		bowlParts[0] = new ModelRenderer(model, 0, 0);
		bowlParts[0].addBox(-8F, -4F, -1F, 16, 8, 2).setRotationPoint(8, 12, 1);
		bowlParts[1] = new ModelRenderer(model, 0, 0);
		bowlParts[1].addBox(-8F, -4F, -1F, 16, 8, 2).setRotationPoint(8, 12, 15);
		bowlParts[2] = new ModelRenderer(model, 0, 0);
		bowlParts[2].addBox(-1F, -4F, -6F, 2, 8, 12).setRotationPoint(1, 12, 8);
		bowlParts[3] = new ModelRenderer(model, 0, 0);
		bowlParts[3].addBox(-1F, -4F, -6F, 2, 8, 12).setRotationPoint(15, 12, 8);
		bowlParts[4] = new ModelRenderer(model, 0, 0);
		bowlParts[4].addBox(-6F, -1F, -6F, 12, 1, 12).setRotationPoint(8, 9, 8);
		
		ashLayer = new ModelRenderer(model, 0, 0);
		ashLayer.addBox(-6F, 0F, -6F, 12, 1, 12).setRotationPoint(8, 9, 8);
		
	}
	
	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
		render(x, y, z);
	}
	
	private void render(double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glTranslated(x, y, z);
		ForgeHooksClient.bindTexture(Reference.SPRITE_SHEET_LOCATION + "calcinator.png", 0);
		
		firePlate.render(factor);
		
		for (ModelRenderer leg : legs) {
			leg.render(factor);
		}
		
		for (ModelRenderer supportArm : supportArms) {
			supportArm.render(factor);
		}

		for (ModelRenderer bowlPart : bowlParts) {
			bowlPart.render(factor);
		}
		
		ashLayer.render(factor);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
