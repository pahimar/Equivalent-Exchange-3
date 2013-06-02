package com.pahimar.ee3.emc;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

import com.pahimar.ee3.item.CustomWrappedStack;

public class EquivalencyGroup {

    private List<CustomWrappedStack> equivalentItems;

    public EquivalencyGroup() {

        equivalentItems = new ArrayList<CustomWrappedStack>();
    }
    
    public EquivalencyGroup(List<ItemStack> equivalentItems) {
        
        this.equivalentItems = new ArrayList<CustomWrappedStack>();
        
        for (ItemStack itemStack : equivalentItems) {
            this.equivalentItems.add(new CustomWrappedStack(itemStack));
        }
    }

    public List<CustomWrappedStack> getMembers() {

        return equivalentItems;
    }

    public boolean containsMember(ItemStack itemStack) {

        return containsMember(new CustomWrappedStack(itemStack));
    }

    public boolean containsMember(CustomWrappedStack customStackWrapper) {

        return equivalentItems.contains(customStackWrapper);
    }

    public void addMember(ItemStack itemStack) {

        this.addMember(new CustomWrappedStack(itemStack));
    }

    public void addMember(CustomWrappedStack customStackWrapper) {

        if (!containsMember(customStackWrapper)) {
            equivalentItems.add(customStackWrapper);
        }
    }
    
    public void setEquivalentItems(List<CustomWrappedStack> equivalentItems) {
        
        this.equivalentItems = equivalentItems;
    }

    public void removeMember(ItemStack itemStack) {

        removeMember(new CustomWrappedStack(itemStack));
    }

    public void removeMember(CustomWrappedStack customStackWrapper) {

        while (containsMember(customStackWrapper)) {
            equivalentItems.remove(customStackWrapper);
        }
    }

    public void clearMembers() {

        equivalentItems = new ArrayList<CustomWrappedStack>();
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
        for (CustomWrappedStack customStackWrapper : equivalentItems) {
            stringBuilder.append(String.format("%s ", customStackWrapper));
        }

        return stringBuilder.toString();
    }

    @Override
    public int hashCode() {

        int hashCode = 1;

        for (CustomWrappedStack customStackWrapper : equivalentItems) {
            hashCode = 37 * hashCode + customStackWrapper.hashCode();
        }

        return hashCode;
    }
}
