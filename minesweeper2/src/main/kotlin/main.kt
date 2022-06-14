package minesweeper

fun main() {
    val gameManager = GameManager()
    gameManager.createGame()

    while (true) {
        gameManager.playGame()
        if (gameManager.tellUsersWin() || gameManager.tellUsersLose()) {
            break
        }
    }
}

