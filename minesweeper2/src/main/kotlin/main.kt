package minesweeper

import kotlin.random.Random

fun main() {
    print("How many mines do you want on the field?")
    //val mines = readln().toInt()
    val mines = 10
    val minesweeper = Minesweeper(mines).initMines().countMines()

    minesweeper.showField()

    while (true) {
        println("Set/delete mine marks (x and y coordinates): ")
//        val (xCoord, yCoord) = readln().split(" ").map { it.toInt() }
        val (xCoord, yCoord) = "3 2".split(" ").map { it.toInt() }

        minesweeper.updateField(yCoord, xCoord).checkFoundMine(yCoord, xCoord)

        if (minesweeper.correctMark == mines && minesweeper.incorrectMark == 0) {
            println("Congratulations! You found all the mines!")
            break
        }
    }
}