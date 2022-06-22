package tictactoe

import kotlin.math.abs

class Tictactoe(val lengthOfSide: Int = 3) {
    private val rowLiner = "-"
    private val columnLiner = "|"
    private val grid = MutableList(lengthOfSide * lengthOfSide){ Cell() }


    init {
        print("Enter cells: ")
        // val input = readln()
        val input = readLine()!!

        try {
            setGrid(input)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun setGrid(input: String) {
        if (input.length != lengthOfSide * lengthOfSide) {
            throw Exception("[INVALID INPUT] Expected input length = ${lengthOfSide * lengthOfSide} , input length = ${input.length}")
        }

        grid.forEachIndexed { index, cell -> run {
            cell.value = input[index].toString()
            cell.columnNumber = index % lengthOfSide
            cell.rowNumber = index / lengthOfSide
        } }

       // grid.forEachIndexed { index, cell ->  if(index % lengthOfSide == lengthOfSide - 1 ) println(" [${cell.rowNumber},${cell.columnNumber}]") else print(" [${cell.rowNumber},${cell.columnNumber}]") }

    }

    fun drawGrid() {
        drawRowBoundary()

        for (i in grid.indices step lengthOfSide) {
            println("$columnLiner ${(grid.subList(i, i + lengthOfSide).map { it.value }).joinToString(" ")} $columnLiner")
        }

        drawRowBoundary()
    }

    fun drawRowBoundary() {
        // grid + space between grid + space outside grid + boundaries
        repeat(lengthOfSide + (lengthOfSide - 1) + 2 + 2) {
            print(rowLiner)
        }
        println()
    }

    fun checkStatus() {
        when {
            impossible() -> println("Impossible")
            winGame("X") -> println("X wins")
            winGame("O") -> println("O wins")
            notFinished() -> println("Game not finished")
            drawGame() -> println("Draw")
        }
    }



    fun winGame(user: String):Boolean {
//        println("grid")
//        grid.forEachIndexed { index, cell ->  if(index % lengthOfSide == lengthOfSide - 1 ) println(" [${cell.rowNumber},${cell.columnNumber}]") else print(" [${cell.rowNumber},${cell.columnNumber}]") }

        val cells = grid.filter { it.value == user }
//        println("cells")
//        cells.forEachIndexed { index, cell ->  print("[${cell.rowNumber},${cell.columnNumber}] ") }
//        println()


        // horizontal or vertical
        for (i in 0 until lengthOfSide) {
            if (cells.count{ it.rowNumber == i} == lengthOfSide || cells.count{ it.columnNumber == i} == lengthOfSide) {
                return true
            }
        }

        // diagonal
        val leftDiagonal = cells.filter { it.columnNumber == it.rowNumber }
        val rightDiagonal = cells.filter { it.columnNumber == abs(it.rowNumber - (lengthOfSide -  1)) }

//        println("is $user win?")
//        println("leftDiagonal")
//        leftDiagonal.sortedBy { it.columnNumber }.forEach{ print("[${it.columnNumber}, ${it.rowNumber}]") }
//        println()
//        println("rightDiagonal")
//        rightDiagonal.sortedBy { it.columnNumber }.forEach{ print("[${it.columnNumber}, ${it.rowNumber}]") }
//        println()

        if (leftDiagonal.size == lengthOfSide || rightDiagonal.size == lengthOfSide) {
            return true
        }

        return false
    }

    fun drawGame():Boolean {
        return !grid.any { it.value == "_"} && !winGame("X") && !winGame("O")
    }

    fun notFinished():Boolean {
        return grid.any { it.value == "_"}
    }

    fun impossible():Boolean {
        return abs(grid.count { it.value == "X"} - grid.count { it.value == "O"}) > 1 || winGame("X") && winGame("O")
    }

    inner class Cell() {
        var value: String = "_"
        var rowNumber = -1
        var columnNumber = -1
    }
}