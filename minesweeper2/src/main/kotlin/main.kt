package minesweeper

import kotlin.random.Random

fun main() {
    print("How many mines do you want on the field?")
    println()
    //val mines = readln().toInt()
    val mines = 10
    val minesweeper = Minesweeper(mines).setField().countMines()

    for (line in minesweeper.mineField) {
        println(line.joinToString(""))
    }
}