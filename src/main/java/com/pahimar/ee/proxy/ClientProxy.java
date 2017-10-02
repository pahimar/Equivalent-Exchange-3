package com.pahimar.ee.proxy;

import com.pahimar.ee.init.ModItems;
import com.pahimar.ee.item.base.ItemBase;
import net.minecraftforge.fml.common.event.*;

/**
 * TODO Finish Javadoc
 *
 * @author      pahimar
 *
 * @since       3.0.0
 */
public class ClientProxy extends CommonProxy {

    /**
     * {@inheritDoc}
     *
     * @since   3.0.0
     */
    public void onServerStarting(FMLServerStartingEvent event) {
        super.onServerStarting(event);
    }

    /**
     * {@inheritDoc}
     *
     * @since   3.0.0
     */
    public void onPreInit(FMLPreInitializationEvent event) {
        super.onPreInit(event);

        ModItems.getItems().forEach(ItemBase::initModelsAndVariants);
    }

    /**
     * {@inheritDoc}
     *
     * @since   3.0.0
     */
    public void onInit(FMLInitializationEvent event) {
        super.onInit(event);
    }

    /**
     * {@inheritDoc}
     *
     * @since   3.0.0
     */
    public void onPostInit(FMLPostInitializationEvent event) {
        super.onPostInit(event);
    }

    /**
     * {@inheritDoc}
     *
     * @since   3.0.0
     */
    public void onServerStopping(FMLServerStoppingEvent event) {
        super.onServerStopping(event);
    }

    /**
     * TODO Finish Javadoc
     *
     * @return  TODO
     *
     * @since   3.0.0
     */
    public ClientProxy getClientProxy() {
        return this;
    }
}
