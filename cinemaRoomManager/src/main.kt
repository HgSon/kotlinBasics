package cinema

fun main() {
    val manager = Manager()
    val cinema = manager.createScheme()
    cinema.printScheme()
    manager.chooseSeat()
    cinema.printScheme()
}
