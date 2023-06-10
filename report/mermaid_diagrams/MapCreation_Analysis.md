classDiagram
    WordImpl..>Word
    GameMap --o Word
    GameMapImpl..>GameMap
    

class GameMap{
    <<Interface>>
    +getDestroyableWalls() : Set<Point2D>
    +getWalls() : Set<Point2D>
}

class GameMapImpl{
    -generateWalls() : void

}

class WordImpl{
    -addTower() : void
}

class Word{  
    <<Interface>>
    +notifyEvent(WorldEvent) : void
    +update() : void
}