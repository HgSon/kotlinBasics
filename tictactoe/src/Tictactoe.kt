package tictactoe

class Tictactoe(val columns: Int = 3, val rows: Int = 3) {
    private val rowLiner = "-"
    private val columnLiner = "|"

    init {
        print("Enter cells: ")
        // val input = readln()
        val input = readLine()!!

        try {
            drawGrid(input)
        } catch (e: Exception) {
            println(e.message)
        }
    }

    fun drawGrid(input: String) {
        if (input.length != columns * rows) {
            throw Exception("[INVALID INPUT] Expected input length = ${columns * rows} , input length = ${input.length}")
        }

        drawRowBoundary()

        for (i in 0..input.lastIndex step columns) {
            println("$columnLiner${input.substring(i, i+3).split("").joinToString(" ")}$columnLiner")
        }

        drawRowBoundary()
    }

    fun drawRowBoundary() {
        // grid + space between grid + space outside grid + boundaries
        repeat(columns + (columns - 1) + 2 + 2) {
            print(rowLiner)
        }
        println()
    }


}