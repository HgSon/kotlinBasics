package cinema

import cinema.scheme.*

class Cinema(val rows: Int = 7, val columns: Int = 8) {
    val totalSeat = rows * columns
    val scheme = MutableList(rows + 1){ MutableList(columns + 1) { Scheme() }}

    init {
        for (row in 0..rows) {
            for (column in 0..columns) {
                when {
                    row == 0 ->  scheme[row][column] = Sign(column.toString())
                    column == 0 -> scheme[row][column] = Sign(row.toString())
                    else -> scheme[row][column] = Seat()
                }
            }
        }
    }

    fun printScheme() {
        println("Cinema:")
        scheme.forEach{ println(it.map{ it.display }.joinToString(" ")) }
    }

    fun setSeatClass() {
        if (totalSeat > 60) {
            for (row in (rows / 2 + 1)..rows) {
                for (column in 1..columns) {
                    (scheme[row][column] as Seat).seatClass = SeatClass.Royal
                }
            }
        }
    }

    fun getPrice(row: Int, column: Int): Int {
        return (scheme[row][column] as Seat).seatClass.price
    }

    fun bookSeat(row: Int, column: Int):Boolean {
        val seat = scheme[row][column] as Seat
        return if (!seat.isBooked) {
            seat.isBooked = true
            true
        } else {
            println()
            println("That ticket has already been purchased!")
            false
        }
    }
}