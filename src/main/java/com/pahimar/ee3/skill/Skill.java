package com.pahimar.ee3.skill;

public class Skill
{
    private boolean learnable;
    private boolean recoverable;
    private int tier;

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
            this.tier = tier;
        }
        else
        {
            this.tier = 0;
        }
    }

    public boolean isLearnable()
    {
        return learnable;
    }

    public void setLearnable(boolean learnable)
    {
        this.learnable = learnable;
    }

    public boolean isRecoverable()
    {
        return recoverable;
    }

    public void setRecoverable(boolean recoverable)
    {
        this.recoverable = recoverable;
    }

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier)
    {
        this.tier = tier;
    }

    @Override
    public boolean equals(Object object)
    {
        return object instanceof Skill && (this.learnable == ((Skill) object).learnable && this.recoverable == ((Skill) object).recoverable && this.tier == ((Skill) object).tier);
    }

    @Override
    public String toString()
    {
        return String.format("Skill[learnable: %s, recoverable: %s, tier: %s]", learnable, recoverable, tier);
    }
}
