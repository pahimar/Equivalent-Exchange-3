package com.pahimar.repackage.cofh.lib.util;

public class CharacterSingleton implements CharSequence
{

    public char character;

    @Override
    public int length()
    {

        return 1;
    }

    @Override
    public char charAt(int index)
    {

        return character;
    }

    @Override
    public CharSequence subSequence(int start, int end)
    {

        if (start == end)
        {
            return "";
        }
        return this;
    }

}

