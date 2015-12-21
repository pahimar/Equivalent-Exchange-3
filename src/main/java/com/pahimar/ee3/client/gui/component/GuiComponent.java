package com.pahimar.ee3.client.gui.component;

import com.pahimar.ee3.client.gui.GuiBase;
import net.minecraft.util.ResourceLocation;

public abstract class GuiComponent implements Comparable<GuiComponent> {

    protected final GuiBase parentGui;
    protected final String id;
    protected ResourceLocation texture;

    protected int positionX, positionY, componentWidth, componentHeight, textureWidth, textureHeight;
    protected int ordering = 0;
    protected int zIndex = 0;
    protected boolean isVisible = true;
    protected boolean isEnabled = true;

    public GuiComponent(GuiBase parentGui, String id) {
        this(parentGui, id, null, 0, 0);
    }

    public GuiComponent(GuiBase parentGui, String id, ResourceLocation texture, int positionX, int positionY) {
        this(parentGui, id, texture, positionX, positionY, 256, 256);
    }

    public GuiComponent(GuiBase parentGui, String id, ResourceLocation texture, int positionX, int positionY, int componentWidth, int componentHeight) {
        this(parentGui, id, texture, positionX, positionY, componentWidth, componentHeight, 256, 256);
    }

    public GuiComponent(GuiBase parentGui, String id, ResourceLocation texture, int positionX, int positionY, int componentWidth, int componentHeight, int textureWidth, int textureHeight) {
        this.parentGui = parentGui;
        this.id = id;
        this.texture = texture;
        this.positionX = positionX;
        this.positionY = positionY;
        this.componentWidth = componentWidth;
        this.componentHeight = componentHeight;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    public final GuiBase getParentGui() {
        return parentGui;
    }

    public final String getId() {
        return id;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public GuiComponent setTexture(ResourceLocation texture) {
        this.texture = texture;
        return this;
    }

    public GuiComponent setTexture(ResourceLocation texture, int textureWidth, int textureHeight) {
        this.texture = texture;
        return this.setTextureSize(textureWidth, textureHeight);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public GuiComponent setPosition(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        return this;
    }

    public int getComponentWidth() {
        return componentWidth;
    }

    public int getComponentHeight() {
        return componentHeight;
    }

    public GuiComponent setComponentSize(int componentWidth, int componentHeight) {
        this.componentWidth = componentWidth;
        this.componentHeight = componentHeight;
        return this;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    public GuiComponent setTextureSize(int textureWidth, int textureHeight) {
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        return this;
    }

    public int getOrdering() {
        return ordering;
    }

    public GuiComponent setOrdering(int ordering) {
        this.ordering = ordering;
        return this;
    }

    public int getZIndex() {
        return zIndex;
    }

    public GuiComponent setZIndex(int zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public GuiComponent setVisible(boolean isVisible) {

        if (this.isVisible && !isVisible) {
            this.onHide();
        } else if (!this.isVisible && isVisible) {
            this.onShow();
        }

        this.isVisible = isVisible;
        return this;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public GuiComponent setEnabled(boolean isEnabled) {

        if (this.isEnabled && !isEnabled) {
            this.onDisable();
        } else if (!this.isEnabled && isEnabled) {
            this.onEnable();
        }

        this.isEnabled = isEnabled;
        return this;
    }

    public abstract void drawForeground(float partialTicks);

    public abstract void drawBackground(float partialTicks);

    public abstract void onUpdate(int mouseX, int mouseY, float partialTicks);

    /**
     * Checks whether or not the specified coordinate intersects with this GuiComponent
     *
     * @param xCoord x position
     * @param yCoord y position
     * @return true if the specified coordinates intersect with this GuiComponent, false otherwise
     */
    public boolean intersectsWith(int xCoord, int yCoord) {
        return (xCoord >= this.positionX && xCoord <= this.positionX + this.componentWidth) && (yCoord >= this.positionY && yCoord <= this.positionY + this.componentHeight);
    }

    /**
     * Checks whether or not the specified coordinate and z index intersects with this GuiComponent
     *
     * @param xCoord x position
     * @param yCoord y position
     * @param zIndex z index
     * @return
     */
    public boolean intersectsWith(int xCoord, int yCoord, int zIndex) {
        return (xCoord >= this.positionX && xCoord <= this.positionX + this.componentWidth) && (yCoord >= this.positionY && yCoord <= this.positionY + this.componentHeight) && (zIndex == this.zIndex);
    }

    public abstract void onMouseButtonClick(int mouseX, int mouseY, int mouseButton);

    public abstract void onMouseButtonDown(int mouseX, int mouseY, int mouseButton);

    public abstract void onMouseButtonUp(int mouseX, int mouseY, int mouseButton);

    public abstract void onMouseButtonRelease(int mouseX, int mouseY, int mouseButton, long duration);

    public abstract void onMouseMove(int prevMouseX, int prevMouseY, int newMouseX, int newMouseY);

    public abstract void onMouseEnter(int mouseX, int mouseY);

    public abstract void onMouseOver(int mouseX, int mouseY);

    public abstract void onMouseLeave(int mouseX, int mouseY);

    public abstract void onKeyDown(int keyPressed);

    public abstract void onKeyUp(int keyPressed);

    public abstract void onKeyPress(char characterTyped, int keyPressed);

    public abstract void onFocusGain();

    public abstract void onFocusLost();

    public abstract void onInit();

    public abstract void onClose();

    public abstract void onHide();

    public abstract void onShow();

    public abstract void onEnable();

    public abstract void onDisable();

    /**
     * Compares this GuiComponent with another one to determine sort order
     *
     * @param guiComponent the GuiComponent we are comparing this one against
     * @return
     */
    @Override
    public int compareTo(GuiComponent guiComponent) {
        if (this.ordering == guiComponent.ordering) {
            if (this.zIndex == guiComponent.zIndex) {
                if (this.id != null && guiComponent.id != null) {
                    return this.id.compareToIgnoreCase(guiComponent.id);
                } else {
                    return this.hashCode() - guiComponent.hashCode();
                }
            } else {
                // Purposefully sorting so that higher z-indices appear first in the map
                return guiComponent.zIndex - this.zIndex;
            }
        } else {
            return this.ordering - guiComponent.ordering;
        }
    }
}
