package com.pahimar.ee3.emc.graph;

public class WeightedEdge<T> {

    private float weight;
    private T target;

    public WeightedEdge(float weight, T target) {

        this.weight = weight;
        this.target = target;
    }

    public float getWeight() {

        return weight;
    }

    public T getTarget() {

        return target;
    }

    public void setWeight(float weight) {

        this.weight = weight;
    }

    public void setTarget(T target) {

        this.target = target;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof WeightedEdge<?>)) {
            return false;
        }

        WeightedEdge<?> edge = (WeightedEdge<?>) object;

        return ((this.weight == edge.weight) && (target.equals(edge.target)));
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Weight: %s, Target: %s ", weight, target));

        return stringBuilder.toString();
    }
}
