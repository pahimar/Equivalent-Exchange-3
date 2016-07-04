package com.pahimar.ee3.client.gui.base;

import com.pahimar.ee3.client.gui.component.GuiComponent;
import com.pahimar.ee3.client.util.RenderUtils;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.*;

@SideOnly(Side.CLIENT)
public abstract class GuiBase extends GuiContainer {

    @SideOnly(Side.CLIENT)
    protected ResourceLocation texture;
    protected Map<String, GuiComponent> guiComponentMap = new TreeMap<String, GuiComponent>();
    protected String title;
    private boolean shouldDrawTitle = true;
    private String activeGuiComponentId = null;

    private int adjustedMouseX = 0;
    private int adjustedMouseY = 0;

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

    public int getAdjustedMouseX() {
        return adjustedMouseX;
    }

    protected GuiBase updateAdjustedMouseX(int rawMouseX) {
        this.adjustedMouseX = rawMouseX - getGuiPositionX();
        return this;
    }

    public int getAdjustedMouseY() {
        return adjustedMouseY;
    }

    protected GuiBase updateAdjustedMouseY(int rawMouseY) {
        this.adjustedMouseY = rawMouseY - getGuiPositionY();
        return this;
    }

    public GuiBase updateAdjustedMousePosition(int rawMouseX, int rawMouseY) {
        return updateAdjustedMouseX(rawMouseX).updateAdjustedMouseY(rawMouseY);
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

    public GuiComponent getFirstGuiComponentAt(int positionX, int positionY) {
        for (GuiComponent guiComponent : getGuiComponents()) {
            if (guiComponent.intersectsWith(positionX, positionY)) {
                return guiComponent;
            }
        }

        return null;
    }

    public GuiComponent getTopGuiComponentAt(int positionX, int positionY) {
        TreeSet<GuiComponent> guiComponents = new TreeSet<GuiComponent>(GuiComponent.zIndexComparator);
        for (GuiComponent guiComponent : getGuiComponents()) {
            if (guiComponent.intersectsWith(positionX, positionY)) {
                guiComponents.add(guiComponent);
            }
        }

        if (!guiComponents.isEmpty()) {
            return guiComponents.first();
        } else {
            return null;
        }
    }

    public Collection<GuiComponent> getGuiComponents() {
        return guiComponentMap.values();
    }

    public Collection<GuiComponent> getGuiComponentsAt(int positionX, int positionY) {
        Collection<GuiComponent> intersectingGuiComponents = new ArrayList<GuiComponent>();

        for (GuiComponent guiComponent : getGuiComponents()) {
            if (guiComponent.intersectsWith(positionX, positionY)) {
                intersectingGuiComponents.add(guiComponent);
            }
        }

        return intersectingGuiComponents;
    }

    public Collection<GuiComponent> getGuiComponentsAt(int positionX, int positionY, int zIndex) {
        Collection<GuiComponent> intersectingGuiComponents = new ArrayList<GuiComponent>();

        for (GuiComponent guiComponent : getGuiComponents()) {
            if (guiComponent.intersectsWith(positionX, positionY, zIndex)) {
                intersectingGuiComponents.add(guiComponent);
            }
        }

        return intersectingGuiComponents;
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

        // A bunch of different impls clear the list of components here - no reason I can discern why at this point

        for (GuiComponent guiComponent : getGuiComponents()) {
            guiComponent.onInit();
        }
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();

        for (GuiComponent guiComponent : getGuiComponents()) {
            guiComponent.onClose();
        }
    }

    // Keyboard
    @Override
    protected void keyTyped(char characterTyped, int keyPressed) throws IOException {
        for (GuiComponent guiComponent : getGuiComponents()) {
            if (guiComponent.onKeyPress(characterTyped, keyPressed)) {
                return;
            }
        }

        super.keyTyped(characterTyped, keyPressed);
    }

    // Mouse
    @Override
    public void handleMouseInput() throws IOException {
        // TODO
        super.handleMouseInput();
    }

    @Override
    protected void mouseClicked(int rawMouseX, int rawMouseY, int mouseButton) throws IOException {
        // TODO
        super.mouseClicked(rawMouseX, rawMouseY, mouseButton);
    }

    @Override
    protected void mouseReleased(int rawMouseX, int rawMouseY, int mouseButton) {
        // TODO
        super.mouseReleased(rawMouseX, rawMouseY, mouseButton);
    }

    @Override
    protected void mouseClickMove(int rawMouseX, int rawMouseY, int mouseButton, long duration) {

    }

    public Slot getSlotAtPosition(int rawMouseX, int rawMouseY) {

        Iterator iterator = this.inventorySlots.inventorySlots.iterator();
        while (iterator.hasNext()) {
            Slot slot = (Slot) iterator.next();
            if (this.isMouseOverSlot(slot, rawMouseX, rawMouseY)) {
                return slot;
            }
        }

        return null;
    }

    public boolean isMouseOverSlot(Slot slot, int rawMouseX, int rawMouseY) {
        return this.isMouseOverSlot(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, rawMouseX, rawMouseY);
    }


    protected boolean isMouseOverSlot(int slotPositionX, int slotPositionY, int slotWidth, int slotHeight, int rawMouseX, int rawMouseY) {
        rawMouseX -= getGuiPositionX();
        rawMouseY -= getGuiPositionX();
        return (rawMouseX >= slotPositionX - 1) && (rawMouseX < slotPositionX + slotWidth + 1) && (rawMouseY >= slotPositionY - 1) && (rawMouseY < slotPositionY + slotHeight + 1);
    }

    @Override
    public void drawScreen(int rawMouseX, int rawMouseY, float partialTicks) {
        super.drawScreen(rawMouseX, rawMouseY, partialTicks);

        updateAdjustedMousePosition(rawMouseX, rawMouseY);

        for (GuiComponent guiComponent : getGuiComponents()) {
            if (guiComponent.intersectsWith(getAdjustedMouseX(), getAdjustedMouseY())) {
                guiComponent.onMouseOver(getAdjustedMouseX(), getAdjustedMouseY(), partialTicks);
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int rawMouseX, int rawMouseY) {
        // Draw text
        if (shouldDrawTitle && title != null) {
            getFontRenderer().drawString(I18n.format(title), RenderUtils.getCenteredTextOffset(getFontRenderer(), I18n.format(title), getGuiWidth()), 6, 0x404040);
        }

        // Draw components
        drawComponents(true, rawMouseX, rawMouseY, 0);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int rawMouseX, int rawMouseY) {
        // Draw background
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        if (texture != null) {
            RenderUtils.bindTexture(texture);
            int xStart = (getScreenWidth() - getGuiWidth()) / 2;
            int yStart = (getScreenHeight() - getGuiHeight()) / 2;
            this.drawTexturedModalRect(xStart, yStart, 0, 0, getGuiWidth(), getGuiHeight());
        }

        // Draw components
        GL11.glPushMatrix();
        GL11.glTranslatef(getGuiPositionX(), getGuiPositionY(), 0.0F);
        drawComponents(false, rawMouseX, rawMouseY, partialTicks);
        GL11.glPopMatrix();
    }

    protected void drawComponents(boolean drawForeground, int rawMouseX, int rawMouseY, float partialTicks) {

        for (GuiComponent guiComponent : getGuiComponents()) {
            if (guiComponent.isVisible()) {
                if (drawForeground) {
                    guiComponent.drawForeground(rawMouseX, rawMouseY, partialTicks);
                } else {
                    guiComponent.drawBackground(rawMouseX, rawMouseY, partialTicks);
                }
            }
        }
    }
}
