package com.pahimar.ee3.emc.graph;

public class WeightedEdge<T> implements Comparable<WeightedEdge<T>> {

    public final float weight;
    public final T target;

    public WeightedEdge(T target) {

        this(target, 1);
    }

    public WeightedEdge(T target, float weight) {

        this.weight = weight;
        this.target = target;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof WeightedEdge<?>)) {
            return false;
        }

        WeightedEdge<?> edge = (WeightedEdge<?>) object;

        return (Float.compare(this.weight, edge.weight) == 0) && target.equals(edge.target);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(WeightedEdge<T> weightedEdge) {

        if (weightedEdge instanceof WeightedEdge) {
            if (Float.compare(this.weight, weightedEdge.weight) == 0) {
                return (this.target.hashCode() - weightedEdge.target.hashCode());
            }
            else {
                return Float.compare(this.weight, weightedEdge.weight);
            }
        }
        else {
            return 1;
        }
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Target: %s, Weight: %s", target, weight));

        return stringBuilder.toString();
    }
}
