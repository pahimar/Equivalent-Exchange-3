package com.pahimar.ee3.skill;

public class Skill
{
    private boolean learnable;
    private boolean recoverable;

    public Skill(boolean learnable, boolean recoverable)
    {
        this.learnable = learnable;
        this.recoverable = recoverable;
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

    @Override
    public boolean equals(Object object)
    {
        return object instanceof Skill && (this.learnable == ((Skill) object).learnable && this.recoverable == ((Skill) object).recoverable);
    }

    @Override
    public String toString()
    {
        return String.format("Skill[learnable: %s, recoverable: %s]", learnable, recoverable);
    }
}
