package com.pahimar.ee3.graph;

public class WeightedEdge<T extends Comparable<T>> implements Comparable<WeightedEdge<T>> {

    public enum EdgeTraversalStatus {
        UNDISCOVERED, DISCOVERY_EDGE, BACK_EDGE
    }

    public final float weight;
    public final Node<T> destinationNode;
    public EdgeTraversalStatus edgeTraversalStatus;

    public WeightedEdge(T nodeObject) {

        this(nodeObject, 1);
    }

    public WeightedEdge(T nodeObject, float weight) {

        this(new Node<T>(nodeObject), weight);
    }
    
    public WeightedEdge(Node<T> destinationNode) {
    	
    	this(destinationNode, 1);
    }

    public WeightedEdge(Node<T> destinationNode, float weight) {

        this.weight = weight;
        this.destinationNode = destinationNode;
        this.edgeTraversalStatus = EdgeTraversalStatus.UNDISCOVERED;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof WeightedEdge<?>)) {
            return false;
        }

        WeightedEdge<?> edge = (WeightedEdge<?>) object;

        return (Float.compare(this.weight, edge.weight) == 0) && 
                destinationNode.equals(edge.destinationNode);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(WeightedEdge<T> weightedEdge) {

        if (weightedEdge instanceof WeightedEdge) {
            if (Float.compare(this.weight, weightedEdge.weight) == 0) {
                return this.destinationNode.compareTo(weightedEdge.destinationNode);
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

        stringBuilder.append(String.format("Target: %s, Weight: %s", destinationNode, weight));

        return stringBuilder.toString();
    }
}
