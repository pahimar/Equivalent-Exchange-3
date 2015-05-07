package com.pahimar.ee3.util;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState;

public class LoaderHelper
{
    public static LoaderState getLoaderState()
    {
        if (Loader.instance().isInState(LoaderState.SERVER_STARTED))
        {
            return LoaderState.SERVER_STARTED;
        }
        else if (Loader.instance().isInState(LoaderState.SERVER_STARTING))
        {
            return LoaderState.SERVER_STARTING;
        }
        else if (Loader.instance().isInState(LoaderState.SERVER_ABOUT_TO_START))
        {
            return LoaderState.SERVER_ABOUT_TO_START;
        }
        else if (Loader.instance().isInState(LoaderState.AVAILABLE))
        {
            return LoaderState.AVAILABLE;
        }
        else if (Loader.instance().isInState(LoaderState.POSTINITIALIZATION))
        {
            return LoaderState.POSTINITIALIZATION;
        }
        else if (Loader.instance().isInState(LoaderState.INITIALIZATION))
        {
            return LoaderState.INITIALIZATION;
        }
        else if (Loader.instance().isInState(LoaderState.PREINITIALIZATION))
        {
            return LoaderState.PREINITIALIZATION;
        }
        else
        {
            return null;
        }
    }
}
