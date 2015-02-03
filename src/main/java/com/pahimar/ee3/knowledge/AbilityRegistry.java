package com.pahimar.ee3.knowledge;

import com.pahimar.ee3.exchange.WrappedStack;

import java.util.Set;
import java.util.TreeSet;

// TODO Serialize the sets to separate JSON for map makers, cause you know, I love them
public class AbilityRegistry {
    private static AbilityRegistry AbilityRegistry = null;
    private Set<WrappedStack> notLearnableSet;
    private Set<WrappedStack> notRecoverableSet;

    private AbilityRegistry() {
    }

    public static AbilityRegistry getInstance() {
        if (AbilityRegistry == null) {
            AbilityRegistry = new AbilityRegistry();
            AbilityRegistry.init();
        }

        return AbilityRegistry;
    }

    private void init() {
        notLearnableSet = new TreeSet<WrappedStack>();
        notRecoverableSet = new TreeSet<WrappedStack>();
    }

    public boolean isLearnable(Object object) {
        if (WrappedStack.canBeWrapped(object)) {
            WrappedStack wrappedObject = new WrappedStack(object);
            return !notLearnableSet.contains(wrappedObject);
        }

        return false;
    }

    public void setAsLearnable(Object object) {
        if (WrappedStack.canBeWrapped(object)) {
            notLearnableSet.remove(new WrappedStack(object));
        }
    }

    public void setAsNotLearnable(Object object) {
        if (WrappedStack.canBeWrapped(object)) {
            notLearnableSet.add(new WrappedStack(object));
        }
    }

    public boolean isRecoverable(Object object) {
        if (WrappedStack.canBeWrapped(object)) {
            return !notRecoverableSet.contains(new WrappedStack(object));
        }

        return false;
    }

    public void setAsRecoverable(Object object) {
        if (WrappedStack.canBeWrapped(object)) {
            notRecoverableSet.remove(new WrappedStack(object));
        }
    }

    public void setAsNotRecoverable(Object object) {
        if (WrappedStack.canBeWrapped(object)) {
            notRecoverableSet.add(new WrappedStack(object));
        }
    }
}
