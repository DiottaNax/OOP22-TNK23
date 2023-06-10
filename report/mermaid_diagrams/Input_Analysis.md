classDiagram

KeyboardInputController <-- InputController
PlayerOneKeyboardController <|-- KeyboardInputController
PlayerTwoKeyboardController <|-- KeyboardInputController
KeyEventHandler --o KeyboardInputController

class InputController {
    <<interface>>
    +getDirection() : Directions
}

class KeyboardInputController {
    <<interface>>
    +isShooting() : boolean
    +setOnKeyPressed(KeyEvent) : void
    +setOnKeyReleased(KeyEvent) : void
}

class PlayerOneKeyboardController {
    
}

class PlayerTwoKeyboardController {
    
}

class KeyEventHandler {
    +onKeyPressed(KeyEvent)
    +onKeyReleased(KeyEvent)
    +addInputController(KeyboardInputController)
    +removeInputController(KeyboardInputController)
}