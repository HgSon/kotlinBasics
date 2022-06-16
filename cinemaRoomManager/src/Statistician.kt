package cinema

import cinema.scheme.Seat
import cinema.scheme.SeatClass

class Statistician {
    var cinema:Cinema = Cinema()

    fun countPurchasedTickets():Int {
        return cinema.scheme.sumOf { it.count { it is Seat && it.isBooked } }
    }

    fun percentOfPurchasedTickets():String {
        return "%.2f".format(countPurchasedTickets().toDouble() / cinema.totalSeat * 100)
    }

    fun calculateTotalIncome():Int {
        var vips = 0
        for (row in 1..cinema.rows) {
            for (column in 1..cinema.columns) {
                if ((cinema.scheme[row][column] as Seat).seatClass == SeatClass.VIP) vips++
            }
        }
        return vips * 10 + (cinema.totalSeat - vips) * 8
    }

    fun calculateCurrentIncome(): Int {
        return cinema.scheme.sumOf { it.sumOf { if(it is Seat && it.isBooked) it.seatClass.price else 0 } }
    }
}