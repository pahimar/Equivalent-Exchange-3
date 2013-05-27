package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.pahimar.ee3.item.CustomStackWrapper;

public class EquivalencyGroup {

    private List<CustomStackWrapper> equivalentItems;

    public EquivalencyGroup() {

        equivalentItems = new ArrayList<CustomStackWrapper>();
    }
    
    public EquivalencyGroup(List<ItemStack> equivalentItems) {
        
        this.equivalentItems = new ArrayList<CustomStackWrapper>();
        
        for (ItemStack itemStack : equivalentItems) {
            this.equivalentItems.add(new CustomStackWrapper(itemStack));
        }
    }

    public List<CustomStackWrapper> getMembers() {

        return equivalentItems;
    }

    public boolean containsMember(ItemStack itemStack) {

        return containsMember(new CustomStackWrapper(itemStack));
    }

    public boolean containsMember(CustomStackWrapper customStackWrapper) {

        return equivalentItems.contains(customStackWrapper);
    }

    public void addMember(ItemStack itemStack) {

        this.addMember(new CustomStackWrapper(itemStack));
    }

    public void addMember(CustomStackWrapper customStackWrapper) {

        if (!containsMember(customStackWrapper)) {
            equivalentItems.add(customStackWrapper);
        }
    }
    
    public void setEquivalentItems(List<CustomStackWrapper> equivalentItems) {
        
        this.equivalentItems = equivalentItems;
    }

    public void removeMember(ItemStack itemStack) {

        removeMember(new CustomStackWrapper(itemStack));
    }

    public void removeMember(CustomStackWrapper customStackWrapper) {

        while (containsMember(customStackWrapper)) {
            equivalentItems.remove(customStackWrapper);
        }
    }

    public void clearMembers() {

        equivalentItems = new ArrayList<CustomStackWrapper>();
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof EquivalencyGroup)) {
            return false;
        }

        EquivalencyGroup equivalencyGroup = (EquivalencyGroup) object;

        return (equivalentItems.equals(equivalencyGroup.equivalentItems));
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append("Equivalent Group Members: ");
        for (CustomStackWrapper customStackWrapper : equivalentItems) {
            stringBuilder.append(String.format("%s ", customStackWrapper));
        }

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {

        int hashCode = 1;

        for (CustomStackWrapper customStackWrapper : equivalentItems) {
            hashCode = 37 * hashCode + customStackWrapper.hashCode();
        }

        return hashCode;
    }
}
