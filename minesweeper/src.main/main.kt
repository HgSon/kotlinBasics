package minesweeper

import kotlin.random.Random

fun main() {
    print("How many mines do you want on the field?")
    val mines = readln().toInt()
    val mineField = createField(mines)
    for (line in mineField) {
        println(line.joinToString(""))
    }
}

fun createField(mines: Int):MutableList<MutableList<String>> {
    val mineField = MutableList(Y_LENGTH){ MutableList(X_LENGTH){ "." }}
    var remain = mines
    while(remain > 0) {
        val y = Random.nextInt(0, Y_LENGTH)
        val x = Random.nextInt(0, X_LENGTH)

        if (mineField[y][x] == "X") {
            continue;
        }
        mineField[y][x] = "X"
        remain--
    }
    return mineField
}

const val X_LENGTH = 9
const val Y_LENGTH = 9