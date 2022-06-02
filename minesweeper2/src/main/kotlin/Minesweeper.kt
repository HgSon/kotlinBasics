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
                var count = 0

                for (innerY in (y-1)..(y+1)) {
                    for (innerX in (x-1)..(x+1)) {
                        try {
                            if (mineField[innerY][innerX] == "X") {
                                count++
                            }
                        }catch(e: IndexOutOfBoundsException) {

                        }
                    }
                }

                mineField[y][x] = if (count == 0) "." else count.toString()
            }
        }
        return this
    }
}