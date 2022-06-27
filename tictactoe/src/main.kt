package tictactoe

fun main() {
    val tictactoe = Tictactoe()
    tictactoe.drawGrid()
    while (!tictactoe.endGame()) {
        tictactoe.makeMove()
        tictactoe.drawGrid()
    }
}