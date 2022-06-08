package minesweeper

import kotlin.random.Random

class Minesweeper(private val mines: Int = 0, private val xLength: Int = 9, private val yLength: Int = 9) {
    var mineField = MutableList(yLength){ MutableList(xLength){ Cell() }}

    init {
        initMines(mines)
        countMinesAround()
        showField()
    }

    private fun initMines(mines: Int):Minesweeper {
        var remain = mines
        while(remain > 0) {
            val y = Random.nextInt(0, yLength)
            val x = Random.nextInt(0, xLength)

            if (mineField[y][x].hasMine) {
                // do not count around mines
                mineField[y][x].aroundMines = -1
                continue
            }
            mineField[y][x].hasMine = true

            remain--
        }
        return this
    }

    private fun countMinesAround():Minesweeper {
        for (y in 0 until yLength) {
            for (x in 0 until xLength) {
                if (mineField[y][x].hasMine) {
                    // do not count around mines
                    continue
                }
                var count = 0

                for (innerY in (y-1)..(y+1)) {
                    for (innerX in (x-1)..(x+1)) {
                        try {
                            if (mineField[innerY][innerX].hasMine) {
                                count++
                            }
                        }catch(e: IndexOutOfBoundsException) {
                        }
                    }
                }
                mineField[y][x].aroundMines = count
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

        for (y in 1..yLength) {
            print("$y|")
            mineField[y - 1].forEach{ print(it.display()) }
            println("|")
        }
        println("—│$rowSeparator│")
    }

    fun updateField(y: Int, x: Int) {
        val cell = mineField[y -1][x - 1]
        if (cell.aroundMines > 0) {
            println("There is a number here!")
        } else {
            cell.isMarked = !cell.isMarked
            showField()
        }
    }

    fun isUserWin(): Boolean {
        return mineField.none { it.any { it.invalidMarking() } }
    }

    inner class Cell(var hasMine: Boolean = false, var isMarked: Boolean = false, var aroundMines: Int = 0) {
        fun display(): String {
            return if (aroundMines > 0) aroundMines.toString() else if (isMarked) "*"  else "."
        }

        fun invalidMarking(): Boolean {
            return hasMine xor isMarked
        }
    }
}