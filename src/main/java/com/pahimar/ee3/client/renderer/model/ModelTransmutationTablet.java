package com.pahimar.ee3.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTransmutationTablet extends ModelBase
{
    public ModelRenderer transmutationPad;
    public ModelRenderer base;

    public ModelTransmutationTablet()
    {
        this.textureWidth = 128;
        this.textureHeight = 58;
        this.transmutationPad = new ModelRenderer(this, 0, 24);
        this.transmutationPad.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.transmutationPad.addBox(-16.0F, 14.0F, -16.0F, 32, 2, 32, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.base.addBox(-8.0F, 6.0F, -8.0F, 16, 8, 16, 0.0F);
    }

    public void render(float scale)
    {
        this.transmutationPad.render(scale);
        this.base.render(scale);
    }
}
