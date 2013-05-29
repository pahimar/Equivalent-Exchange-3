package com.pahimar.ee3.emc.graph;



public class WeightedEdge<T> {
    
    private int weight;
    private T target;
    
    public WeightedEdge(int weight, T target) {
        
        this.weight = weight;
        this.target = target;
    }
    
    public int getWeight() {
        
        return weight;
    }
    
    public T getTarget() {
        
        return target;
    }
    
    public void setWeight(int weight) {
        
        this.weight = weight;
    }
    
    public void setTarget(T target) {
        
        this.target = target;
    }
}
