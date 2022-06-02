package minesweeper

import kotlin.random.Random

fun main() {
    print("How many mines do you want on the field?")
    //val mines = readln().toInt()
    val mines = 10
    var mineField = createField(mines)
    mineField = countMines(mineField)
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

fun countMines(mineField:MutableList<MutableList<String>>):MutableList<MutableList<String>> {
    var instance = mineField
    for (y in 0 until Y_LENGTH) {
        for (x in 0 until X_LENGTH) {
            //println("this is [$y][$x]")
            if (mineField[y][x] == "X") {
                continue;
            }
            val xMin = if (x - 1 < 0) x else (x - 1)
            val xMax = if (x + 1 > X_LENGTH - 1) x else (x + 1)
            val yMin = if (y - 1 < 0) y else (y - 1)
            val yMax = if (y + 1 > Y_LENGTH - 1) y else (y + 1)

            //println("[y] $yMin ~ $yMax,[x] $xMin ~ $xMax")

            var count = 0
            for (innerY in yMin..yMax) {
                for (innerX in xMin..xMax) {
                    //println()
                    if (mineField[innerY][innerX] == "X") {
                        count++
                        //println(count)
                    }
                }
            }

            instance[y][x] = if (count == 0) "." else count.toString()
        }
    }
    return instance
}