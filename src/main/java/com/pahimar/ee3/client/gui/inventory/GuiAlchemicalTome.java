package com.pahimar.ee3.client.gui.inventory;

import com.pahimar.ee3.inventory.ContainerAlchemicalTome;
import com.pahimar.ee3.reference.Textures;
import com.pahimar.repackage.cofh.lib.gui.GuiBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.util.StatCollector;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiAlchemicalTome extends GuiBase
{
    //    private InventoryAlchemicalTome inventoryAlchemicalTome;
    private InventoryPlayer inventoryPlayer;
    public static InventoryBasic tempInventory = new InventoryBasic("tmp", true, 81);

    private float currentScroll;
    private boolean isScrolling;
    private boolean wasClicking;
    private GuiTextField searchField;

    private Slot field_147064_C;
    private boolean field_147057_D;

    public GuiAlchemicalTome(EntityPlayer entityPlayer)
    {
        super(new ContainerAlchemicalTome(entityPlayer.inventory));
        this.inventoryPlayer = entityPlayer.inventory;
        entityPlayer.openContainer = this.inventorySlots;
        this.allowUserInput = true;
        xSize = 209;
        ySize = 200;
    }

    @Override
    public void initGui()
    {
        super.initGui();
        Keyboard.enableRepeatEvents(true);
        this.searchField = new GuiTextField(this.fontRendererObj, this.guiLeft + 90, this.guiTop + 19, 96, this.fontRendererObj.FONT_HEIGHT);
        this.searchField.setMaxStringLength(32);
        this.searchField.setEnableBackgroundDrawing(false);
        this.searchField.setVisible(true);
        this.searchField.setFocused(true);
        this.searchField.setCanLoseFocus(false);
        this.searchField.setText("");
        this.searchField.setTextColor(16777215);
    }

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
    }

    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        boolean flag = Mouse.isButtonDown(0);
        int k = this.guiLeft;
        int l = this.guiTop;
        int i1 = k + 175;
        int j1 = l + 18;
        int k1 = i1 + 14;
        int l1 = j1 + 112;

        if (!this.wasClicking && flag && p_73863_1_ >= i1 && p_73863_2_ >= j1 && p_73863_1_ < k1 && p_73863_2_ < l1)
        {
            this.isScrolling = this.needsScrollBars();
        }

        if (!flag)
        {
            this.isScrolling = false;
        }

        this.wasClicking = flag;

        if (this.isScrolling)
        {
            this.currentScroll = ((float) (p_73863_2_ - j1) - 7.5F) / ((float) (l1 - j1) - 15.0F);

            if (this.currentScroll < 0.0F)
            {
                this.currentScroll = 0.0F;
            }

            if (this.currentScroll > 1.0F)
            {
                this.currentScroll = 1.0F;
            }

            ((ContainerAlchemicalTome) this.inventorySlots).scrollTo(this.currentScroll);
        }

        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y)
    {
        String containerName = StatCollector.translateToLocal(inventoryPlayer.getInventoryName());
        fontRendererObj.drawString(containerName, xSize / 2 - fontRendererObj.getStringWidth(containerName) / 2, 6, 4210752);
        fontRendererObj.drawString("Search Items", 16, 20, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.Gui.ALCHEMICAL_TOME);
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        this.searchField.drawTextBox();
    }

    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
        if (this.field_147057_D)
        {
            this.field_147057_D = false;
            this.searchField.setText("");
        }

        if (!this.checkHotbarKeys(p_73869_2_))
        {
            if (this.searchField.textboxKeyTyped(p_73869_1_, p_73869_2_))
            {
                this.updateSearch();
            }
            else
            {
                super.keyTyped(p_73869_1_, p_73869_2_);
            }
        }
    }

    private void updateSearch()
    {

    }

    private void updateFilteredItems(ContainerAlchemicalTome containerAlchemicalTome)
    {

    }

    @Override
    public void handleMouseInput()
    {
        super.handleMouseInput();
        int i = Mouse.getEventDWheel();

        if (i != 0 && this.needsScrollBars())
        {
            int j = ((ContainerAlchemicalTome) this.inventorySlots).itemList.size() / 9 - 5 + 1;

            if (i > 0)
            {
                i = 1;
            }

            if (i < 0)
            {
                i = -1;
            }

            this.currentScroll = (float) ((double) this.currentScroll - (double) i / (double) j);

            if (this.currentScroll < 0.0F)
            {
                this.currentScroll = 0.0F;
            }

            if (this.currentScroll > 1.0F)
            {
                this.currentScroll = 1.0F;
            }

            ((ContainerAlchemicalTome) this.inventorySlots).scrollTo(this.currentScroll);
        }
    }

    private boolean needsScrollBars()
    {
        return ((ContainerAlchemicalTome) this.inventorySlots).isScrollable();
    }
}
