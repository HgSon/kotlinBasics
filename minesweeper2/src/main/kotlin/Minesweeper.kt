package minesweeper

import kotlin.random.Random

class Minesweeper(private val mines: Int = 0, private val xLength: Int = 9, private val yLength: Int = 9) {
    var mineField = MutableList(yLength){ MutableList(xLength){ "." }}
    var coveredField = MutableList(yLength){ MutableList(xLength){ "." }}
    var correctMark = 0
    var incorrectMark = 0

    fun initMines():Minesweeper {
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

                coveredField[y][x] = if (count == 0) "." else count.toString()
            }
        }
        return this
    }

    fun showField() {
        var xCoords = ""
        var rowSeparator = ""
        for (i in 1..xLength) {
            xCoords += i
            rowSeparator += "-"
        }

        println(" │$xCoords│")
        println("—│$rowSeparator│")

        for (i in 1..yLength) {
            println("$i|${coveredField[i - 1].joinToString("")}|")
        }
        println("—│$rowSeparator│")
    }

    fun updateField(y: Int, x: Int):Minesweeper {
        if (coveredField[y - 1][x - 1] == ".") {
            coveredField[y - 1][x - 1] = "*"
            showField()
        } else if (coveredField[y - 1][x - 1] == "*") {
            coveredField[y - 1][x - 1] = "."
            showField()
        } else {
            println("There is a number here!")
        }

        return this
    }

    fun checkFoundMine(y: Int, x: Int) {
        // "X", "."
        val isMine = if (mineField[y - 1][x - 1] == "X") true else false

        // "*", ".", number
        val isMarked: Boolean
        when (coveredField[y - 1][x - 1]) {
            "*" -> isMarked = true
            "." -> isMarked = false
            else -> return
        }

        if (isMine && isMarked) {
            correctMark++
        } else if (!isMine && isMarked) {
            incorrectMark++
        } else if (isMine && !isMarked) {
            correctMark--
        } else if (!isMine && !isMarked) {
            incorrectMark--
        }
    }
}