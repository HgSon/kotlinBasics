package cinema.scheme

class Seat(var seatClass:SeatClass = SeatClass.VIP, isBooked:Boolean = false) : Scheme() {
    override var display = "S"

    var isBooked = isBooked
        set(value) {
            display = if (value) "B" else "S"
            field = value
        }
}