package it.unibo.tnk23.game.graph.impl;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.api.AbstractVisitableNode;

/**
 * The {@code VisitableGridGraphNode} class represents a node in a grid-based graph that can be visited.
 * It extends the {@link AbstractVisitableNode} class.
 * 
 * <p> This class serves as a wrapper for a {@link GridGraphNode} with additional visitation-related functionality.
 */
public class VisitableGridGraphNode extends AbstractVisitableNode<GridGraphNode> {

    private Directions dirToParent;

    /**
     * Constructs a new {@code VisitableGridGraphNode} with the specified grid position.
     *
     * @param gridPos the grid position of the node
     */
    public VisitableGridGraphNode(Pair<Integer, Integer> gridPos) {
        super(new GridGraphNode(gridPos));
        this.reset();
    }

    /**
     * Constructs a new {@code VisitableGridGraphNode} with the specified {@link GridGraphNode}.
     *
     * @param node the {@code GridGraphNode} representing the node
     */
    public VisitableGridGraphNode(GridGraphNode node) {
        super(node);
        this.reset();
    }

    /**
     * Retrieves the direction to the parent node.
     *
     * @return the direction to the parent node
     */
    public Directions getDirectionToParent() {
        return this.dirToParent;
    }

    /**
     * Sets the direction to the parent node.
     *
     * @param dirToParent the direction to the parent node
     */
    public void setDirectionToParent(Directions dirToParent) {
        this.dirToParent = dirToParent;
    }

    /**
     * Resets the node by calling the superclass implementation and setting the direction to the parent node to {@link Directions#NONE}.
     */
    @Override
    public void reset() {
        super.reset();
        this.dirToParent = Directions.NONE;
    }

    /**
     * Calculates the hash code for the node.
     *
     * @return the hash code of the node
     */
    @Override
    public int hashCode() {
        return 37 * super.getNode().hashCode();
    }

    /**
     * Checks if the node is equal to the specified object.
     *
     * @param obj the object to compare
     * @return {@code true} if the node is equal to the object, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj.getClass().equals(this.getClass())) {
            var node = (VisitableGridGraphNode) obj;
            return node.getNode().equals(this.getNode());
        }
        return false;
    }

}

