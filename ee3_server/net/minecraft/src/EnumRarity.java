package net.minecraft.src;

/**
 * EnumRarity
 * Stubbed in server side to allow for EE item code to be common and not client/server specific
 * @author pahimar
 *
 */

public enum EnumRarity
{
    common(15, "Common"),
    uncommon(14, "Uncommon"),
    rare(11, "Rare"),
    epic(13, "Epic");

    /** The color given to the name of items with that rarity. */
    public final int nameColor;
    public final String field_40532_f;

    private EnumRarity(int par3, String par4Str)
    {
        this.nameColor = par3;
        this.field_40532_f = par4Str;
    }
}