classDiagram

    GraphDecorator --o Graph~N~
    VisitableGraphDecorator --|> GraphDecorator
    
    VisitableGraph --|> Graph
    GameGraph ..|> VisitableGraphDecorator
    GameGraph --o VisitableGraph

    class Graph~N~{
        <<interface>>
        getNodes(): Set~N~
        getAdjacencies(N): Set~N~
    }

    class GraphDecorator{
        <<abstract>>
    }

    class VisitableGraphDecorator{
        <<abstract>>
    }

    class VisitableGraph~N~{
        <<interface>>
        setGoal(N): void
        getPathFrom(N): Set~N~
    }

    class GameGraph{
        update(): void
    }