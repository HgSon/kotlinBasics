package minesweeper

import kotlin.random.Random

class Minesweeper(private val mines: Int = 0, private val xLength: Int = 9, private val yLength: Int = 9) {
    var mineField = MutableList(yLength){ MutableList(xLength){ "." }}

    fun setField():Minesweeper {
        var remain = mines
        while(remain > 0) {
            val y = Random.nextInt(0, yLength)
            val x = Random.nextInt(0, xLength)

            if (mineField[y][x] == "X") {
                continue
            }
            mineField[y][x] = "X"
            remain--
        }
        return this
    }

    fun countMines():Minesweeper {
        for (y in 0 until yLength) {
            for (x in 0 until xLength) {
                if (mineField[y][x] == "X") {
                    continue
                }
                val xMin = if (x - 1 < 0) x else (x - 1)
                val xMax = if (x + 1 > xLength - 1) x else (x + 1)
                val yMin = if (y - 1 < 0) y else (y - 1)
                val yMax = if (y + 1 > yLength - 1) y else (y + 1)

                var count = 0
                for (innerY in yMin..yMax) {
                    for (innerX in xMin..xMax) {
                        if (mineField[innerY][innerX] == "X") {
                            count++
                        }
                    }
                }

                mineField[y][x] = if (count == 0) "." else count.toString()
            }
        }
        return this
    }
}