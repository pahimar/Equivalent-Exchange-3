package com.pahimar.ee3.skill;

public class Skill
{
    private final boolean canBeLearned;
    private final boolean recoverable;
    private final int knowledgeTier;

    public Skill()
    {
        this(true, true, 0);
    }

    public Skill(boolean canBeLearned, boolean recoverable)
    {
        this(canBeLearned, recoverable, 0);
    }

    public Skill(boolean canBeLearned, boolean recoverable, int tier)
    {
        this.canBeLearned = canBeLearned;
        this.recoverable = recoverable;

        if (tier >= 0)
        {
            this.knowledgeTier = tier;
        }
        else
        {
            this.knowledgeTier = 0;
        }
    }

    public boolean canBeLearned()
    {
        return canBeLearned;
    }

    public boolean isRecoverable()
    {
        return recoverable;
    }

    public int getKnowledgeTier()
    {
        return knowledgeTier;
    }

    @Override
    public boolean equals(Object object)
    {
        return object instanceof Skill && (this.canBeLearned == ((Skill) object).canBeLearned && this.recoverable == ((Skill) object).recoverable && this.knowledgeTier == ((Skill) object).knowledgeTier);
    }

    @Override
    public String toString()
    {
        return String.format("Skill[canBeLearned: %s, recoverable: %s, knowledgeTier: %s]", canBeLearned, recoverable, knowledgeTier);
    }
}
