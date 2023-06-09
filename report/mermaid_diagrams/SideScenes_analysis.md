classDiagram

    SideScenesController --|> PlayerInfoController
    SideScenesController --|> RoundInfoController
    Scene --|> GameScene
    class SideScenesController {
        <<interface>>
        +updateGraphics()
    }

    class PlayerInfoController {

    }

    class RoundInfoController {

    }

    class Scene {

    }

    class GameScene {

    }