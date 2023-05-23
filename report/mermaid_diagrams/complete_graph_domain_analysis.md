classDiagram
    VisitableGraph --o VisitableGraphNode
    Graph --o GraphNode
    GridGraph --o GridGraphNode
    VisitableGridGraph --o VisitableGridGraphNode

    GridGraph ..|> Graph
    GridGraphNode ..|> GraphNode

    VisitableGraph ..|> Graph
    VisitableGridGraph ..|> VisitableGraph
    VisitableGridGraph --|> GridGraph

    VisitableGraphNode ..|> GraphNode
    VisitableGridGraphNode --|> GridGraphNode
    VisitableGridGraphNode ..|> VisitableGraphNode

    class GridGraph{
        initNewGraph(int)
    }
    class Graph{
        getNodes(): Stream~GraphNode~
    }
    class GraphNode{
        <<interface>>
        getAdjacencySet(): Set~GraphNode~
    }
    class GridGraphNode{
        addAdjacentNode(GridGraphNode)
        removeAdjacentNode(GridGraphNode)
    }
    class VisitableGraphNode{
        <<interface>>
        getColor(): NodeColor
        getParent(): Optional~GridGraphNode~
        getDistance(): int
        getDirectionToParent(): Directions
    }
    class VisitableGraph{
        <<interface>>
        +getPathFrom(GraphNode): List~GraphNode~
    }
