package com.pahimar.ee3.knowledge;

public class Skill
{
    private final boolean learnable;
    private final boolean recoverable;
    private final int knowledgeTier;

    public Skill()
    {
        this(true, true, 0);
    }

    public Skill(boolean learnable, boolean recoverable)
    {
        this(learnable, recoverable, 0);
    }

    public Skill(boolean learnable, boolean recoverable, int tier)
    {
        this.learnable = learnable;
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

    public boolean isLearnable()
    {
        return learnable;
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
        return object instanceof Skill && (this.learnable == ((Skill) object).learnable && this.recoverable == ((Skill) object).recoverable && this.knowledgeTier == ((Skill) object).knowledgeTier);
    }

    @Override
    public String toString()
    {
        return String.format("Skill[learnable: %s, recoverable: %s, knowledgeTier: %s]", learnable, recoverable, knowledgeTier);
    }
}
