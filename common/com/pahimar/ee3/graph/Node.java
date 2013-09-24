package com.pahimar.ee3.graph;

/**
 * Equivalent-Exchange-3
 * 
 * Node
 * 
 * @author pahimar
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 * 
 */
public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

    public enum NodeTraversalStatus {
        UNDISCOVERED, DISCOVERED
    }

    public final T nodeObject;
    public NodeTraversalStatus traversalState;

    public Node(T nodeObject) {

        this.nodeObject = nodeObject;
        traversalState = NodeTraversalStatus.UNDISCOVERED;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Node<?>)) {
            return false;
        }

        Node<?> node = (Node<?>) object;

        return this.traversalState == node.traversalState && this.nodeObject.equals(node.nodeObject);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Node<T> node) {

        if (node instanceof Node && node.nodeObject instanceof Comparable<?>) {
            if (this.nodeObject instanceof Comparable<?>) {
                return this.nodeObject.compareTo(node.nodeObject);
            }
            else {
                return -1;
            }
        }
        else {
            return 1;
        }
    }
    
    @Override
    public int hashCode() {
    	
    	return this.nodeObject.hashCode();
    }
    
    @Override
    public String toString() {
        
        return nodeObject.toString();
    }
}
