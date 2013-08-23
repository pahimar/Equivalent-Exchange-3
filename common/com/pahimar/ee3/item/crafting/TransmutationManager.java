package com.pahimar.ee3.item.crafting;

import java.util.ArrayList;
import java.util.List;

/**
 * Equivalent-Exchange-3
 * 
 * TransmutationManager
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class TransmutationManager {

    /** The static instance of this class */
    private static final TransmutationManager instance = new TransmutationManager();

    /** A list of all the recipes added */
    private List<?> recipes = new ArrayList<Object>();

    /**
     * Returns the static instance of this class
     */
    public static final TransmutationManager getInstance() {

        return instance;
    }

    private TransmutationManager() {

    }

    /**
     * returns the List<> of all recipes
     */
    public List<?> getRecipeList() {

        return recipes;
    }
}
