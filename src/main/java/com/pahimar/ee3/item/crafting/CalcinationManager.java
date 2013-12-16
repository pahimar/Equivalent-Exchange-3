package com.pahimar.ee3.item.crafting;

/**
 * Equivalent-Exchange-3
 * <p/>
 * CalcinationManager
 *
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */
public class CalcinationManager
{

    private static final CalcinationManager calcinationBase = new CalcinationManager();

    public static CalcinationManager calcination()
    {

        return calcinationBase;
    }
}
