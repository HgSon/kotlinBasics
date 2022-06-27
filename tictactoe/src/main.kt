package tictactoe

fun main() {
    val tictactoe = Tictactoe()
    tictactoe.drawGrid()
    // tictactoe.checkStatus()
    tictactoe.makeMove()
    tictactoe.drawGrid()
}