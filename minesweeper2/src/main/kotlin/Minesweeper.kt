package minesweeper

import kotlin.random.Random

class Minesweeper(mines: Int = 0, private val xLength: Int = 9, private val yLength: Int = 9) {
    var mineField = MutableList(yLength){ MutableList(xLength){ Cell() }}

    init {
        setMines(mines)
        countMinesAround()
        showField()
    }

    private fun setMines(mines: Int):Minesweeper {
        var remain = mines
        while(remain > 0) {
            val y = Random.nextInt(0, yLength)
            val x = Random.nextInt(0, xLength)

            if (mineField[y][x].hasMine) {
                continue
            }
            mineField[y][x].hasMine = true

            remain--
        }
        return this
    }

    fun changeField(yCoord: Int, xCoord: Int) {
        val cell = mineField[yCoord -1][xCoord - 1]
        if (cell.hasMine) {
            setMines(1)
            cell.hasMine = false
            countMinesAround()
        }
    }

    private fun countMinesAround():Minesweeper {
        for (y in 0 until yLength) {
            for (x in 0 until xLength) {
                if (mineField[y][x].hasMine) {
                    // do not count around mines
                    mineField[y][x].aroundMines = -1
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

        println()
        println(" │$xCoords│")
        println("—│$rowSeparator│")

        for (y in 1..yLength) {
            print("$y|")
            mineField[y - 1].forEach{ print(it.display()) }
            println("|")
        }
        println("—│$rowSeparator│")
    }

    fun updateField(yCoord: Int, xCoord: Int, action: String) {
        val y = yCoord - 1
        val x = xCoord - 1
        val cell = mineField[y][x]
        when (action) {
            "mine" -> {
                if (cell.isFree) {
                    println("this is free cell")
                } else {
                    cell.isMarked = !cell.isMarked
                }
            }

            "free" -> {
                cell.isFree = true
                if (cell.aroundMines == 0) {
                    openAround(y,x)
                }
            }
        }
        showField()
    }

    fun openAround(y: Int, x: Int) {
        for (targetY in (y-1)..(y+1)) {
            for (targetX in (x-1)..(x+1)) {
                try {
                    val cell = mineField[targetY][targetX]

                    if (cell.isFree) {
                        continue
                    }

                    cell.isFree = true
                    if (cell.aroundMines == 0) {
                        openAround(targetY, targetX)
                    }
                }catch(e: IndexOutOfBoundsException) {
                }
            }
        }
    }

    fun isUserWin(): Boolean {
        return mineField.all { it.all { it.checkCellValidity() } }
    }

    fun isUserLose(): Boolean {
        return mineField.any { it.any { it.detonate() }}
    }

    inner class Cell(var hasMine: Boolean = false, var isMarked: Boolean = false, var aroundMines: Int = 0, var isFree: Boolean = false) {
        /**
         * not free :
         * not marked, not free : .
         * marked (not free) : *
         *
         * free :
         * hasMine : X
         * aroundMines == 0 : /
         * aroundMines > 0 : number (1~8)
         * */
        fun display(): String {
            return when {
                !isFree && isMarked  -> "*"
                !isFree && !isMarked  -> "."
                isFree && hasMine -> "X"
                isFree && aroundMines == 0 -> "/"
                else -> aroundMines.toString()
            }
        }

        fun invalidMarking(): Boolean {
            /** hasMine, not Marked
             *  not Mine, Marked
             * */
            return hasMine xor isMarked
        }

        fun validFree(): Boolean {
            return isFree && !hasMine
        }

        fun checkCellValidity(): Boolean {
            return !invalidMarking() || validFree()
        }

        fun detonate(): Boolean {
            return isFree && hasMine
        }
    }
}