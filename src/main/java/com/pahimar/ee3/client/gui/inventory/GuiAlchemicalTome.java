package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerAlchemicalTome;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import com.pahimar.repackage.cofh.lib.gui.element.ElementButton;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.InventoryBasic;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiAlchemicalTome extends GuiBase
{
    private InventoryPlayer inventoryPlayer;
    public static InventoryBasic tempInventory = new InventoryBasic("tmp", true, 81);

    private ElementButton prevPageButton;
    private ElementButton nextPageButton;

    private ElementButton commonAlchemyRibbon;
    private ElementButton forbiddenAlchemyRibbon;
    private ElementButton alkahestryRibbon;

    public GuiAlchemicalTome(EntityPlayer entityPlayer)
    {
        super(new ContainerAlchemicalTome(entityPlayer.inventory), Textures.Gui.ALCHEMICAL_TOME);
        this.inventoryPlayer = entityPlayer.inventory;
        entityPlayer.openContainer = this.inventorySlots;
        xSize = 256;
        ySize = 226;
    }

    @Override
    public void initGui()
    {
        super.initGui();

        prevPageButton = new ElementButton(this, 15, 177, "Prev", 0, 0, 20, 0, 40, 0, 20, 10, 60, 10, "textures/gui/elements/arrowLeft.png");
        nextPageButton = new ElementButton(this, 223, 177, "Next", 0, 0, 22, 0, 44, 0, 22, 10, 66, 10, "textures/gui/elements/arrowRight.png");
        commonAlchemyRibbon = new ElementButton(this, 140, 187, "Common Alchemy", 0, 0, 0, 0, 0, 0, 20, 40, 20, 40, "textures/gui/elements/ribbonVerticalRed.png");
        alkahestryRibbon = new ElementButton(this, 190, 187, "Alkahestry", 0, 0, 0, 0, 0, 0, 20, 40, 20, 40, "textures/gui/elements/ribbonVerticalGreen.png");
        forbiddenAlchemyRibbon = new ElementButton(this, 165, 187, "Forbidden Alchemy", 0, 0, 0, 0, 0, 0, 20, 40, 20, 40, "textures/gui/elements/ribbonVerticalPurple.png");

        commonAlchemyRibbon.setToolTip("Common Alchemy");
        alkahestryRibbon.setToolTip("Alkahestry");
        forbiddenAlchemyRibbon.setToolTip("Forbidden Alchemy");

        addElement(prevPageButton);
        addElement(nextPageButton);
        addElement(commonAlchemyRibbon);
        addElement(alkahestryRibbon);
        addElement(forbiddenAlchemyRibbon);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        // NOOP
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y)
    {
        mouseX = x - guiLeft;
        mouseY = y - guiTop;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        bindTexture(texture);
        drawSizedTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize, 256f, 256f);
        GL11.glPushMatrix();
        GL11.glTranslatef(guiLeft, guiTop, 0.0F);
        drawElements(partialTicks, false);
        drawTabs(partialTicks, false);
        GL11.glPopMatrix();
    }
}
