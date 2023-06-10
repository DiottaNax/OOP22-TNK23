classDiagram

ColorPickerController --|> Initializable
ColorPickerController --> Scene

GameOverController --o FxGameView
TitleMenuController --o FxGameView
ColorPickerController --o FxGameView

class TitleMenuController {
    + startGame(): void
    + goPickColor(): void
    + initialize(): void
}

class ColorPickerController {
    + initialize(URL, ResourceBundle): void
    + setColor(ActionEvent): void
    + confirm(ActionEvent): void
    + setPlayerSprite(): void
}

class GameOverController {
    + restartGame(): void
    + exit(): void
}

class FxGameView