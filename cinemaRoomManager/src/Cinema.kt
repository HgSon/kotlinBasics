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

    fun calculateTotalPrice():Int {
        var vips = 0
        for (row in 1..rows) {
            for (column in 1..columns) {
                if ((scheme[row][column] as Seat).seatClass == SeatClass.VIP) vips++
            }
        }
        return vips * 10 + (totalSeat - vips) * 8
    }

    fun getPrice(row: Int, column: Int): Int {
        return (scheme[row][column] as Seat).seatClass.price
    }

    fun bookSeat(row: Int, column: Int) {
        (scheme[row][column] as Seat).isBooked = true
    }
}