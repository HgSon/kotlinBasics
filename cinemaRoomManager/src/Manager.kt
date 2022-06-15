package cinema

class Manager() {

    var cinema:Cinema = Cinema()
    var exit = false

    fun createScheme():Cinema {
        println("Enter the number of rows:")
        val rows = readln().toInt()
        //val rows = 7
        println("Enter the number of seats in each row:")
        val columns = readln().toInt()
        //val columns = 8
        cinema = Cinema(rows, columns)
        cinema.setSeatClass()
        return cinema
    }

    fun handleBooking() {
        println("Enter a row number:")
        val row = readln().toInt()
        //val row = 7
        println("Enter a seat number in that row:")
        val column = readln().toInt()
        //val column = 8
        val price = cinema.getPrice(row, column)
        println("Ticket price: $$price")

        cinema.bookSeat(row, column)
    }

    fun selectMenu() {
        println("1. Show the seats")
        println("2. Buy a ticket")
        println("0. Exit")
        val answer = readln()
        //val answer = "1"
        when(answer) {
            "1" -> cinema.printScheme()
            "2" -> handleBooking()
            "0" -> exit = true
        }
    }

    fun exitProgram():Boolean {
        return exit
    }
}