package cinema

import java.lang.Exception

class Manager() {

    var cinema = Cinema()
    var statistician = Statistician()
    var exit = false

    fun createScheme():Cinema {
        println("Enter the number of rows:")
        val rows = readLine()!!.toInt()
        //val rows = 7
        println("Enter the number of seats in each row:")
        val columns = readLine()!!.toInt()
        //val columns = 8
        cinema = Cinema(rows, columns)
        cinema.setSeatClass()
        statistician.cinema = cinema
        return cinema
    }

    private fun handleBooking() {
        while (true) {
            try {
                println()
                println("Enter a row number:")
                val row = readLine()!!.toInt() // readln()

                println("Enter a seat number in that row:")
                val column = readLine()!!.toInt()

                if(cinema.bookSeat(row, column)) {
                    val price = cinema.getPrice(row, column)
                    println()
                    println("Ticket price: $$price")
                    break
                }
            } catch (e: Exception) {
                println("Wrong input!")
            }
        }
    }

    fun selectMenu() {
        println()
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("3. Statistics")
        println("0. Exit")
        when(readLine()) {
            "1" -> cinema.printScheme()
            "2" -> handleBooking()
            "3" -> showStatistics()
            "0" -> exit = true
        }
    }

    fun exitProgram():Boolean {
        return exit
    }

    private fun showStatistics() {
        println()
        println("Number of purchased tickets: ${statistician.countPurchasedTickets()}")
        println("Percentage: ${statistician.percentOfPurchasedTickets()}%")
        println("Current income: $${statistician.calculateCurrentIncome()}")
        println("Total income: $${statistician.calculateTotalIncome()}")
    }
}