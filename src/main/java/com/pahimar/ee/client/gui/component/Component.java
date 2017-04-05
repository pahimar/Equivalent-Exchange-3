package com.pahimar.ee.client.gui.component;

import net.minecraft.util.ResourceLocation;

/**
 * TODO Finish Javadoc
 *
 * @author Pahimar (pahimar@gmail.com)
 * @version 3.0.0
 * @since 3.0.0
 */
//FIXME Pick up here
public class Component {

    protected String componentId;
    protected int width;
    protected int height;
    protected int xPosition;
    protected int yPosition;
    protected int uPosition;
    protected int vPosition;
    protected ResourceLocation resourceLocation;
    protected boolean isEnabled;
    protected boolean isVisible;

    /**
     * TODO Finish Javadoc
     *
     * @param componentId
     * @since 3.0.0
     */
    public Component(final String componentId) {
        this.componentId = componentId;
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
     * @return
     * @since 3.0.0
     */
    public int getHeight() {
        return height;
    }

    public enum HoverState {
        DISABLED,
        NOT_HOVERED,
        HOVERED
    }
}
