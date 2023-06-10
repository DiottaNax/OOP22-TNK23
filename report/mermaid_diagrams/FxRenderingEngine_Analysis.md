classDiagram
    Gameview--o RenderingEngine
    FxRenderingEngine..>RenderingEngine
    class Gameview{

    }
    class FxRenderingEngine{
    
    
        -setCachedSprites() : void
        +addSprites() : void
        + update() : void
    }
    
    class RenderingEngine{
        <<Interface>>
        +getgamePane() : Pane
        +render() : void
    }