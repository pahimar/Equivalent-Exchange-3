package com.pahimar.ee.client.gui.component;

import com.pahimar.ee.client.gui.base.GuiBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Comparator;

public abstract class GuiComponent implements Comparable<GuiComponent> {

    public static Comparator<GuiComponent> orderingComparator = new Comparator<GuiComponent>() {

        @Override
        public int compare(GuiComponent guiComponent1, GuiComponent guiComponent2) {
            if (guiComponent1.ordering == guiComponent2.ordering) {
                if (guiComponent1.zIndex == guiComponent2.zIndex) {
                    if (guiComponent1.id != null && guiComponent2.id != null) {
                        return guiComponent1.id.compareToIgnoreCase(guiComponent2.id);
                    } else {
                        return guiComponent1.hashCode() - guiComponent2.hashCode();
                    }
                } else {
                    // Purposefully sorting so that higher z-indices appear first in the map
                    return guiComponent2.zIndex - guiComponent1.zIndex;
                }
            } else {
                return guiComponent1.ordering - guiComponent2.ordering;
            }
        }
    };
    public static Comparator<GuiComponent> zIndexComparator = new Comparator<GuiComponent>() {

        @Override
        public int compare(GuiComponent guiComponent1, GuiComponent guiComponent2) {
            if (guiComponent1.zIndex == guiComponent2.zIndex) {
                if (guiComponent1.ordering == guiComponent2.ordering) {
                    if (guiComponent1.id != null && guiComponent2.id != null) {
                        return guiComponent1.id.compareToIgnoreCase(guiComponent2.id);
                    } else {
                        return guiComponent1.hashCode() - guiComponent2.hashCode();
                    }
                } else {
                    return guiComponent1.ordering - guiComponent2.ordering;
                }
            } else {
                // Purposefully sorting so that higher z-indices appear first in the map
                return guiComponent2.zIndex - guiComponent1.zIndex;
            }
        }
    };

    protected final GuiBase parentGui;
    protected final String id;
    @SideOnly(Side.CLIENT)
    protected ResourceLocation texture;
    protected int positionX, positionY, componentWidth, componentHeight, textureWidth, textureHeight;
    protected int ordering = 0;
    protected int zIndex = 0;
    protected boolean isVisible = true;
    protected boolean isEnabled = true;
    protected boolean isFocusable = true;

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

    public boolean isFocusable() {
        return isFocusable;
    }

    public GuiComponent setIsFocusable(boolean isFocusable) {
        this.isFocusable = isFocusable;
        return this;
    }

    public boolean hasFocus() {
        if (isFocusable) {
            return getParentGui().getActiveGuiComponentId().equals(getId());
        }

        return false;
    }

    public abstract void drawForeground(int rawMouseX, int rawMouseY, float partialTicks);

    public abstract void drawBackground(int rawMouseX, int rawMouseY, float partialTicks);

    public abstract void onUpdate(int rawMouseX, int rawMouseY, float partialTicks);

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


    /**
     * @param rawMouseX
     * @param rawMouseY
     * @param mouseButton
     */
    public abstract void onMouseButtonClicked(int rawMouseX, int rawMouseY, int mouseButton);

    /**
     *
     * @param rawMouseX
     * @param rawMouseY
     * @param mouseButton
     * @return
     */
    public boolean onMouseButtonClick(int rawMouseX, int rawMouseY, int mouseButton) {
        onMouseButtonClicked(rawMouseX, rawMouseY, mouseButton);
        return false;
    }

    public abstract void onMouseButtonDown(int rawMouseX, int rawMouseY, int mouseButton);

    public abstract void onMouseButtonUp(int rawMouseX, int rawMouseY, int mouseButton);

    public abstract void onMouseButtonRelease(int rawMouseX, int rawMouseY, int mouseButton, long duration);

    public abstract void onMouseMove(int prevRawMouseX, int prevRawMouseY, int newRawMouseX, int newRawMouseY);

    public abstract void onMouseEnter(int rawMouseX, int rawMouseY);

    public abstract void onMouseOver(int rawMouseX, int rawMouseY, float partialTicks);

    public abstract void onMouseLeave(int rawMouseX, int rawMouseY);

    public abstract void onMouseWheel(int rawMouseX, int rawMouseY, int change);

    public abstract void onKeyPressed(char characterTyped, int keyPressed);

    /**
     * @param characterTyped
     * @param keyPressed
     * @return
     */
    public boolean onKeyPress(char characterTyped, int keyPressed) {
        onKeyPress(characterTyped, keyPressed);
        return false;
    }

    public abstract void onFocusGained();

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
        return orderingComparator.compare(this, guiComponent);
    }
}
