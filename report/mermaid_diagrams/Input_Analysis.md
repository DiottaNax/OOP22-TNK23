classDiagram

KeyboardInputController --> InputController
PlayerOneKeyboardController --|> KeyboardInputController
PlayerTwoKeyboardController --|> KeyboardInputController
KeyEventHandler

class InputController {
    <<interface>>
    +getDirection() : Directions
}

class KeyboardInputController {
    +isShooting() : boolean
    +setOnKeyPressed(e : KeyEvent) : void
    +setOnKeyReleased(e : KeyEvent) : void
}

class PlayerOneKeyboardController {
    
}

class PlayerTwoKeyboardController {
    
}

class KeyEventHandler {
    +onKeyPressed(e : KeyEvent)
    +onKeyReleased(e : KeyEvent)
    +addInputController(k : KeyboardInputController)
    +removeInputController(k : KeyboardInputController)
}