package it.unibo.tnk23.game.graph.impl;

import it.unibo.tnk23.common.Directions;
import it.unibo.tnk23.common.Pair;
import it.unibo.tnk23.game.graph.api.AbstractVisitableNode;

public class VisitableGridGraphNode extends AbstractVisitableNode<GridGraphNode> {

    private Directions dirToParent;

    public VisitableGridGraphNode(Pair<Integer, Integer> gridPos) {
        super(new GridGraphNode(gridPos));
        this.reset();
    }

    public VisitableGridGraphNode(GridGraphNode node) {
        super(node);
        this.reset();
    }

    public Directions getDirectionToParent() {
        return this.dirToParent;
    }

    public void setDirectionToParent(Directions dirToParent) {
        this.dirToParent = dirToParent;
    }

    @Override
    public void reset() {
        super.reset();
        this.dirToParent = Directions.NONE;
    }

    @Override
    public int hashCode() {
        return 37 * super.getNode().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj != null && obj.getClass().equals(this.getClass())){
            var node = (VisitableGridGraphNode) obj;
            return node.getNode().equals(this.getNode());
        }
        return false;
    }
    
}
