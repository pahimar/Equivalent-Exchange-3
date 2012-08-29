package ee3.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;
import net.minecraft.src.Tessellator;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderCalcinator extends TileEntitySpecialRenderer {

	static final float factor = (float) (1.0 / 16.0);

	private final ModelRenderer lowerLeftLeg;
	private final ModelRenderer upperLeftLeg;
	private final ModelRenderer lowerRightLeg;
	private final ModelRenderer upperRightLeg;
	
	private final ModelRenderer supportPlate;
	
	private final ModelRenderer supportArm1;
	
	private ModelBase model = new ModelBase() {};
	
	public RenderCalcinator() {
		upperLeftLeg = new ModelRenderer(model, 0, 0);
		upperLeftLeg.addBox(-1F, -8F, -1F, 2, 16, 2).setRotationPoint(1, 8, 1);
		
		lowerLeftLeg = new ModelRenderer(model, 0, 0);
		lowerLeftLeg.addBox(-1F, -8F, -1F, 2, 16, 2).setRotationPoint(1, 8, 15);
		
		upperRightLeg = new ModelRenderer(model, 0, 0);
		upperRightLeg.addBox(-1F, -8F, -1F, 2, 16, 2).setRotationPoint(15, 8, 1);
		
		lowerRightLeg = new ModelRenderer(model, 0, 0);
		lowerRightLeg.addBox(-1F, -8F, -1F, 2, 16, 2).setRotationPoint(15, 8, 15);
		
		supportPlate = new ModelRenderer(model, 0, 0);
		supportPlate.addBox(-4F, -1F, -4F, 8, 2, 8).setRotationPoint(8, 8, 8);
		
		supportArm1 = new ModelRenderer(model, 0, 0);
		supportArm1.addBox(-5F, -1F, -1F, 5, 2, 2);
		supportArm1.setRotationPoint(1, 8, 1);
		supportArm1.rotateAngleY = ((float)Math.PI * 3F/ 4F);
		
	}
	
	@Override
	public void renderTileEntityAt(TileEntity var1, double x, double y, double z, float f) {
		render(x, y, z);
	}
	
	private void render(double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glTranslated(x, y, z);
		ForgeHooksClient.bindTexture("/hopper.png", 0);
		lowerLeftLeg.render(factor);
		upperLeftLeg.render(factor);
		lowerRightLeg.render(factor);
		upperRightLeg.render(factor);
		supportPlate.render(factor);
		supportArm1.render(factor);

		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

}
