package com.pahimar.ee3.client.gui;

import com.pahimar.ee3.client.gui.component.GuiComponent;
import com.pahimar.ee3.client.util.RenderUtils;
import com.pahimar.repackage.cofh.lib.util.helpers.StringHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@SideOnly(Side.CLIENT)
public abstract class GuiBase extends GuiContainer {

    protected ResourceLocation texture;
    protected Map<String, GuiComponent> guiComponentMap = new TreeMap<String, GuiComponent>();
    protected String title;
    private boolean shouldDrawTitle = true;
    private String activeGuiComponentId = null;

    public GuiBase(Container container) {
        this(null, container);
    }

    public GuiBase(String title, Container container) {
        this(title, container, null);
    }

    public GuiBase(Container container, ResourceLocation texture) {
        this(null, container, texture);
    }

    public GuiBase(String title, Container container, ResourceLocation texture) {
        super(container);
        this.title = title;
        this.texture = texture;
    }

    public String getActiveGuiComponentId() {
        return activeGuiComponentId;
    }

    public String getTitle() {
        return title;
    }

    public GuiBase setTitle(String title) {
        this.title = title;
        return this;
    }

    public boolean shouldDrawTitle() {
        return shouldDrawTitle;
    }

    public GuiBase setShouldDrawTitle(boolean shouldDrawTitle) {
        this.shouldDrawTitle = shouldDrawTitle;
        return this;
    }

    public int getScreenWidth() {
        return width;
    }

    public int getScreenHeight() {
        return height;
    }

    public int getGuiPositionX() {
        return guiLeft;
    }

    public GuiBase setGuiPositionX(int positionX) {
        this.guiLeft = positionX;
        return this;
    }

    public int getGuiPositionY() {
        return guiTop;
    }

    public GuiBase setGuiPositionY(int positionY) {
        this.guiTop = positionY;
        return this;
    }

    public int getGuiWidth() {
        return xSize;
    }

    public GuiBase setGuiWidth(int width) {
        this.xSize = width;
        return this;
    }

    public int getGuiHeight() {
        return ySize;
    }

    public GuiBase setGuiHeight(int height) {
        this.ySize = height;
        return this;
    }

    public FontRenderer getFontRenderer() {
        return fontRendererObj;
    }

    public GuiComponent getGuiComponent(String id) {
        return guiComponentMap.get(id);
    }

    public Collection<GuiComponent> getGuiComponents() {
        return guiComponentMap.values();
    }

    public GuiBase addGuiComponent(GuiComponent guiComponent) {
        this.guiComponentMap.put(guiComponent.getId(), guiComponent);
        return this;
    }

    public GuiBase addGuiComponents(Collection<GuiComponent> guiComponents) {
        for (GuiComponent guiComponent : guiComponents) {
            this.guiComponentMap.put(guiComponent.getId(), guiComponent);
        }

        return this;
    }

    public void clearGuiComponents() {
        this.guiComponentMap.clear();
    }

    @Override
    public void initGui() {
        super.initGui();

        // A bunch of different impls clear the list of components here - no reason I can gather why at this point

        for (GuiComponent guiComponent : guiComponentMap.values()) {
            guiComponent.onInit();
        }
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        for (GuiComponent guiComponent : guiComponentMap.values()) {
            guiComponent.onClose();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        for (GuiComponent guiComponent : guiComponentMap.values()) {
            guiComponent.onUpdate(mouseX, mouseY, partialTicks);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

        if (shouldDrawTitle && title != null) {
            getFontRenderer().drawString(StringHelper.localize(title), RenderUtils.getCenteredTextOffset(getFontRenderer(), StringHelper.localize(title), getGuiWidth()), 6, 0x404040);
        }
        drawComponents(true);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (texture != null) {
            RenderUtils.bindTexture(texture);
            int xStart = (getScreenWidth() - getGuiWidth()) / 2;
            int yStart = (getScreenHeight() - getGuiHeight()) / 2;
            this.drawTexturedModalRect(xStart, yStart, 0, 0, getGuiWidth(), getGuiHeight());
        }

        GL11.glPushMatrix();
        GL11.glTranslatef(getGuiPositionX(), getGuiPositionY(), 0.0F);
        drawComponents(false, partialTicks);
        GL11.glPopMatrix();

    }

    protected void drawComponents(boolean drawForeground) {
        drawComponents(drawForeground, 0);
    }

    protected void drawComponents(boolean drawForeground, float partialTicks) {

        for (GuiComponent guiComponent : guiComponentMap.values()) {
            if (guiComponent.isVisible()) {
                if (drawForeground) {
                    guiComponent.drawForeground(partialTicks);
                } else {
                    guiComponent.drawBackground(partialTicks);
                }
            }
        }
    }
}
