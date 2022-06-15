package cinema

class Manager() {

    var cinema:Cinema = Cinema()

    fun createScheme():Cinema {
        println("Enter the number of rows:")
        //val rows = readln().toInt()
        val rows = 7
        println("Enter the number of seats in each row:")
        //val columns = readln().toInt()
        val columns = 8
        cinema = Cinema(rows, columns)
        cinema.setSeatClass()
        return cinema
    }

    fun chooseSeat() {
        println("Enter a row number:")
        //val row = readln().toInt()
        val row = 7
        println("Enter a seat number in that row:")
        //val column = readln().toInt()
        val column = 8
        val price = cinema.getPrice(row, column)
        println("Ticket price: $$price")

        cinema.bookSeat(row, column)
    }
}