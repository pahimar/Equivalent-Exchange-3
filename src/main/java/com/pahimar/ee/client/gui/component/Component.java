package com.pahimar.ee.client.gui.component;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * TODO Finish Javadoc
 *
 * @author Pahimar (pahimar@gmail.com)
 * @version 3.0.0
 * @since 3.0.0
 */
public class Component {

    private GuiScreen parentGui;
    private String id;
    private int width;
    private int height;
    private int xPosition;
    private int yPosition;
    private int uPosition;
    private int vPosition;
    private ResourceLocation texture;
    private boolean isEnabled;
    private boolean isVisible;

    /**
     * TODO Finish Javadoc
     *
     * @param id
     * @since 3.0.0
     */
    public Component(final GuiScreen parentGui, final String id) {
        this.parentGui = parentGui;
        this.id = id;
    }

    /**
     * TODO Finish Javadoc
     *
     * @return
     * @since 3.0.0
     */
    public GuiScreen getParentGui() {
        return parentGui;
    }

    /**
     * TODO Finish Javadoc
     *
     * @return
     * @since 3.0.0
     */
    public String getId() {
        return id;
    }

    /**
     * TODO Finish Javadoc
     *
     * @return
     * @since 3.0.0
     */
    public int getWidth() {
        return width;
    }

    /**
     * TODO Finish Javadoc
     *
     * @param width
     * @return <code>this</code> for method chaining.
     * @since 3.0.0
     */
    public Component setWidth(int width) {
        this.width = width;
        return this;
    }

    /**
     * TODO Finish Javadoc
     *
     * @return
     * @since 3.0.0
     */
    public int getHeight() {
        return height;
    }

    /**
     * TODO Finish Javadoc
     *
     * @param height
     * @return <code>this</code> for method chaining.
     * @since 3.0.0
     */
    public Component setHeight(int height) {
        this.height = height;
        return this;
    }

    /**
     * TODO Finish Javadoc
     *
     * @return
     * @since 3.0.0
     */
    public int getX() {
        return xPosition;
    }

    /**
     * TODO Finish Javadoc
     *
     * @param xPosition
     * @return <code>this</code> for method chaining.
     * @since 3.0.0
     */
    public Component setX(int xPosition) {
        this.xPosition = xPosition;
        return this;
    }

    /**
     * TODO Finish Javadoc
     *
     * @return
     * @since 3.0.0
     */
    public int getY() {
        return yPosition;
    }

    /**
     * TODO Finish Javadoc
     *
     * @param yPosition
     * @return <code>this</code> for method chaining.
     * @since 3.0.0
     */
    public Component setY(int yPosition) {
        this.yPosition = yPosition;
        return this;
    }

    /**
     * TODO Finish Javadoc
     *
     * @return
     * @since 3.0.0
     */
    public int getU() {
        return uPosition;
    }

    /**
     * TODO Finish Javadoc
     *
     * @param uPosition
     * @return <code>this</code> for method chaining.
     * @since 3.0.0
     */
    public Component setU(int uPosition) {
        this.uPosition = uPosition;
        return this;
    }

    /**
     * TODO Finish Javadoc
     *
     * @return
     * @since 3.0.0
     */
    public int getV() {
        return vPosition;
    }

    /**
     * TODO Finish Javadoc
     *
     * @param vPosition
     * @return <code>this</code> for method chaining.
     * @since 3.0.0
     */
    public Component setV(int vPosition) {
        this.vPosition = vPosition;
        return this;
    }

    /**
     * TODO Finish Javadoc
     *
     * @return
     * @since 3.0.0
     */
    @Nullable
    public ResourceLocation getTexture() {
        return texture;
    }

    /**
     * TODO Finish Javadoc
     *
     * @param texture
     * @return <code>this</code> for method chaining.
     * @since 3.0.0
     */
    public Component setTexture(ResourceLocation texture) {
        this.texture = texture;
        return this;
    }

    /**
     * TODO Finish Javadoc
     *
     * @return <code>true</code> if the component is enabled, <code>false</code> otherwise.
     * @since 3.0.0
     */
    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * TODO Finish Javadoc
     *
     * @param isEnabled
     * @return <code>this</code> for method chaining.
     * @since 3.0.0
     */
    public Component setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
        return this;
    }

    /**
     * TODO Finish Javadoc
     *
     * @return <code>true</code> if the component is visible, <code>false</code> otherwise.
     * @since 3.0.0
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * TODO Finish Javadoc
     *
     * @param isVisible
     * @return <code>this</code> for method chaining.
     * @since 3.0.0
     */
    public Component setVisibility(boolean isVisible) {
        this.isVisible = isVisible;
        return this;
    }
}