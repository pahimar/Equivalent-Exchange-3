package ee3.client.core;

import net.minecraft.src.RenderEngine;
import net.minecraft.src.forge.MinecraftForgeClient;
import cpw.mods.fml.client.FMLTextureFX;
import ee3.block.ModBlocks;
import ee3.lib.Reference;

/**
 * TODO Class Description 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 *
 * Based heavily off of TextureWaterFX (like, 99%)
 */
public class TextureRedWaterFX extends FMLTextureFX {

    protected float[] red = new float[256];
    protected float[] green = new float[256];
    protected float[] blue = new float[256];
    protected float[] alpha = new float[256];
    private int tickCounter = 0;
	
	public TextureRedWaterFX() {
		super(ModBlocks.redWaterStill.blockIndexInTexture);
		setup();
	}

	@Override
	public void setup() {
        super.setup();
        red = new float[tileSizeSquare];
        green = new float[tileSizeSquare];
        blue = new float[tileSizeSquare];
        alpha = new float[tileSizeSquare];
        tickCounter = 0;
    }
	
	@Override
	public void bindImage(RenderEngine renderengine) {
		MinecraftForgeClient.bindTexture(Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET);
	}
	
	public void onTick() {
        ++this.tickCounter;
        int var1;
        int var2;
        float var3;
        int var5;
        int var6;

        for (var1 = 0; var1 < tileSizeBase; ++var1) {
            for (var2 = 0; var2 < tileSizeBase; ++var2) {
                var3 = 0.0F;

                for (int var4 = var1 - 1; var4 <= var1 + 1; ++var4) {
                    var5 = var4 & tileSizeMask;
                    var6 = var2 & tileSizeMask;
                    var3 += this.red[var5 + var6 * tileSizeBase];
                }

                this.green[var1 + var2 * tileSizeBase] = var3 / 3.3F + this.blue[var1 + var2 * tileSizeBase] * 0.8F;
            }
        }

        for (var1 = 0; var1 < tileSizeBase; ++var1) {
            for (var2 = 0; var2 < tileSizeBase; ++var2) {
                this.blue[var1 + var2 * tileSizeBase] += this.alpha[var1 + var2 * tileSizeBase] * 0.05F;

                if (this.blue[var1 + var2 * tileSizeBase] < 0.0F) {
                    this.blue[var1 + var2 * tileSizeBase] = 0.0F;
                }

                this.alpha[var1 + var2 * tileSizeBase] -= 0.1F;

                if (Math.random() < 0.05D) {
                    this.alpha[var1 + var2 * tileSizeBase] = 0.5F;
                }
            }
        }

        float[] var12 = this.green;
        this.green = this.red;
        this.red = var12;

        for (var2 = 0; var2 < tileSizeSquare; ++var2) {
            var3 = this.red[var2];

            if (var3 > 1.0F) {
                var3 = 1.0F;
            }

            if (var3 < 0.0F) {
                var3 = 0.0F;
            }

            float var13 = var3 * var3;
            var5 = (int)(32.0F + var13 * 32.0F);
            var6 = (int)(50.0F + var13 * 64.0F);
            int var7 = 255;
            int var8 = (int)(146.0F + var13 * 50.0F);

            if (this.anaglyphEnabled) {
                int var9 = (var5 * 30 + var6 * 59 + var7 * 11) / 100;
                int var10 = (var5 * 30 + var6 * 70) / 100;
                int var11 = (var5 * 30 + var7 * 70) / 100;
                var5 = var9;
                var6 = var10;
                var7 = var11;
            }

            this.imageData[var2 * 4 + 0] = (byte)var5;
            this.imageData[var2 * 4 + 1] = (byte)0;
            this.imageData[var2 * 4 + 2] = (byte)0;
            this.imageData[var2 * 4 + 3] = (byte)192;
        }
    }
}
